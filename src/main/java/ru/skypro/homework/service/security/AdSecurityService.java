package ru.skypro.homework.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;

@Service
public class AdSecurityService {
    private final AdRepository adRepository;

    public AdSecurityService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public boolean isOwner(Integer adId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new AdNotFoundException(adId));

        return ad.getAuthor().equals(currentUsername);
    }
}
