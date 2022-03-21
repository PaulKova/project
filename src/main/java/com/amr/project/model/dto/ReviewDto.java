package com.amr.project.model.dto;


import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
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

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

}
