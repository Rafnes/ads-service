package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

public interface AdService {
    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO properties, MultipartFile image);

    ExtendedAdDTO getAd(Integer Id);

    void deleteAd(Integer id);

    AdDTO updateAd(Integer id, CreateOrUpdateAdDTO ad);

    AdsDTO getUserAds();

    void updateAdImage(Integer id, MultipartFile image);
}
