package com.amr.project.model.dto;

import com.amr.project.model.dto.report.SalesHistoryDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
    private UserDto user;
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
    @JsonBackReference
    private List<SalesHistoryDto> history;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;
}
