package com.amr.project.model.dto;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class ReviewDto {

    private Long id;
    private String dignity;
    private String flaw;
    private String text;
    private Date date;
    private int rating;

    //@JsonManagedReference
    private UserDto user;
    //@JsonManagedReference
    private ShopDto shop;
    //@JsonManagedReference
    private ItemDto item;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

}
