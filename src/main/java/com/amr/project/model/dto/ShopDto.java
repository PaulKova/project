package com.amr.project.model.dto;

import com.amr.project.model.entity.Country;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private int count;
    private double rating;

    @JsonManagedReference
    private Country location;
    @JsonBackReference
    private List<ItemDto> items;
    @JsonBackReference
    private List<ReviewDto> reviews;
    @JsonManagedReference
    private ImageDto logo;
    @JsonManagedReference
    private UserDto user;
    @JsonManagedReference
    private CartItemDto cartItem;
    @JsonBackReference
    private List<FeedbackDto> feedbacks;
    @JsonBackReference
    private List<DiscountDto> discounts;
    @JsonBackReference
    private List<FavoriteDto> favorites;
    @JsonManagedReference
    private AddressDto address;
    @JsonBackReference
    private List<CouponDto> coupons;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

}
