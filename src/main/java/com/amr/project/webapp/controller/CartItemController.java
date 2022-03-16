package com.amr.project.webapp.controller;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @Operation(summary = "Returns list of cartitems")
    @ApiResponse(responseCode = "200", description = "Get all cartitems",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CartItemDto.class))})
    @GetMapping("/cartItems")
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        List<CartItemDto> cartItems = cartItemService.getAllCartItems();
        logger.info(GET_CARTITEM_LOG);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @Operation(summary = "get cartitem by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get one cartitem by id",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CartItemDto.class))}),
            @ApiResponse(responseCode = "404", description = "CartItem not found", content = @Content)})
    @GetMapping("/cartItems/{id}")
    public ResponseEntity<CartItemDto> getCartItem(@PathVariable(name = "id") Long id) {
        if (!cartItemService.getCartItemsById(id).isPresent()) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CartItemDto cartItemDto = cartItemService.getCartItemsById(id).get();
        logger.info(GET_CARTITEM_LOG + ID + id);
        return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
    }

    @Operation(summary = "Create a new CartItem")
    @ApiResponse(responseCode = "200",
            description = "CartItem was created",
            content = @Content)
    @PostMapping("/cartItems")
    public ResponseEntity<HttpStatus> addCartItem(@RequestBody CartItemDto cartitemDto) {
        cartItemService.saveCartItem(cartitemDto);
        logger.info(NEW_CARTITEM_LOG);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an CartItem by its ID")
    @ApiResponse(responseCode = "200",
            description = "CartItem was deleted",
            content = @Content)
    @DeleteMapping("/cartItems/{id}")
    public ResponseEntity<HttpStatus> deleteCartItem(@PathVariable(name = "id") Long id) {
        cartItemService.deleteCartItem(id);
        logger.info("Deleted Item" + ID + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update an CartItem by its ID")
            @ApiResponse(responseCode = "200",
                    description = "CartItem was updated",
                    content = @Content)
    @PutMapping("/cartItems/{id}")
    public ResponseEntity<HttpStatus> editCartItem(
            @PathVariable(name = "id") Long id,
            @RequestBody CartItemDto cartitemDto) {
        cartItemService.updateCartItem(cartitemDto);
        logger.info(CARTITEM_UP_LOG + ID + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

