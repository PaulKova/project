package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<ItemDto> getAllItems();

    ItemDto getItemById(Long id);

    void saveItem(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItem(Long id);

    List<ItemDto> findFirst4ByOrderByRatingDesc();

    List<ItemDto> getPretendedToDelete();

    List<ItemDto> searchItemsByNameSortedByRatingDesc(String pattern, Pageable pageable);
}
