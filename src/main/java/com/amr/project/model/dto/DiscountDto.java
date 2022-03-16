package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountDto {

    private Long id;
    private Integer minOrder;
    private Integer percentage;
    private Integer fixedDiscount;

    private ShopDto shop;


}
