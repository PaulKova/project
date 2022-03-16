package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<ReviewDto> getAllReview();

    ReviewDto getReviewById(Long id);

    void saveReview(ReviewDto reviewDto);

    void updateReview(ReviewDto reviewDto);

    void deleteReview(Long id);
}
