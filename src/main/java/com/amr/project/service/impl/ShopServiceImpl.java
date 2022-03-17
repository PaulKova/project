package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ShopRepository;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        Shop shop = shopRepository.getById(id);
        return shopMapper.toDto(shop);
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
    public List<ShopDto> findFirstByOrdOrderByRatingAsc() {
        List<Shop> shops = shopRepository.findFirstByOrdOrderByRatingAsc();
        return shopMapper.toDtoList(shops);
    }

    @Override
    public List<ShopDto> findShopByNoModerated() {
        List<Shop> shopList = shopRepository.findShopByNoModerated();
        return shopMapper.toDtoList(shopList);
    }

    @Override
    public void saveShopAsNoModerated(ShopDto shop) {
        shop.setModerated(false);
        shopRepository.saveAndFlush(shopMapper.toEntity(shop));
    }


}
