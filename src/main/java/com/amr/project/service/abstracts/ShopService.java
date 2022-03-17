package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ShopDto;

import java.util.List;

public interface ShopService {

    List<ShopDto> getAllShops();

    ShopDto getShopById(Long id);

    void updateShopById(ShopDto shop);

    void deleteShopById(Long id);

    void saveShop(ShopDto shop);

    List<ShopDto> findFirst4ByOrdOrderByRatingAsc();

    List<ShopDto> findShopsForCreate();

    List<ShopDto> findExistsShops();

}
