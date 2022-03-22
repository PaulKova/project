package com.amr.project.service.impl;

import com.amr.project.dao.UserRepository;
import com.amr.project.model.Mail;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.MailSender;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailSender mailSender;


    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        user.setActivate(false);
        user.setRole(Roles.USER);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getEmail() != null) {
            String message = String.format("Your activation code %s", user.getActivationCode());
            Mail verificationMail = new Mail();
            verificationMail.setTo(user.getEmail());
            verificationMail.setText(message);
            mailSender.send(verificationMail);
        }
        userRepository.save(user);
    }

    public User getUserByEmail (String email) {
        return userRepository.getUserByEmail(email);
    }

    public boolean activateUser (String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        return true;
    }
}
