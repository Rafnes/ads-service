package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageNotSavedException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final ImageRepository imageRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;

    public AdServiceImpl(AdRepository adRepository,
                         ImageRepository imageRepository,
                         AdMapper adMapper,
                         @Qualifier("adImageService") ImageService imageService,
                         UserRepository userRepository) {
        this.adRepository = adRepository;
        this.imageRepository = imageRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
        this.userRepository = userRepository;
    }

    @Override
    public AdsDTO getAllAds() {
        List<Ad> adsList = adRepository.findAll();
        List<AdDTO> adDTOList = adsList.stream()
                .map(adMapper::toDtoAdDTO)
                .peek(this::setImageForDto)
                .collect(Collectors.toList());

        AdsDTO adsDto = adMapper.toAds(adsList.size(), adsList);
        adsDto.setResults(adDTOList);
        return adsDto;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO properties, MultipartFile imageFile) {
        Ad model = adMapper.toModel(properties);
        model.setAuthor(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
        Ad savedModel = adRepository.save(model);

        try {
            Image image = imageService.addImage(savedModel.getId(), imageFile);
            savedModel.setImage(image);
        } catch (IOException e) {
            throw new ImageNotSavedException("Ошибка при сохранении изображения");
        }
        adRepository.save(savedModel);
        AdDTO adDto = adMapper.toDtoAdDTO(model);
        setImageForDto(adDto);
        return adMapper.toDtoAdDTO(model);
    }

    @Override
    public ExtendedAdDTO getAd(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        ExtendedAdDTO adDto = adMapper.toDtoExtendedAdDTO(ad);
        if (adDto.getImage() != null) {
            adDto.setImage("/ads/" + adDto.getImage() + "/image/get");
        }
        return adDto;
    }

    @Override
    public void deleteAd(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        if (ad.getImage() != null) {
            imageRepository.delete(ad.getImage());
        }
        adRepository.delete(ad);
        log.info("Удалено объявление: {}", ad);
    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        adRepository.save(ad);
        log.info("Обновлено объявление: {}", ad);
        return adMapper.toDtoAdDTO(ad);
    }

    @Override
    public AdsDTO getUserAds() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Integer userId = user.getId();

        List<Ad> adsList = adRepository.findAllByAuthorId(userId);
        List<AdDTO> adDTOList = adsList.stream()
                .map(adMapper::toDtoAdDTO)
                .peek(this::setImageForDto)
                .collect(Collectors.toList());

        return new AdsDTO(adDTOList);
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile imageFile) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        int imageId = (ad.getImage() != null) ? ad.getImage().getId() : 0;
        try {
            Image image = imageService.addImage(id, imageFile);
            ad.setImage(image);
            adRepository.save(ad);
            log.info("Обновлена картинка {} для объявления: {}", image, ad);
        } catch (IOException e) {
            throw new ImageNotSavedException("Ошибка при обновлении изображения");
        }
    }

    public void downloadAvatarFromFileSystem(int id, HttpServletResponse response)
            throws IOException {

        Image image = imageRepository.findById(id).orElseThrow(() ->
                new AdNotFoundException(id));

        Path path = Path.of(image.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(image.getMediaType());
            response.setContentLength((int) image.getFileSize());
            is.transferTo(os);
        }
    }

    private void setImageForDto(AdDTO adDto) {
        adDto.setImage("/ads/" + adDto.getImage() + "/image/get");
    }
}
