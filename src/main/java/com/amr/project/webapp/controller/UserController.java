package com.amr.project.webapp.controller;

import com.amr.project.converter.mappers.UserMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
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
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String ID = "itemId";
    private static final String USER_UPDATED_LOG = "User:{} was updated";
    private static final String GET_USER_LOG = "User:{} is get";
    private static final String GET_USERS_LOG = "{} users has been loaded";



    private final UserService userService;
    private final UserMapper userMapper;



    @Operation(summary = "get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDto = userService.getAllUsers();
        logger.info(GET_USERS_LOG, userDto.size());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }





    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("users/{id}")
    public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        Optional<UserDto> optionalUserDto =Optional.of(userDto);
        logger.info(GET_USER_LOG, id);
        return new ResponseEntity<>(optionalUserDto, HttpStatus.OK);
    }





    @Operation(summary = "Update an User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Optional<User> optionalUser = Optional.of(user);
        if (optionalUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(userDto);
        logger.info(USER_UPDATED_LOG, userDto.getId());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }




}