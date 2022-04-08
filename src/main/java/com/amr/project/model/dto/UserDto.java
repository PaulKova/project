package com.amr.project.model.dto;

import com.amr.project.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class UserDto {

    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean activate;
    private String activationCode;
    private boolean isUsingTwoFactorAuth;
    private String secret;
    private int passport;
    private int inn;
    private int driverId;

    //@JsonManagedReference
    private RolesDto role;
    //@JsonManagedReference
    private UserInfoDto userInfo;
    //@JsonManagedReference
    private FavoriteDto favorite;
    //@JsonManagedReference
    private AddressDto address;
    //@JsonBackReference
    private List<ImageDto> images;
    //@JsonBackReference
    private List<CouponDto> coupons;
    //@JsonBackReference
    private List<CartItemDto> cart;
    //@JsonBackReference
    private List<OrderDto> orders;
    //@JsonBackReference
    private List<ReviewDto> reviews;
    //@JsonBackReference
    private List<ShopDto> shops;
    //@JsonBackReference
    private List<DiscountDto> discounts;
    //@JsonBackReference
    private List<MessageDto> messages;
    //@JsonBackReference
    private List<ChatDto> chats;
    //@JsonBackReference
    private List<FeedbackDto> feedbacks;

}
