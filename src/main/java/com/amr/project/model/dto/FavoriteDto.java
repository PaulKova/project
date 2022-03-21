package com.amr.project.model.dto;

import lombok.*;

import java.util.List;
import java.util.Set;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    private Long id;

    private List<ShopDto> shops;
    private List<ItemDto> items;
    private UserDto user;
}