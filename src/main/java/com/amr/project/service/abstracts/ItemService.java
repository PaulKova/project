package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItems();

    ItemDto getItemById(Long id);

    void saveItem(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItem(Long id);

    List<ItemDto> findFirst4ByOrderByRatingAsc();

    List<ItemDto> getPretendedToDelete();
}
