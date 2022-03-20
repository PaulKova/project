package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemShopDto;
import com.amr.project.model.dto.MainPageDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageRestController {

    private final ItemService itemService;
    private final ShopService shopService;


    @GetMapping("/search")
    public ResponseEntity<ItemShopDto> getShopAndItems(@RequestParam("shopOrItemName") String shopOrItemName, Pageable pageable) {
        if (shopOrItemName == null) {
            return new ResponseEntity<>(new ItemShopDto(shopService.findAll(), itemService.findAll()));
        }
        List<ShopDto> shopDtos =shopService.searchShopsByNameSortedByRatingDesc(shopOrItemName, pageable);
        List<ItemDto> itemDtos = itemService.searchItemsByNameSortedByRatingDesc(shopOrItemName, pageable);
        ItemShopDto itemShopDto = new ItemShopDto(shopDtos, itemDtos);
        return new ResponseEntity<>(itemShopDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MainPageDto>() {

        return new ResponseEntity<>(MainPageDto, HttpStatus.OK);
    }

}
