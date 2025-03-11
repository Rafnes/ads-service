package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseEntity<Comments> getComments(Integer adId) {
        return ResponseEntity.ok(new Comments());
    }

    @Override
    public ResponseEntity<Comments> addComment(Integer adId, CreateOrUpdateComment comment) {
        return ResponseEntity.ok(new Comments());
    }

    @Override
    public ResponseEntity<Void> deleteComment(Integer adId, Integer commentId) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Comment> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        return ResponseEntity.ok(new Comment());
    }
}
