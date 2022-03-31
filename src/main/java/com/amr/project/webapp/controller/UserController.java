package com.amr.project.webapp.controller;

import com.amr.project.converter.mappers.UserMapper;
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
import com.amr.project.converter.CycleAvoidingMappingContext;

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
    private static final String DELETE_USER = "Deleted User id: {}";
    private static final String NEW_USER_LOG = "New user was created id:{}";



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
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        logger.info(GET_USER_LOG, id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }







    @Operation(summary = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)))
    })
    @PostMapping( "/admin/create/user")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        logger.info(NEW_USER_LOG, userDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto, new CycleAvoidingMappingContext());
        Optional<User> optionalUser = Optional.of(user);
        if (optionalUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(userDto);
        logger.info(USER_UPDATED_LOG, userDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
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
        logger.info(DELETE_USER, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}