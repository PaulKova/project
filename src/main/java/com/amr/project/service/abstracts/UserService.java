package com.amr.project.service.abstracts;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    void updateUser(UserDto user);

    void deleteUserById(Long id);

    void saveUser(UserDto user);

    User getUserByEmail(String email);
}
