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
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String NEW_ORDER_LOG = "New order was created id:{}";
    private static final String ORDER_UPDATED_LOG = "Order id:{} was updated";
    private static final String GET_ORDER_LOG = "Order id:{} is loaded";
    private static final String GET_ORDERS_LOG = "{} orders is loaded";
    private static final String DELETE_ORDER_LOG = "Order id:{} is deleted";

    private final OrderService orderService;

    @Operation(summary = "get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders found", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "404", description = "No orders found", content = @Content)})
    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllCategories() {
        List<OrderDto> orders = orderService.getAllOrders();
        logger.info(GET_ORDERS_LOG, orders.size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderDto>> getOrder(@PathVariable(name = "id") Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        logger.info(GET_ORDER_LOG, orderDto.getId());
        return new ResponseEntity<>(Optional.of(orderDto), HttpStatus.OK);
    }

    @Operation(summary = "Create a new Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Order is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class)))
    })
    @PostMapping("/")
    public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        logger.info(NEW_ORDER_LOG, orderDto.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an Order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<HttpStatus> editOrder( @RequestBody OrderDto orderDto) {
        orderService.updateOrder(orderDto);
        logger.info(ORDER_UPDATED_LOG, orderDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete an Order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.deleteOrder(id);
        logger.info(DELETE_ORDER_LOG, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
