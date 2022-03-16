package com.amr.project.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private String dignity;
    private String flaw;
    private String text;
    private Date date;
    private int rating;
    private UserDto user;
    private ShopDto shop;
    private ItemDto item;


}
