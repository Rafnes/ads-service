package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public Ads getAllAds() {
        return new Ads();
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
        return new Ad();
    }

    @Override
    public Ad getAd(Integer Id) {
        return new Ad();
    }

    @Override
    public void deleteAd(Integer id) {

    }
}
