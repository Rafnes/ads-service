package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.nio.file.Path;

@Service
@Transactional
public class ImageForUserServiceImpl implements ImageService {

    @Value("${ad.image.dir.path}")
    private String imageDir;
    private final UserService userService;
    private final ImageRepository imageRepository;

    public ImageForUserServiceImpl(UserService userService, ImageRepository imageRepository) {
        this.userService = userService;
        this.imageRepository = imageRepository;
    }

    // Ждет готовности userService

    @Override
    public void addImage(int id, MultipartFile file) throws IOException {
    }

    @Override
    public byte[] generateImage(Path filePath) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] resizeImage(byte[] originalImage, int width, int height) throws IOException {
        return new byte[0];
    }

    @Override
    public String getExtension(String filename) {
        return null;
    }
}