package com.amr.project.dao;

import com.amr.project.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
