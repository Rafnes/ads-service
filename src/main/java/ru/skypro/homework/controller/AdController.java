package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Объявления")
@RequestMapping("/ads")
@Tag(name = "Управление объявлениями", description = "Методы для работы с объявлениями")
public class AdController {

    @Operation(summary = "Получить все объявления", description = "Возвращает список всех объявлений")
    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Operation(summary = "Добавить объявление", description = "Создаёт новое объявление")
    @PostMapping
    public ResponseEntity<Ad> addAd() {
        return ResponseEntity.status(201).body(new Ad());
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