package com.amr.project.service.abstracts;

import com.amr.project.model.dto.report.GrandSalesDto;
import com.amr.project.model.dto.ShopDto;

import org.springframework.data.domain.Pageable;

import java.util.Calendar;
import java.util.List;

public interface ShopService {

    List<ShopDto> getAllShops();

    ShopDto getShopById(Long id);

    void updateShopById(ShopDto shop);

    void deleteShopById(Long id);

    void saveShop(ShopDto shop);

    List<ShopDto> findFirst4ByOrderByRatingDesc();

    List<ShopDto> findShopsForCreate();

    List<ShopDto> findExistsShops();

    List<ShopDto> getPretendedToDelete();

    List<ShopDto> searchShopsByNameSortedByRatingDesc(String pattern, Pageable pageable);

    GrandSalesDto getSalesReport(Long itemId, Calendar startData, Calendar finishData);
}
