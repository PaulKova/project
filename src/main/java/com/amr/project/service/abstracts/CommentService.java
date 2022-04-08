package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CommentDto;
import com.amr.project.model.dto.UserDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments();

    CommentDto getCommentById(Long id);

    void saveComment(CommentDto commentDto);

    void deleteCommentById(Long id);

    void updateComment(CommentDto commentDto);
}
