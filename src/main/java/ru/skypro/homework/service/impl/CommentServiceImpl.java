package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    final private CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    /**
     * Получить все комментарии к объявлению.
     *
     * @param adId идентификатор объявления
     * @return объект CommentsDTO, содержащий список комментариев и их количество
     */
    @Override
    public CommentsDTO getComments(Integer adId) {
        List<Comment> commentList = commentRepository.findAllByAdId(adId);
        return commentMapper.toDtoCommentsDTO(commentList.size(), commentList);
    }


    /**
     * Добавить комментарий к объявлению.
     *
     * @param adId    идентификатор объявления
     * @param comment объект CreateOrUpdateCommentDTO, содержащий данные для создания или обновления комментария
     * @return объект CommentDTO, представляющий добавленный комментарий
     */
    @Override
    public CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO comment) {
        Comment model = commentMapper.toModel(comment);
        Comment savedModel = commentRepository.save(model);
        return commentMapper.toDtoCommentDTO(savedModel);
    }


    /**
     * Удалить комментарий.
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     */
    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteById(commentId);
    }


    /**
     * Обновляет существующий комментарий.
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @param comment   объект с новым текстом комментария
     * @return обновленный комментарий в виде объекта CommentDTO
     */
    @Override
    public CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO comment) {
        Comment existingComment = commentRepository.findById(commentId).orElseThrow();
        existingComment.setText(comment.getText());
        commentRepository.save(existingComment);
        return commentMapper.toDtoCommentDTO(existingComment);
    }
}
