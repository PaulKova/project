package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getAllShops();

    Shop getShopById(Long id);

    void updateShopById(Shop shop);

    void deleteShopById(Long id);

    void saveShop(Shop shop);

}
