package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ReviewMapper;
import com.amr.project.dao.ReviewRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Message;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ReviewService;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAllReview() {
        return reviewMapper.toDtoList(reviewRepository.findAll());
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        return reviewMapper.toDto(reviewRepository.getById(id));
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        reviewRepository.saveAndFlush(review);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        reviewRepository.saveAndFlush(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
