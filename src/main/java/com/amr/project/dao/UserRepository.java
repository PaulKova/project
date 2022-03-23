package com.amr.project.dao;

import com.amr.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByEmail(String email);

    User findByActivationCode(String code);
}
