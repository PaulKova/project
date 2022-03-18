package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private static final String DELETE = "Deleted Item id: {}";
    private static final String NEW_SHOP_LOG = "New shop was created id:{}";
    private static final String ITEMS_TO_DELETE = "{} items has been marked to delete";
    private static final String SHOPS_TO_DELETE = "{} shops has been marked to delete";
    private static final String SHOPS_TO_CREATE = "{} shops has been marked to create";

    private final ItemService itemService;
    private final ShopService shopService;
    private final UserService userService;




    @Operation(summary = "get items marked to delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ItemDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/admin/items/pretended/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<List<ItemDto>> getItemsPretendedToDelete() {
        List<ItemDto> items = itemService.getPretendedToDelete();
        logger.info(ITEMS_TO_DELETE, items.size());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }





    @Operation(summary = "get shops marked to delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/admin/shops/pretended/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<List<ShopDto>> getShopsPretendedToDelete() {
        List<ShopDto> shops = shopService.getPretendedToDelete();
        logger.info(SHOPS_TO_DELETE, shops.size());
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }






    @Operation(summary = "Getting a list of shops to moderate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/admin/shops/pretended/create/request")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<List<ShopDto>> getShopsToCreate() {
        List<ShopDto> shopDto = shopService.findShopsForCreate();
        logger.info(SHOPS_TO_CREATE, shopDto.size());
        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }






    @Operation(summary = "Delete an Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Item was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Item not found",
                    content = @Content)
    })
    @DeleteMapping("/admin/delete/items/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteItem(@PathVariable(name = "id") Long id) {
        itemService.deleteItem(id);
        logger.info(DELETE, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }





    @Operation(summary = "Create request for a new Shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Request is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShopDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop already exists",
                    content = @Content)
    })
    @PostMapping("/admin/create/shop")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ShopDto> requestToCreateShop(@RequestBody ShopDto shopDto) {
        shopDto.setModerateAccept(true);// admins accept create a shop
        shopDto.setModerated(false);
        shopService.saveShop(shopDto);
        logger.info(NEW_SHOP_LOG, shopDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }





    @Operation(summary = "Delete an Shop by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Shop was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop not found",
                    content = @Content)
    })
    @DeleteMapping("/admin/delete/shop{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Long> deleteShop(@PathVariable(name = "id") Long id) {
        shopService.deleteShopById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



    @Operation(summary = "Delete an User by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("/admin/delete/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
