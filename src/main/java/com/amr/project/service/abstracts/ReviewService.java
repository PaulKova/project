package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<ReviewDto> getAllReview();

    ReviewDto getReviewById(Long id);

    void saveReview(ReviewDto reviewDto);

    Review saveReviewAsNoModerated(ReviewDto reviewDto);

    void updateReview(ReviewDto reviewDto);

    void deleteReview(Long id);

    List<ReviewDto> getAllNoModeratedReview();

    List<ReviewDto> getAllUserReviewById(Long id);

    List<ReviewDto> getAllShopReviewById(Long id);

    List<ReviewDto> getAllItemReviewById(Long id);

}
