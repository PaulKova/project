package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    private String name;
    private BigDecimal basePrice;
    private BigDecimal price;
    private int count;
    private double rating;
    private String description;
    private int discount;

    @JsonManagedReference
    private CategoryDto category;
    @JsonManagedReference
    private CartItemDto cartItem;
    @JsonBackReference
    private List<ImageDto> images;
    @JsonBackReference
    private List<ReviewDto> reviews;
    @JsonBackReference
    private List<FavoriteDto> favorites;
    @JsonBackReference
    private List<OrderDto> orders;
    @JsonManagedReference
    private ShopDto shop;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;
}
