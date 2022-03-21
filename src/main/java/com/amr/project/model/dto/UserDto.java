package com.amr.project.model.dto;

import com.amr.project.model.enums.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean activate;
    private String activationCode;
    private boolean isUsingTwoFactorAuth;
    private String secret;
    private RolesDto role;
    private UserInfoDto userInfo;
    private FavoriteDto favorite;
    private AddressDto address;
    private List<ImageDto> images;

    private List<CouponDto> coupons;
    private List<CartItemDto> cart;
    private List<OrderDto> orders;
    private List<ReviewDto> reviews;
    private List<ShopDto> shops;
    private List<DiscountDto> discounts;
    private List<MessageDto> messages;
    private List<ChatDto> chats;
    private List<FeedbackDto> feedbacks;

}
