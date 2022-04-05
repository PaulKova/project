package com.amr.project.dao;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllByChat(Chat chat);
}
