package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public AdsDTO getAllAds() {
        return new AdsDTO();
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO properties, MultipartFile image) {
        return new AdDTO();
    }

    @Override
    public ExtendedAdDTO getAd(Integer Id) {
        return new ExtendedAdDTO();
    }

    @Override
    public void deleteAd(Integer id) {

    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO ad) {
        return new AdDTO();
    }

    @Override
    public AdsDTO getUserAds() {
        return new AdsDTO();
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image) {

    }
}
