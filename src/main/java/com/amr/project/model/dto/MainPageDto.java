package com.amr.project.model.dto;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MainPageDto {
    private CategoryDto categoryDto;
    private List<ShopDto> shopDtoList;
    private List<ItemDto> itemDtoList;
}
