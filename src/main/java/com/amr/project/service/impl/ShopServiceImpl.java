package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ShopRepository;
import com.amr.project.dao.report.SalesHistoryRepository;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.report.GrandSalesDto;
import com.amr.project.model.dto.report.SalesDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.report.SalesHistory;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailShopAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final SalesHistoryRepository salesHistoryRepository;
    private final ShopMapper shopMapper;
    private final EmailShopAssistant emailShopAssistant;
    private final MailSender mailSender;


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
        mailSender.send(emailShopAssistant.trackEmailShopUpdate(shop1));
        shopRepository.saveAndFlush(shop1);
    }

    @Override
    public void deleteShopById(Long id) {
        mailSender.send(emailShopAssistant.trackEmailShopDelete(shopRepository.getById(id)));
        shopRepository.deleteById(id);
    }

    @Override
    public void saveShop(ShopDto shop) {
        Shop shop1 = shopMapper.toEntity(shop, new CycleAvoidingMappingContext());
        mailSender.send(emailShopAssistant.trackEmailShopCreate(shop1));
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
    public GrandSalesDto getSalesReport(Long itemId, Calendar startData, Calendar finishData) {
        BigDecimal grandTotalSum = BigDecimal.valueOf(0);
        BigDecimal grandTotalProfit = BigDecimal.valueOf(0);
        GrandSalesDto salesReport = new GrandSalesDto();


        List<SalesHistory> listRequest = salesHistoryRepository.findByItem_IdIsAndOrderDateBetweenOrderByOrderDateAsc(itemId, startData, finishData);

        List<SalesDto> salesDtoList = new ArrayList<>(listRequest.size());

        for (SalesHistory request : listRequest) {
            SalesDto salesDto = new SalesDto();
            salesDto.setItem(request.getItem().getName());
            salesDto.setOrderDate(request.getOrderDate());
            salesDto.setCount(request.getCount());
            salesDto.setTotalSum(request.getPrice().multiply(BigDecimal.valueOf(request.getCount())));
            salesDto.setBasePrice(request.getBasePrice());
            salesDto.setProfit(request.getPrice().subtract(request.getBasePrice()));
            salesDto.setTotalProfit(salesDto.getProfit().multiply(BigDecimal.valueOf(request.getCount())));

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
