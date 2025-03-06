package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    @GetMapping
    public ResponseEntity<List<AdDTO>> getAllAds() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<AdDTO> addAd() {
        return ResponseEntity.status(201).body(new AdDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDTO> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new AdDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAds(@PathVariable Integer id, @RequestBody AdDTO ad) {
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AdDTO>> getAdsMe() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }
}
