package com.amr.project.service.impl;

import com.amr.project.converter.mappers.UserMapper;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getById(id);
        UserDto userDto = userMapper.toDto(user);
        return userDto;
    }

    @Override
    public void updateUser(UserDto user) {
        User user1 = userMapper.toEntity(user);
        userRepository.saveAndFlush(user1);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(UserDto user) {
        User user1 = userMapper.toEntity(user);
        userRepository.saveAndFlush(user1);
    }

    @Override
    public User getUserByEmail (String email) {
        return userRepository.getUserByEmail(email);
    }
}
