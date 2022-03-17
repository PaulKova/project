package com.amr.project.webapp.controller;



import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private static final String ID = "shopId";
    private static final String NEW_ITEM_LOG = "New shop was created id:{}";
    private static final String ITEM_UPDATED_LOG = "Shop:{} was updated";
    private static final String GET_ITEM_LOG = "Shop:{} is get";

    private final ShopService shopService;
    private final ShopMapper shopMapper;


    @Operation(summary = "get all shops")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/shops")
    public ResponseEntity<List<ShopDto>> getAllShops() {
        List<ShopDto> shops = shopService.getAllShops();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }


    @Operation(summary = "get shop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/shops/{id}")
    public ResponseEntity<Optional<ShopDto>> getItem(@PathVariable(name = "id") Long id) {
        ShopDto shopDto = shopService.getShopById(id);
        Optional<ShopDto> optionalShopDto = Optional.of(shopDto);
        return new ResponseEntity<>(optionalShopDto, HttpStatus.OK);
    }


    @Operation(summary = "get fist fow shops by rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/shops/top")
    public ResponseEntity<List<ShopDto>> getFistForShopsByRating() {
        List<ShopDto> shopDtos = shopService.findFirstByOrdOrderByRatingAsc();
        return new ResponseEntity<>(shopDtos, HttpStatus.OK);
    }



    @Operation(summary = "Create a new Shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Shop is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShopDto.class)))
    })
    @PostMapping("/shops")
    public ResponseEntity<ShopDto> addItem(@RequestBody ShopDto shopDto) {
        shopService.saveShop(shopDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    @PutMapping("/items/{id}")
    public ResponseEntity<HttpStatus> editItem(
            @PathVariable(name = "id") Long id,
            @RequestBody ShopDto shopDto) {
        Shop shop =  shopMapper.toEntity(shopDto);
        Optional<Shop> optionalShop = Optional.of(shop);
        if (optionalShop.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shopService.updateShopById(shopDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @Operation(summary = "Delete an Shop by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Shop was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop not found",
                    content = @Content)
    })
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Long> deleteShop(@PathVariable(name = "id") Long id) {
        shopService.deleteShopById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
