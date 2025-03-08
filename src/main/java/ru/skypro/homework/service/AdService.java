package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;

public interface AdService {
    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image);

    Ad getAd(Integer Id);

    void deleteAd(Integer id);
}
