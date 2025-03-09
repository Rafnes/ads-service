package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.Comment;

import java.util.List;

public interface CommentService {
    ResponseEntity<List<Comment>> getComments(Integer adId);

    ResponseEntity<Comment> addComment(Integer adId, Comment comment);

    ResponseEntity<Void> deleteComment(Integer adId, Integer commentId);
}
