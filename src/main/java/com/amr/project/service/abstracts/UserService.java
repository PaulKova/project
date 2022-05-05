package com.amr.project.service.abstracts;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Status;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    void updateUser(UserDto user);

    void deleteUserById(Long id);

    void saveUser(UserDto user);

    User getUserByEmail(String email);

    String activateUser(String code);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    List<ImageDto> getUserWithPicture(User user, byte[] bytes);
    List<OrderDto> getUserOrders(Long id, Status status);
}
