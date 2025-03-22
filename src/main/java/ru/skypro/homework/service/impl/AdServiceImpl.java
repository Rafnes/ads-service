package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    private final ImageRepository imageRepository;
  
    private final AdMapper adMapper;

    public AdServiceImpl(AdRepository adRepository, ImageRepository imageRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.imageRepository = imageRepository;
        this.adMapper = adMapper;
    }


    /**
     * Возвращает список всех объявлений из базы данных.
     *
     * @return {@link AdsDTO} содержащий список объявлений
     */
    @Override
    public AdsDTO getAllAds() {
        List<Ad> adsList = adRepository.findAll();
        List<AdDTO> adDTOList = new ArrayList<>();
        for (Ad ad : adsList) {
            AdDTO adDTO = adMapper.toDtoAdDTO(ad);
            adDTOList.add(adDTO);
        }
        return new AdsDTO(adDTOList);
    }


    /**
     * Сохраняет новое объявление в базу данных. Изображение объявления извлекается
     * из данного MultipartFile и сохраняется в базу данных.
     *
     * @param properties свойства объявления, за исключением изображения
     * @param imageFile  изображение объявления
     * @return сохраненное объявление
     */
    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO properties, MultipartFile imageFile) {
        Ad model = adMapper.toModel(properties);

        // Создаём новый объект Image
        Image image = new Image();
        image.setFilePath(imageFile.getOriginalFilename());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());

        // Сохраняем изображение перед добавлением объявления
        image = imageRepository.save(image);
        model.setImage(image);

        adRepository.save(model);
        return adMapper.toDtoAdDTO(model);
    }


    /**
     * Возвращает объявление по ID
     *
     * @param id ID объявления
     * @return {@link ExtendedAdDTO} с информацией об объявлении
     */
    @Override
    public ExtendedAdDTO getAd(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        return adMapper.toDtoExtendedAdDTO(ad);
    }


    /**
     * Удаляет объявление из базы данных.
     * <p>
     * Удаляет объявление с указанным {@code id} из базы данных. Если объявление
     * с таким ID не найдено, выбрасывается исключение.
     *
     * @param id ID объявления, которое нужно удалить
     */
    @Override
    public void deleteAd(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow();

        // Удаляем изображение, если оно есть
        if (ad.getImage() != null) {
            imageRepository.delete(ad.getImage());
        }

        adRepository.delete(ad);
    }


    /**
     * Обновляет информацию об объявлении
     * <p>
     * Обновляет информацию об объявлении с указанным {@code id} согласно данным
     * из {@code createOrUpdateAdDTO}. Изображение объявления не может быть
     * обновлено этим методом.
     *
     * @param id                  ID объявления
     * @param createOrUpdateAdDTO новые данные объявления
     * @return {@link AdDTO} с обновленными данными
     */
    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepository.findById(id).orElseThrow();
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        return adMapper.toDtoAdDTO(ad);
    }


    /**
     * Возвращает список объявлений, созданных текущим пользователем
     * <p>
     * Возвращает список {@link AdDTO} объявлений, созданных текущим
     * пользователем.
     *
     * @return {@link AdsDTO} с информацией список объявлений
     */
    @Override
    public AdsDTO getUserAds() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = Integer.parseInt(userDetails.getUsername());
        List<Ad> adsList = adRepository.findAllByAuthorId(userId);
        List<AdDTO> adDTOList = new ArrayList<>();
        for (Ad ad : adsList) {
            AdDTO adDTO = adMapper.toDtoAdDTO(ad);
            adDTOList.add(adDTO);
        }
        return new AdsDTO(adDTOList);
    }


    /**
     * Обновляет изображение объявления.
     * <p>
     * Обновляет изображение объявления с указанным {@code id} на новое
     * изображение, переданное в {@code image}.
     *
     * @param id        ID объявления
     * @param imageFile новое изображение
     */
    @Override
    public void updateAdImage(Integer id, MultipartFile imageFile) {
        Ad ad = adRepository.findById(id).orElseThrow();

        // Удаляем старое изображение, если оно есть
        if (ad.getImage() != null) {
            imageRepository.delete(ad.getImage());
        }

        // Создаём новое изображение
        Image image = new Image();
        image.setFilePath(imageFile.getOriginalFilename());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());

        image = imageRepository.save(image);
        ad.setImage(image);
        adRepository.save(ad);
    }
}
