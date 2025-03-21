package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Tag(name = "Комментарии")
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Комментарии", description = "Методы для работы с комментариями к объявлениям")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Получить все комментарии", description = "Возвращает список всех комментариев для указанного объявления")
    @GetMapping
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Integer adId) {
        return commentService.getComments(adId);
    }

    @Operation(summary = "Добавить комментарий", description = "Добавляет новый комментарий к объявлению")
    @PostMapping
    public ResponseEntity<CommentsDTO> addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateCommentDTO comment) {
        return commentService.addComment(adId, comment);
    }

    @Operation(summary = "Удалить комментарий", description = "Удаляет комментарий по его ID")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return commentService.deleteComment(adId, commentId);
    }

    @Operation(summary = "Обновить комментарий", description = "Обновляет текст комментария по его ID")
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO comment) {
        return commentService.updateComment(adId, commentId, comment);
    }
}