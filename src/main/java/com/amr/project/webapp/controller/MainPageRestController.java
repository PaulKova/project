package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageRestController {
    private final ItemService itemService;
    private final ShopService shopService;

    @GetMapping("/search/{criteria}")
    //TODO Разобраться какой список будет возвращаться на фронт?
    public ResponseEntity<List<ShopDto>> getFirst5ItemsAndListsByPattern(@PathVariable String criteria) {
        return null;
    }
}
