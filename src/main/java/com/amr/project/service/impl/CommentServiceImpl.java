package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.model.entity.Feedback;
import com.amr.project.service.abstracts.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getAllComments() {
        List<CommentDto> comments = commentRepository.findAll();
        return comments;
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.getById(id);
        return commentMapper.toDto(comment, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        Comment comment = commentRepository.toEntity(commentDto, new CycleAvoidingMappingContext());
        commentRepository.saveAndFlush(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);

    }
}
