package com.amr.project.restcontroller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/shops", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ShopController {

//    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
//
//    private static final String ID = "shopId";
//    private static final String NEW_SHOP_LOG = "New shop was created id:{}";
//    private static final String SHOP_UPDATED_LOG = "Shop:{} was updated";
//
//    private final ShopService shopService;
//
//    @Operation(summary = "create new Shop")
//    @ApiResponse(responseCode = "201", description = "Shop is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))})
//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    public ResponseEntity<ShopDto> createShop(@Valid @RequestBody ShopDto shopDto) {
//        shopService.createShop(shopDto);
//        logger.info(NEW_SHOP_LOG, shopDto.toString());
//        return ResponseEntity.status(HttpStatus.CREATED).body(shopDto);
//    }
//
//    @Operation(summary = "get shop by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
//            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
//    @GetMapping("/{shopId}")
//    public ResponseEntity<ShopDto> loadShop(@PathVariable Long shopId) {
//        final Optional<ShopDto> shop = shopService.loadShop(shopId);
//        if (shop.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(shop.get());
//    }
//
//    @Operation(summary = "Returns list of shops")
//    @ApiResponse(responseCode = "200", description = "Get all shops", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))})
//    @GetMapping
//    public ResponseEntity<List<ShopDto>> getShops() {
//        final List<ShopDto> dtoList = shopService.getShops();
//        return ResponseEntity.ok(dtoList);
//    }
//
//    @Operation(summary = "Update shop by it's id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Shop was updated", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
//            @ApiResponse(responseCode = "404", description = "Shop was not found", content = @Content)
//    })
//    @PutMapping(path = "/{shopId}", consumes = APPLICATION_JSON_VALUE)
//    public ResponseEntity<ShopDto> updateShop(@PathVariable long shopId,
//                                              @Valid @RequestBody ShopDto shopDto) {
//        final Optional<ShopDto> updatedShop = shopService.updateShop(shopId, shopDto);
//        if (updatedShop.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        logger.info(SHOP_UPDATED_LOG, updatedShop.toString());
//        return ResponseEntity.ok(updatedShop.get());
//    }
}
