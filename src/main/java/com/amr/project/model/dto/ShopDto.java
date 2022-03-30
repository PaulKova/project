package com.amr.project.model.dto;

import com.amr.project.model.entity.Country;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
         property = "id", scope = Long.class)
public class ShopDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private int count;
    private double rating;

    ////@JsonManagedReference
    //private Country country;
    //@JsonBackReference
    private List<ItemDto> items;
    //@JsonBackReference
    private List<ReviewDto> reviews;
    ////@JsonManagedReference
    private ImageDto logo;
    ////@JsonManagedReference
    private UserDto user;
    ////@JsonManagedReference
    private CartItemDto cartItem;
    //@JsonBackReference
    private List<FeedbackDto> feedbacks;
    //@JsonBackReference
    private List<DiscountDto> discounts;
    //@JsonBackReference
    private List<FavoriteDto> favorites;
    ////@JsonManagedReference
    private AddressDto address;
    //@JsonBackReference
    private List<CouponDto> coupons;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    public ShopDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
