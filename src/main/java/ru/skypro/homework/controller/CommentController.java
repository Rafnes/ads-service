package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии", description = "Методы для работы с комментариями к объявлениям")
public class CommentController {

    @Operation(summary = "Получить все комментарии", description = "Возвращает список всех комментариев для указанного объявления")
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Integer adId) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Operation(summary = "Добавить комментарий", description = "Добавляет новый комментарий к объявлению")
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer adId, @RequestBody CommentDTO comment) {
        return ResponseEntity.ok(comment);
    }

    @Operation(summary = "Удалить комментарий", description = "Удаляет комментарий по его ID")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить комментарий", description = "Обновляет текст комментария по его ID")
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CommentDTO comment) {
        return ResponseEntity.ok(comment);
    }
}
