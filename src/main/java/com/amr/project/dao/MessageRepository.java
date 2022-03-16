package com.amr.project.dao;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
