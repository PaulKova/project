package com.amr.project.model.dto;

import com.amr.project.model.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class CartItemDto {
    private Long id;
    private int quantity;
    private User user;
//TODO удалить этот класс

    //@JsonManagedReference
    private ShopDto shop;
    //@JsonBackReference
    private List<ItemDto> itemsInCart;
}
