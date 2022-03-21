package com.amr.project.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ItemShopDto {
    private List<ShopDto> shopDtoList;
    private List<ItemDto> itemDtoList;


}
