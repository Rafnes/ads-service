package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "Методы для работы с объявлениями")
public class AdController {
    private final AdService adService;

    AdController(AdService adService) {
        this.adService = adService;
    }

    @Operation(summary = "Получить все объявления", description = "Возвращает список всех объявлений")
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавить объявление", description = "Создаёт новое объявление")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image) {
        Ad ad = adService.addAd(properties, image);
        return ResponseEntity.status(200).body(ad);
    }

    @Operation(summary = "Получить объявление по ID", description = "Возвращает объявление по указанному ID")
    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new Ad());
    }

    @Operation(summary = "Удалить объявление", description = "Удаляет объявление по указанному ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновить объявление", description = "Обновляет информацию об объявлении по ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id, @RequestBody Ad ad) {
        return ResponseEntity.ok(ad);
    }

    @Operation(summary = "Получить мои объявления", description = "Возвращает список объявлений, созданных текущим пользователем")
    @GetMapping("/me")
    public ResponseEntity<List<Ad>> getAdsMe() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Operation(summary = "Обновить изображение объявления", description = "Загружает или изменяет изображение для указанного объявления")
    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }
}