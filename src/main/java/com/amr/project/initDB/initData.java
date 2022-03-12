package com.amr.project.initDB;

import com.amr.project.initDB.repository.UserRepository;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class initData {

    private final UserRepository userRepository;

    public initData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initializationDB() {
        User user = User.builder()
                .email("user@mail.com")
                .username("user")
                .password("user")
                .activate(true)
                .activationCode("some_code")
                .isUsingTwoFactorAuth(false)
                .secret("secret?")
                .role(Roles.USER)
                .userInfo(null)
                .favorite(null)
                .address(null)
                .images(null)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .discounts(null)
                .messages(null)
                .chats(null)
                .feedbacks(null)
                .build();
        userRepository.save(user);
    }
}
