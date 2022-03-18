package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ReviewMapper;
import com.amr.project.dao.ReviewRepository;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    public Review saveReviewAsNoModerated(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setModerated(false);
        return reviewRepository.saveAndFlush(review);
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

    @Override
    public List<ReviewDto> getAllNoModeratedReview() {
        return reviewMapper.toDtoList(reviewRepository.findByIsModeratedIsFalseOrderByDateAsc(Sort.by(Sort.Direction.ASC)));
    }

    @Override
    public List<ReviewDto> getAllUserReviewById(Long id) {
        return reviewMapper.toDtoList(
                reviewRepository
                        .findByUser_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(id));
    }

    @Override
    public List<ReviewDto> getAllShopReviewById(Long id) {
        return reviewMapper.toDtoList(
                reviewRepository
                        .findByShop_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(id));
    }

    @Override
    public List<ReviewDto> getAllItemReviewById(Long id) {
        return reviewMapper.toDtoList(
                reviewRepository
                        .findByItem_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(id));
    }
}
