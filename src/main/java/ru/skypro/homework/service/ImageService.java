package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    void addImage(int id, MultipartFile file) throws IOException;

    byte[] generateImage(Path filePath) throws IOException;
}
