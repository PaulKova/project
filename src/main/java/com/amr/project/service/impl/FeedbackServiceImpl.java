package com.amr.project.service.impl;

import com.amr.project.converter.mappers.FeedbackMapper;
import com.amr.project.dao.FeedbackRepository;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Feedback;
import com.amr.project.service.abstracts.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mapstruct.example.mapper.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return null;
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.getById(id);
        return feedbackMapper.toDto(feedback, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = feedbackMapper.toEntity(feedbackDto, new CycleAvoidingMappingContext());
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void updateFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = feedbackMapper.toEntity(feedbackDto, new CycleAvoidingMappingContext());
        feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
