package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentService {
    ResponseEntity<CommentsDTO> getComments(Integer adId);

    ResponseEntity<CommentsDTO> addComment(Integer adId, CreateOrUpdateCommentDTO comment);

    ResponseEntity<Void> deleteComment(Integer adId, Integer commentId);

    ResponseEntity<CommentDTO> updateComment(Integer adId,
                                             Integer commentId,
                                             CreateOrUpdateCommentDTO comment);
}
