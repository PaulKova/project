package com.amr.project.model.dto;

import lombok.*;


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
