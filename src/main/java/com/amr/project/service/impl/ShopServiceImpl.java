package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ShopRepository;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;



    @Override
    public List<ShopDto> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return shopMapper.toDtoList(shops);
    }

    @Override
    public ShopDto getShopById(Long id) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isEmpty()) {
            throw new NullPointerException("User not found");
        }
        ShopDto shopDto = shopMapper.toDto(shop.get());
        return shopDto;
    }

    @Override
    public void updateShopById(ShopDto shop) {
        Shop shop1 = shopMapper.toEntity(shop);
        shopRepository.saveAndFlush(shop1);
    }

    @Override
    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public void saveShop(ShopDto shop) {
        Shop shop1 = shopMapper.toEntity(shop);
        shopRepository.saveAndFlush(shop1);
    }

    @Override
    public List<ShopDto> findFirst4ByOrderByRatingDesc() {
        List<Shop> shops = shopRepository.findFirst4ByOrderByRatingDesc();
        return shopMapper.toDtoList(shops);
    }

    @Override
    public List<ShopDto> findShopsForCreate() {
        List<Shop> shopList = shopRepository.findShopByNoModerated();
        return shopMapper.toDtoList(shopList);
    }


    @Override
    public List<ShopDto> findExistsShops() {
        List<Shop> shops = shopRepository.findExistsShops();
        return shopMapper.toDtoList(shops);
    }

    @Override
    public List<ShopDto> getPretendedToDelete() {
        List<Shop> shops = shopRepository.findAll();
        shops.stream()
                .filter(s -> s.isPretendedToBeDeleted())
                .collect(Collectors.toList());
        return shopMapper.toDtoList(shops);
    }

    @Override
    public List<ShopDto> searchShopsByNameSortedByRatingDesc(String pattern) {
        List<Shop> shops = shopRepository.findShopByNameContainingOrderByRatingDesc(pattern);
        return shopMapper.toDtoList(shops);
    }
}
