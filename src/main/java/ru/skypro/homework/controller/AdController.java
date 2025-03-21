package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

@CrossOrigin(value = "http://localhost:3000")
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
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавить объявление", description = "Создаёт новое объявление")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestPart("properties") CreateOrUpdateAdDTO properties,
                                       @RequestPart("image") MultipartFile image) {
        AdDTO adDTO = adService.addAd(properties, image);
        return ResponseEntity.status(200).body(adDTO);
    }

    @Operation(summary = "Получить объявление по ID", description = "Возвращает объявление по указанному ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @Operation(summary = "Удалить объявление", description = "Удаляет объявление по указанному ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновить объявление", description = "Обновляет информацию об объявлении по ID")
    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDTO ad) {
        return ResponseEntity.ok(adService.updateAd(id, ad));
    }

    @Operation(summary = "Получить мои объявления", description = "Возвращает список объявлений, созданных текущим пользователем")
    @GetMapping("/me")
    public ResponseEntity <AdsDTO> getUserAds() {
        return ResponseEntity.ok(adService.getUserAds());
    }

    @Operation(summary = "Обновить изображение объявления", description = "Обновляет картинки объявления")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id,
                                            @RequestPart ("image") MultipartFile image) {
        return ResponseEntity.noContent().build();
    }
}