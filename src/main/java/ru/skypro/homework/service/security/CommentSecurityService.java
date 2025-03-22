package ru.skypro.homework.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;

@Service
public class CommentSecurityService {
    private final CommentRepository commentRepository;

    public CommentSecurityService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean isOwner(Integer commentId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        return comment.getAuthor().equals(currentUsername);
    }
}