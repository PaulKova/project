package com.amr.project.service.impl;

import com.amr.project.dao.ShopRepository;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;


    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getShopById(Long id) {
        return shopRepository.getById(id);
    }

    @Override
    public void updateShopById(Shop shop) {
        shopRepository.saveAndFlush(shop);
    }

    @Override
    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public void saveShop(Shop shop) {
        shopRepository.saveAndFlush(shop);
    }

}
