package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd() {
        return ResponseEntity.status(201).body(new Ad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new Ad());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id, @RequestBody Ad ad) {
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    public ResponseEntity<List<Ad>> getAdsMe() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }
}
