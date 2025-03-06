package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentController {
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Integer adId) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Integer adId, @RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }
}
