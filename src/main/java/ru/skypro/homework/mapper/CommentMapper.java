package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "ad.id")
    @Mapping(source = "text", target = "text")
    Comment toModel(CommentDTO dto);

    @Mapping(source = "text", target = "text")
    Comment toModel(CreateOrUpdateCommentDTO dto);

    //_____ toDto___
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "ad.id", target = "pk")
    @Mapping(source = "text", target = "text")
    CommentDTO toDtoCommentDTO(Comment commentModel);

    @Mapping(source = "text", target = "text")
    CreateOrUpdateCommentDTO toDtoCreateOrUpdateCommentDTO(Comment commentModel);
    @Mapping(target = "count", source = "size")
    @Mapping(target = "results", source = "list")
    CommentsDTO toDtoCommentsDTO(Integer size, List<Comment> list);
}
