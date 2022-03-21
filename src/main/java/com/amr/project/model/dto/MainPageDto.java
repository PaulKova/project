package com.amr.project.model.dto;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class MainPageDto {
    private List<CategoryDto> categoryDto;
    private List<ShopDto> shopDtoList;
    private List<ItemDto> itemDtoList;
}
