package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    private ShopDto shop;


}
