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
    private String description;
    private String email;
    private String phone;
    private double rating;
    private List<ReviewDto> reviews;
    private ImageDto logo;
    private List<DiscountDto> discounts;
    private CityDto location;
    private Long userId;
    private List<Long> couponIds;
}
