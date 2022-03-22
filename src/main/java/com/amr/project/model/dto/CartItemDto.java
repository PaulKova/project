package com.amr.project.model.dto;

import com.amr.project.model.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Embedded;
import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private int quantity;
    private User user;


    @JsonManagedReference
    private ShopDto shop;
    @JsonBackReference
    private List<ItemDto> itemList;
}