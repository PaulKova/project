package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ItemRepository;
import com.amr.project.dao.ShopRepository;
import com.amr.project.model.dto.GrandSalesDto;
import com.amr.project.model.dto.SalesDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.enums.Status;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.amr.project.converter.CycleAvoidingMappingContext;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
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
    public void updateShopById(ShopDto shop) {
        Shop shop1 = shopMapper.toEntity(shop, new CycleAvoidingMappingContext());
        shopRepository.saveAndFlush(shop1);
    }

    @Override
    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public void saveShop(ShopDto shop) {
        Shop shop1 = shopMapper.toEntity(shop, new CycleAvoidingMappingContext());
        shopRepository.saveAndFlush(shop1);
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

    @Override
    public GrandSalesDto getSalesReport(Long shopId, String itemName, Calendar startData, Calendar finishData) {
        BigDecimal grandTotalSum = BigDecimal.valueOf(0);
        BigDecimal grandTotalProfit = BigDecimal.valueOf(0);
        GrandSalesDto salesReport = new GrandSalesDto();


        List<Item> listItemsRequest = itemRepository.findByShop_IdIsAndNameLikeIgnoreCaseAndOrder_StatusIsOrOrder_StatusIsOrOrder_StatusIsAndOrder_OrderDateIsBetweenOrderByOrder_OrderDateDesc(shopId, itemName, Status.PAID, Status.SENT, Status.DELIVERED, startData, finishData);

        List<SalesDto> salesDtoList = new ArrayList<>(listItemsRequest.size());

        for (Item item : listItemsRequest) {
            SalesDto salesDto = new SalesDto();
            salesDto.setItem(item.getName());
            salesDto.setOrderDate(item.getOrder().getOrderDate());
            salesDto.setCount(item.getCount());
            salesDto.setTotalSum(item.getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            salesDto.setBasePrice(item.getBasePrice());
            salesDto.setProfit(item.getPrice().subtract(item.getBasePrice()));
            salesDto.setTotalProfit(salesDto.getProfit().multiply(BigDecimal.valueOf(item.getCount())));

            salesDtoList.add(salesDto);
            grandTotalSum = grandTotalSum.add(salesDto.getTotalSum());
            grandTotalProfit = grandTotalProfit.add(salesDto.getTotalProfit());
        }

        salesReport.setSales(salesDtoList);
        salesReport.setGrandTotalSum(grandTotalSum);
        salesReport.setGrandTotalProfit(grandTotalProfit);

        return salesReport;
    }
}
