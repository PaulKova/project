package com.amr.project.initDB.repository;

import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Review findByUserId(Long id);
}
