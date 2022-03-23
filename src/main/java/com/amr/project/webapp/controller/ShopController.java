package com.amr.project.webapp.controller;



import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
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
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private static final String ID = "shopId";
    private static final String SHOP_UPDATED_LOG = "Shop:{} was updated";
    private static final String GET_SHOP_LOG = "Shop:{} is get";
    private static final String GET_SHOPS_LOG = "{} shops has been loaded";
    private static final String GET_TOP_SHOPS_LOG = "{} shops has been loaded";
    private static final String GET_PRETENDED_TO_DELETE_SHOPS_LOG = "{} shops has been mark as pretended to delete";
    private static final String GET_PRETENDED_TO_CREATE_SHOPS_LOG = "{} shops has been mark as pretended to create";
    private static final String SHOPS_TO_DELETE = "{} shops has been marked to delete";
    private static final String SHOPS_TO_CREATE = "{} shops has been marked to create";
    private static final String NEW_SHOP_LOG = "New shop was created id:{}";
    private static final String DELETE_SHOP = "Deleted Shop id: {}";

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @Operation(summary = "Getting all shops")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of shops created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any shop found", content = @Content)})
    @GetMapping("/shops")
    public ResponseEntity<List<ShopDto>> getAllShops() {
        List<ShopDto> shops = shopService.findExistsShops();
        logger.info(GET_SHOPS_LOG, shops.size());
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }





    @Operation(summary = "Getting shop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopDto> getShop(@PathVariable(name = "id") Long id) {
        ShopDto shopDto = shopService.getShopById(id);
        logger.info(GET_SHOP_LOG, shopDto);
        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }






    @Operation(summary = "Getting fist four shops by rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of shops", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any shop found", content = @Content)})
    @GetMapping("/shops/top")
    public ResponseEntity<List<ShopDto>> getFistForShopsByRating() {
        List<ShopDto> shopDtos = shopService.findFirst4ByOrderByRatingDesc();
        logger.info(GET_TOP_SHOPS_LOG, shopDtos.size());
        return new ResponseEntity<>(shopDtos, HttpStatus.OK);
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
    @GetMapping("/admin/shops/pretended/create/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<List<ShopDto>> getShopsToCreate() {
        List<ShopDto> shopDto = shopService.findShopsForCreate();
        logger.info(SHOPS_TO_CREATE, shopDto.size());
        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }




    @Operation(summary = "Create request for mark a shop to save")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Request is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShopDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop already exists",
                    content = @Content)
    })
    @PostMapping("/request/to_create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> userMarkShopToCreate(@RequestBody ShopDto shopDto) {
        shopDto.setModerated(true); // user mark a shop to create
        shopService.saveShop(shopDto);
        logger.info(GET_PRETENDED_TO_CREATE_SHOPS_LOG, shopDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
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
    @PutMapping("/shop/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> editShop(
            @RequestBody ShopDto shopDto) {
        Shop shop =  shopMapper.toEntity(shopDto, new CycleAvoidingMappingContext() );
        Optional<Shop> optionalShop = Optional.of(shop);
        if (optionalShop.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shopService.updateShopById(shopDto);
        logger.info(SHOP_UPDATED_LOG, shopDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Operation(summary = "Mark shop to delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Shop was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Shop not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<Long> markAsDeleteShop(@PathVariable(name = "id") Long id) {
        ShopDto shopDto = shopService.getShopById(id);
        shopDto.setPretendedToBeDeleted(true);
        logger.info(GET_PRETENDED_TO_DELETE_SHOPS_LOG, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteShop(@PathVariable(name = "id") Long id) {
        shopService.deleteShopById(id);
        logger.info(DELETE_SHOP, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
