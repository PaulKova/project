package com.amr.project.model.dto;

import com.amr.project.model.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private int count;

    private double rating;
    private Country location;
    private List<ItemDto> items;
    private List<ReviewDto> reviews;
    private ImageDto logo;
    private UserDto user;
    private CartItemDto cartItem;
    private List<FeedbackDto> feedbacks;
    private List<DiscountDto> discounts;
    private List<FavoriteDto> favorites;
    private AddressDto address;
    private List<CouponDto> coupons;

}
