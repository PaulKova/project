package com.amr.project.webapp.controller;

import com.amr.project.model.dto.OrderDto;
import com.amr.project.service.abstracts.OrderService;
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
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String NEW_ORDER_LOG = "New order was created id:{}";
    private static final String ORDER_UPDATED_LOG = "Order1 id:{} was updated";
    private static final String GET_ORDER_LOG = "Order1 id:{} is loaded";
    private static final String GET_ORDERS_LOG = "{} order1s is loaded";
    private static final String DELETE_ORDER_LOG = "Order1 id:{} is deleted";

    private final OrderService orderService;

    @Operation(summary = "get all order1s")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders found", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "404", description = "No order1s found", content = @Content)})
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllCategories() {
        List<OrderDto> orders = orderService.getAllOrders();
        logger.info(GET_ORDERS_LOG, orders.size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order1 not found", content = @Content)})
    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<OrderDto>> getOrder(@PathVariable(name = "id") Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        logger.info(GET_ORDER_LOG, orderDto.getId());
        return new ResponseEntity<>(Optional.of(orderDto), HttpStatus.OK);
    }

    @Operation(summary = "Create a new Order1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Order1 is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class)))
    })
    @PostMapping("/orders")
    public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        logger.info(NEW_ORDER_LOG, orderDto.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an Order1 by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order1 was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order1 not found",
                    content = @Content)
    })
    @PutMapping("/orders")
    public ResponseEntity<HttpStatus> editOrder( @RequestBody OrderDto orderDto) {
        orderService.updateOrder(orderDto);
        logger.info(ORDER_UPDATED_LOG, orderDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete an Order1 by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order1 was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order1 not found",
                    content = @Content)
    })
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.deleteOrder(id);
        logger.info(DELETE_ORDER_LOG, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
