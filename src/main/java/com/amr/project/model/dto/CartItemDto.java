package com.amr.project.model.dto;

import com.amr.project.model.entity.User;
import lombok.*;

import javax.persistence.Embedded;
import java.util.List;
import java.util.Set;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private int quantity;
    private User user;



    private ShopDto shop;
    private List<ItemDto> itemList;
}
