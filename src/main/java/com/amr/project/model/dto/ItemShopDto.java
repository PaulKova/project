package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemShopDto {
    private List<ShopDto> shopDtoList;
    private List<ItemDto> itemDtoList;


}
