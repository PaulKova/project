package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ReadWriteService;

public interface UserDao extends ReadWriteDao<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
}
