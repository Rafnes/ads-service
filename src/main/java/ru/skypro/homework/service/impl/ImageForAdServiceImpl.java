package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

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
    public void addImage(int adId, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }

        ExtendedAdDTO ad = adService.getAd(adId);
        // Создаем путь для сохранения изображения
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename(), "File name cannot be null");
        Path filePath = Path.of(imageDir, adId + "." + getExtension(originalFilename));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        // Уменьшаем изображение
        byte[] resizedImage;
        try {
            resizedImage = resizeImage(file.getBytes(), 800, 600); // Устанавливаем размеры 800x600
        } catch (IOException e) {
            throw new RuntimeException("Failed to resize image", e);
        }

        // Сохраняем изображение на диск
        Files.write(filePath, resizedImage);

        // Ищем существующее изображение или создаем новое
        Image image = imageRepository.findByAdId(adId).orElse(new Image());
        image.setAd(convertToAd(ad));
        image.setFilePath(filePath.toString()); // Сохраняем путь к изображению
        image.setFileSize(resizedImage.length);
        image.setMediaType(file.getContentType());
        imageRepository.save(image);
    }

    @Override
    public byte[] generateImage(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            BufferedImage image = ImageIO.read(bis);

            if (image == null) {
                throw new IOException("Could not read image from file: " + filePath);
            }

            // Создаем уменьшенную версию изображения
            int newWidth = 150;
            int newHeight = (int) ((newWidth / (double) image.getWidth()) * image.getHeight());
            BufferedImage preview = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, newWidth, newHeight, null);
            graphics.dispose();

            // Записываем изображение в ByteArrayOutputStream
            ImageIO.write(preview, "png", baos);
            return baos.toByteArray();
        }
    }

    @Override
    public byte[] resizeImage(byte[] originalImage, int width, int height) throws IOException {
        if (originalImage == null) {
            throw new IllegalArgumentException("Original image bytes cannot be null");
        }

        // Чтение оригинального изображения
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(originalImage)) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            if (bufferedImage == null) {
                throw new IOException("Could not read image");
            }

            // Создание нового изображения с заданными размерами
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = resizedImage.createGraphics();

            // Масштабирование изображения
            graphics.drawImage(bufferedImage, 0, 0, width, height, null);
            graphics.dispose();

            // Запись изображения в массив байтов
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ImageIO.write(resizedImage, "jpg", outputStream);
                return outputStream.toByteArray();
            }
        }
    }

    @Override
    public String getExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            throw new IllegalArgumentException("Filename must contain an extension");
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public Ad convertToAd(ExtendedAdDTO adDTO) {
        if (adDTO == null) {
            throw new IllegalArgumentException("adDTO cannot be null");
        }
        Ad ad = new Ad();
        ad.setId(adDTO.getPk());
        ad.setTitle(adDTO.getTitle());
        ad.setDescription(adDTO.getDescription());
        ad.setPrice(adDTO.getPrice());

        return ad;
    }
}