package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    private Long id;

    private List<ShopDto> shops;
    private List<ItemDto> items;
    private UserDto user;
}