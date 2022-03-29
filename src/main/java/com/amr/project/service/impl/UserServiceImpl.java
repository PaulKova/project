package com.amr.project.service.impl;

import com.amr.project.converter.mappers.UserMapper;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.Mail;
import com.amr.project.model.dto.RolesDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.email.MailSender;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.util.EmailUserAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;
import java.util.UUID;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private MailSender mailSender;
    private EmailUserAssistant emailUserAssistant;


    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users, new CycleAvoidingMappingContext());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NullPointerException("User not found");
        }
        UserDto userDto = userMapper.toDto(user.get(), new CycleAvoidingMappingContext());
        return userDto;
    }

    @Override
    public void updateUser(UserDto user) {
        User user1 = userMapper.toEntity(user, new CycleAvoidingMappingContext());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mailSender.send(emailUserAssistant.trackedEmailUserUpdate(user1));
        userRepository.saveAndFlush(user1);
    }

    @Override
    public void deleteUserById(Long id) {
        mailSender.send(emailUserAssistant.trackedEmailUserDelete(userRepository.getById(id)));
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(UserDto user) {
        User user1 = userMapper.toEntity(user, new CycleAvoidingMappingContext());
        user.setActivate(false);
        user.setRole(RolesDto.USER);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getEmail() != null) {
            String message = "<a href=http://localhost:8888/registrationConfirm?" +
                    "&code=" + user.getActivationCode() +
                    "> + Verify email and activate account</a>";
            Mail verificationMail = new Mail();
            verificationMail.setTo(user.getEmail());
            verificationMail.setText(message);
            mailSender.send(verificationMail);
        }
        userRepository.saveAndFlush(user1);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public String activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return "user nor found";
        }
        if (user.getActivationCode().equals(code)) {
            user.setActivate(true);
        }
        user.setActivationCode(null);
        return "user activated";
    }
}
