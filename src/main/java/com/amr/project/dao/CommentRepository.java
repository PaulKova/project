package com.amr.project.dao;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.model.dto.CommentDto;
import com.amr.project.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
