package com.amr.project.model.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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

    @JsonManagedReference
    private UserDto user;
    @JsonManagedReference
    private ShopDto shop;
    @JsonManagedReference
    private ItemDto item;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

}
