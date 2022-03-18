package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ItemMapper;
import com.amr.project.dao.ItemRepository;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.toDtoList(items);
    }

    @Override
    public Optional<ItemDto> getItemById(Long id) {
        Item item = itemRepository.getById(id);
        ItemDto itemDto = itemMapper.toDto(item);
        return Optional.of(itemDto);
    }

    @Override
    public void saveItem(ItemDto itemDto) {
       Item item = itemMapper.toEntity(itemDto);
       itemRepository.saveAndFlush(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemDto> findFirst4ByOrderByRatingAsc() {
        List<Item> items = itemRepository.findFirst4ByOrderByRatingDesc();
        return itemMapper.toDtoList(items);
    }

    @Override
    public List<ItemDto> getPretendedToDelete() {
        List<Item> items = itemRepository.findAll();
        List<Item> result = items.stream()
                                .filter(Item::isPretendedToBeDeleted)
                                .collect(Collectors.toList());
        return itemMapper.toDtoList(result);
    }
}
