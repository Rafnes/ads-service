package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
@Primary
@Service
@Transactional
public class ImageForUserServiceImpl implements ImageService {

    @Value("${users.image.dir.path}")
    private String imageDir;
    private final ImageRepository imageRepository;

    public ImageForUserServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // Ждет готовности userService

    @Override
    public Image addImage(int id, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }

        // Ищем существующее изображение или создаем новое
        //
        Image image;
        if (id == 0) {
            //это я делаю чтобы получить айдишник изображения и использоваь его в имени файла. Можно переписать имя на getName тогда это будет не нужно
            image = new Image();
            image.setFilePath("");
            image.setFileSize(0);
            image.setMediaType("");
            imageRepository.save(image);
        } else {
            image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        }

        // Создаем путь для сохранения изображения
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename(), "File name cannot be null");
        Path filePath = Path.of(imageDir, image.getId() + "." + getExtension(originalFilename));
        Files.deleteIfExists(filePath);
        Files.createDirectories(filePath.getParent());

        // Уменьшаем изображение
        /*  byte[] resizedImage;
            try {
                resizedImage = resizeImage(file.getBytes(), 800, 600); // Устанавливаем размеры 800x600
            } catch (IOException e) {
                throw new RuntimeException("Failed to resize image", e);
            }*/

        // Сохраняем изображение на диск
        Files.write(filePath, file.getBytes());

        //image.setAd(convertToAd(ad));
        image.setFilePath(filePath.toString()); // Сохраняем путь к изображению
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        imageRepository.save(image);
        return image;
    }

    @Override
    public byte[] generateImage(Path filePath) throws IOException {
        return new byte[0];
    }

    private String getExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            throw new IllegalArgumentException("Filename must contain an extension");
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}