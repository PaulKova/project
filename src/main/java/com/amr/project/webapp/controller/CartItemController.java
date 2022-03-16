package com.amr.project.webapp.controller;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.service.abstracts.CartItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private static final String ID = "CartItemId";
    private static final String NEW_CARTITEM_LOG = "New CartItem was created id:{}";
    private static final String CARTITEM_UP_LOG = "CartItem:{} was updated";
    private static final String GET_CARTITEM_LOG = "CartItem:{} is get";

    private final CartItemService cartItemService;

    @GetMapping("/cartItems")
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        List<CartItemDto> cartItems = cartItemService.getAllCartItems();
        logger.info(GET_CARTITEM_LOG);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/cartItems/{id}")
    public ResponseEntity<CartItemDto> getCartItem(@PathVariable(name = "id") Long id) {
        CartItemDto cartItemDto = cartItemService.getCartItemsById(id);
        logger.info(GET_CARTITEM_LOG + ID + id);
        return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
    }

    @PostMapping("/cartItems")
    public ResponseEntity<ItemDto> addCartItem(@RequestBody CartItemDto cartitemDto) {
        cartItemService.saveCartItem(cartitemDto);
        logger.info(NEW_CARTITEM_LOG);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/cartItems/{id}")
    public ResponseEntity<Long> deleteCartItem(@PathVariable(name = "id") Long id) {
        cartItemService.deleteCartItem(id);
        logger.info("Deleted Item" + ID + id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/cartItems/{id}")
    public ResponseEntity<ItemDto> editCartItem(
            @PathVariable(name = "id") Long id,
            @RequestBody CartItemDto cartitemDto) {
        cartItemService.updateCartItem(cartitemDto);
        logger.info(CARTITEM_UP_LOG + ID + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
