package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.CommentMapper;
import com.amr.project.converter.mappers.FeedbackMapper;
import com.amr.project.dao.CommentRepository;
import com.amr.project.model.dto.CommentDto;
import com.amr.project.model.entity.Comment;
import com.amr.project.model.entity.Feedback;
import com.amr.project.service.abstracts.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.toDtoList(comments, new CycleAvoidingMappingContext());
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
