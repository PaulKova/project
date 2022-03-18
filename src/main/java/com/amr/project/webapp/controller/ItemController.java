package com.amr.project.webapp.controller;


import com.amr.project.converter.mappers.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
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
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {


    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private static final String ID = "itemId";
    private static final String NEW_ITEM_LOG = "New item was created id:{}";
    private static final String ITEM_UPDATED_LOG = "Item:{} was updated";
    private static final String GET_ITEM_LOG = "Item:{} is get";
    private static final String GET_ITEMS_LOG = "{} items has been loaded";
    private static final String ITEM_TO_DELETE = "Item {} was marked to delete";
    private static final String ITEMS_TO_DELETE = "{} items has been marked to delete";
    private static final String DELETE_ITEM = "Deleted Item id: {}";

    private final ItemService itemService;
    private final ItemMapper itemMapper;





    @Operation(summary = "get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ItemDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> items = itemService.getAllItems();
        logger.info(GET_ITEMS_LOG, items.size());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @Operation(summary = "get item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ItemDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable(name = "id") Long id) {
        if (!itemService.getItemById(id).isPresent()) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<ItemDto> optionalItemDto = itemService.getItemById(id);
        ItemDto itemDto = optionalItemDto.get();
        logger.info(GET_ITEM_LOG, itemDto.getId());
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }


    @Operation(summary = "get first four item by rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ItemDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/items/top")
    public ResponseEntity<List<ItemDto>> getFistForItemsByRating() {
        List<ItemDto> itemDtos = itemService.findFirst4ByOrderByRatingDesc();
        logger.info(GET_ITEMS_LOG, itemDtos.size());
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }



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




    @Operation(summary = "Create a new Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Item is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class)))
    })
    @PostMapping("/items")
    public ResponseEntity<HttpStatus> addItem(@RequestBody ItemDto itemDto) {
        itemService.saveItem(itemDto);
        logger.info(NEW_ITEM_LOG,itemDto.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Update an Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Item was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Item not found",
                    content = @Content)
    })
    @PutMapping("/items")
    public ResponseEntity<HttpStatus> editItem(
            @RequestBody ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        Optional<Item> optionalItem = Optional.of(item);
        if (optionalItem.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        itemService.updateItem(itemDto);
        logger.info(ITEM_UPDATED_LOG,itemDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }





    @Operation(summary = "Mark item to delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Item was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Item not found",
                    content = @Content)
    })
    @DeleteMapping("/items/mark/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<ItemDto> markToDelete(@PathVariable(name = "id") Long id) {
        Optional<ItemDto> optionalItemDto = itemService.getItemById(id);
        ItemDto itemDto = optionalItemDto.get();
        itemDto.setPretendedToBeDeleted(true);
        logger.info(ITEM_TO_DELETE, itemDto.getId());
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
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
        logger.info(DELETE_ITEM, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}