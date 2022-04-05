package com.amr.project.dao;

import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findBySenderAndRecipient(User sender, User recipient);
    List<Chat> getAllBySender(User sender);
}
