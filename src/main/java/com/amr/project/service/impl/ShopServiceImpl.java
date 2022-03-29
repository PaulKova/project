package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ShopRepository;
import com.amr.project.exception.ShopAlreadyExistingException;
import com.amr.project.exception.ShopNotFoundException;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.amr.project.converter.CycleAvoidingMappingContext;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;


    @Override
    public List<ShopDto> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return shopMapper.toDtoList(shops, new CycleAvoidingMappingContext());
    }

    @Override
    public ShopDto getShopById(Long id) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isEmpty()) {
            throw new NullPointerException("User not found");
        }
        ShopDto shopDto = shopMapper.toDto(shop.get(), new CycleAvoidingMappingContext());
        return shopDto;
    }

    @Override
    public void updateShopById(ShopDto shopDto) {
        Shop shop = shopMapper.toEntity(shopDto, new CycleAvoidingMappingContext());
        if (!shopRepository.existsById(shopDto.getId())) {
            throw new ShopNotFoundException();
        }
        shopRepository.saveAndFlush(shop);
    }

    @Override
    public void deleteShopById(Long id) {
        if (!shopRepository.existsById(id)) {
            throw new ShopNotFoundException();
        }
        shopRepository.deleteById(id);
    }

    @Override
    public void saveShop(ShopDto shopDto) {
        Shop shop = shopMapper.toEntity(shopDto, new CycleAvoidingMappingContext());
        if (shopRepository.existsByName(shop)) {
            throw new ShopAlreadyExistingException("Shop with Name " + shop.getName() + " already exists");
        }
        shopRepository.saveAndFlush(shop);
    }

    @Override
    public List<ShopDto> findFirst4ByOrderByRatingDesc() {
        List<Shop> shops = shopRepository.findFirst4ByOrderByRatingDesc();
        return shopMapper.toDtoList(shops, new CycleAvoidingMappingContext());
    }

    @Override
    public List<ShopDto> findShopsForCreate() {
        List<Shop> shopList = shopRepository.findShopByNoModerated();
        return shopMapper.toDtoList(shopList, new CycleAvoidingMappingContext());
    }


    @Override
    public List<ShopDto> findExistsShops() {
        List<Shop> shops = shopRepository.findExistsShops();
        return shopMapper.toDtoList(shops, new CycleAvoidingMappingContext());
    }

    @Override
    public List<ShopDto> getPretendedToDelete() {
        List<Shop> shops = shopRepository.findAll();
        shops.stream()
                .filter(s -> s.isPretendedToBeDeleted())
                .collect(Collectors.toList());
        return shopMapper.toDtoList(shops, new CycleAvoidingMappingContext());
    }

    @Override
    public List<ShopDto> searchShopsByNameSortedByRatingDesc(String pattern, Pageable pageable) {
        List<Shop> shops = shopRepository.findShopByNameContainingOrderByRatingDesc(pattern, pageable);
        return shopMapper.toDtoList(shops, new CycleAvoidingMappingContext());
    }
}
