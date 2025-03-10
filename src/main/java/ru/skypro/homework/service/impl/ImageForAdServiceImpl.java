package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class ImageForAdServiceImpl implements ImageService {

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