package com.amr.project.initDB.repository;

import com.amr.project.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
}
