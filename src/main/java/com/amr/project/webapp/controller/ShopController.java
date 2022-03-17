package com.amr.project.webapp.controller;



import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private static final String ID = "shopId";
    private static final String NEW_ITEM_LOG = "New shop was created id:{}";
    private static final String ITEM_UPDATED_LOG = "Shop:{} was updated";
    private static final String GET_ITEM_LOG = "Shop:{} is get";

    private final ShopService shopService;


    //TODO добавить Optional во все методы, где необходимо

    @Operation(summary = "Getting all shops")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of shops created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any shop found", content = @Content)})
    @GetMapping("")
    public ResponseEntity<List<ShopDto>> getAllShops() {
        List<ShopDto> shops = shopService.getAllShops();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }


    @Operation(summary = "Getting shop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the shop", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Shop not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getItem(@PathVariable(name = "id") Long id) {
        ShopDto shopDto = shopService.getShopById(id);
        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }


    //TODO реализовать метод findFirstByOrdOrderByRatingAsc() в ShopRepository
    @Operation(summary = "Getting fist four shops by rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of shops", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any shop found", content = @Content)})
    @GetMapping("/top")
    public ResponseEntity<List<ShopDto>> getFistForShopsByRating() {
        List<ShopDto> shopDtos = shopService.findFirstByOrdOrderByRatingAsc();
        return new ResponseEntity<>(shopDtos, HttpStatus.OK);
    }


    //GetMapping на получение списка магазинов на модерацию ("User'ами созданы заявки на создание магазинов")
    @Operation(summary = "Getting a list of shops to moderate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/request")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<List<ShopDto>> getShopsToCreate() {
        List<ShopDto> shopDtos = shopService.findShopByNoModerated();
        return new ResponseEntity<>(shopDtos, HttpStatus.OK);
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
    @PostMapping("/request")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ShopDto> requestToCreateShop(@RequestBody ShopDto shopDto) {
        //устанавливаем поле "isModerated" в "false" (магазин создан в БД,
        //но требует модерации для "отображения" на страницах приложения)
        shopService.saveShopAsNoModerated(shopDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @Operation(summary = "Give the Shop permission to work")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Permission was changed",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShopDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop not found",
                    content = @Content)
    })
    @PutMapping("/permission/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ShopDto> changeShopPermission(@RequestBody ShopDto shopDto) {
        //TODO: где используется ID?
        shopService.updateShopById(shopDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }




    @Operation(summary = "Update an Shop by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Shop was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShopDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> editShop(
            @PathVariable(name = "id") Long id,
            @RequestBody ShopDto shopDto) {
        //TODO: где используется ID?
        shopService.updateShopById(shopDto);
        return new ResponseEntity<>(HttpStatus.OK);
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Long> deleteShop(@PathVariable(name = "id") Long id) {
        shopService.deleteShopById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
