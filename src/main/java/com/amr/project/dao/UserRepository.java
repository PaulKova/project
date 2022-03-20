package com.amr.project.dao;

import com.amr.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByEmail(String email);

    User findByActivationCode(String code);
}
