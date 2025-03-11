package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseEntity<CommentsDTO> getComments(Integer adId) {
        return ResponseEntity.ok(new CommentsDTO());
    }

    @Override
    public ResponseEntity<CommentsDTO> addComment(Integer adId, CreateOrUpdateCommentDTO comment) {
        return ResponseEntity.ok(new CommentsDTO());
    }

    @Override
    public ResponseEntity<Void> deleteComment(Integer adId, Integer commentId) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CommentDTO> updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO comment) {
        return ResponseEntity.ok(new CommentDTO());
    }
}
