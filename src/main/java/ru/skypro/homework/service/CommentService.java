package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    ResponseEntity<Comments> getComments(Integer adId);

    ResponseEntity<Comments> addComment(Integer adId, CreateOrUpdateComment comment);

    ResponseEntity<Void> deleteComment(Integer adId, Integer commentId);

    ResponseEntity<Comment> updateComment(Integer adId,
                                          Integer commentId,
                                          CreateOrUpdateComment comment);
}
