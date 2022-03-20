package com.amr.project.service.abstracts;
import com.amr.project.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUserById(Long id);

    void saveUser(User user);

    User getUserByEmail(String email);

    boolean activateUser(String code);
}
