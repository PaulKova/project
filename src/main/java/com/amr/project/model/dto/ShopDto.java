package com.amr.project.model.dto;

import com.amr.project.model.entity.Country;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class ShopDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private int count;
    private double rating;

//    @JsonManagedReference(value = "location")
@JsonIgnore
    private Country location;
    @JsonIgnore
//    @JsonBackReference(value = "items")
    private List<ItemDto> items;
//    @JsonBackReference(value = "reviews")
@JsonIgnore
    private List<ReviewDto> reviews;
//    @JsonManagedReference(value = "logo")
@JsonIgnore
    private ImageDto logo;
//    @JsonManagedReference(value = "user")
@JsonIgnore
    private UserDto user;
//    @JsonManagedReference(value = "cartItem")
@JsonIgnore
    private CartItemDto cartItem;
//    @JsonBackReference(value = "feedbacks")
@JsonIgnore
    private List<FeedbackDto> feedbacks;
//    @JsonBackReference(value = "discounts")
@JsonIgnore
    private List<DiscountDto> discounts;
//    @JsonBackReference(value = "favorites")
@JsonIgnore
    private List<FavoriteDto> favorites;
//    @JsonManagedReference(value = "address")
@JsonIgnore
    private AddressDto address;
//    @JsonBackReference(value = "coupons")
    @JsonIgnore
    private List<CouponDto> coupons;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

}
