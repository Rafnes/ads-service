package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentController {
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Integer adId) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer adId, @RequestBody CommentDTO comment) {
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CommentDTO comment) {
        return ResponseEntity.ok(comment);
    }
}
