package com.amr.project.model.dto;

import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Calendar;
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
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private int age;
    private Gender gender;
    private LocalDate birthday;
    private String secret;
    private AddressDto address;
    private List<ImageDto> images;
    private List<Long> couponIds;
    private List<Long> orderIds;
    private List<ReviewDto> reviews;
    private List<FeedbackDto> feedbacks;
    private List<Long> shopIds;
    private FavoriteDto favorite;
    private List<DiscountDto> discounts;
    private Set<Long> chatIds;
}





//package com.amr.project.model.dto;
//
//import com.amr.project.model.enums.Roles;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id", scope = Long.class)
//public class UserDto {
//
//    private Long id;
//    private String email;
//    private String username;
//    private String password;
//    private boolean activate;
//    private String activationCode;
//    private boolean isUsingTwoFactorAuth;
//    private String secret;
//    private boolean isIdentification;
//
//    //@JsonManagedReference
//    private RolesDto role;
//    //@JsonManagedReference
//    private UserInfoDto userInfo;
//    //@JsonManagedReference
//    private FavoriteDto favorite;
//    //@JsonManagedReference
//    private AddressDto address;
//    //@JsonBackReference
//    private List<ImageDto> images;
//    //@JsonBackReference
//    private List<CouponDto> coupons;
//    //@JsonBackReference
//    private List<CartItemDto> cart;
//    //@JsonBackReference
//    private List<OrderDto> orders;
//    //@JsonBackReference
//    private List<ReviewDto> reviews;
//    //@JsonBackReference
//    private List<ShopDto> shops;
//    //@JsonBackReference
//    private List<DiscountDto> discounts;
//    //@JsonBackReference
//    private List<MessageDto> messages;
//    //@JsonBackReference
//    private List<ChatDto> chats;
//    //@JsonBackReference
//    private List<FeedbackDto> feedbacks;
//
//}
