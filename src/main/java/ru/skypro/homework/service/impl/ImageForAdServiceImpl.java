package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.FailedToReadFileException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
@Transactional
public class ImageForAdServiceImpl implements ImageService {

    @Value("${ad.image.dir.path}")
    private String imageDir;
    private final AdService adService;
    private final ImageRepository imageRepository;

    public ImageForAdServiceImpl(AdService adService, ImageRepository imageRepository) {
        this.adService = adService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image addImage(int adId, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }

        ExtendedAdDTO ad = adService.getAd(adId);

        String originalFilename = Objects.requireNonNull(file.getOriginalFilename(), "File name cannot be null");
        Path filePath = Path.of(imageDir, adId + "." + getExtension(originalFilename));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        Files.write(filePath, file.getBytes());
        Image image = imageRepository.findById(adId).orElse(new Image());
        image.setFilePath(filePath.toString());
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        imageRepository.save(image);

        return image;
    }

    @Override
    public byte[] generateImage(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            BufferedImage image = ImageIO.read(is);

            if (image == null) {
                throw new FailedToReadFileException("Could not read image from file: " + filePath);
            }

            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }

    private String getExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            throw new IllegalArgumentException("Filename must contain an extension");
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}