package com.amr.project.dao;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
