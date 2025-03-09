package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdService {
    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image);

    ExtendedAd getAd(Integer Id);

    void deleteAd(Integer id);

    Ad updateAd(Integer id, CreateOrUpdateAd ad);

    Ads getUserAds();

    void updateAdImage(Integer id, MultipartFile image);
}
