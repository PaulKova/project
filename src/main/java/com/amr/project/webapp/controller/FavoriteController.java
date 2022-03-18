package com.amr.project.webapp.controller;

import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.service.abstracts.FavoriteService;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class FavoriteController {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
    private static final String ID = "itemId";
    private static final String Favorite_UPDATED_LOG = "Favorite:{} was updated";
    private static final String GET_Favorite_LOG = "Favorite:{} is get";
    private static final String GET_Favorites_LOG = "{} favorites has been loaded";
    private static final String DELETE_Favorite = "Deleted Favorite id: {}";
    private static final String NEW_Favorite_LOG = "New favorite was created id:{}";

    private final FavoriteService favoriteService;

    @Operation(summary = "get all favorites")
    @ApiResponse(responseCode = "200", description = "Found the favorites", content =
            {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FavoriteDto.class))})
    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteDto>> getAllFavorites() {
        List<FavoriteDto> favoriteDto = favoriteService.getAllFavorites();
        logger.info(GET_Favorites_LOG, favoriteDto.size());
        return new ResponseEntity<>(favoriteDto, HttpStatus.OK);
    }

    @Operation(summary = "get favorite by id")
            @ApiResponse(responseCode = "200", description = "Found the favorite", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FavoriteDto.class))})
    @GetMapping("favorites/{id}")
    public ResponseEntity<FavoriteDto> getFavoriteById(@PathVariable Long id) {
        FavoriteDto favoriteDto = favoriteService.findById(id);
        logger.info(GET_Favorite_LOG, id);
        return new ResponseEntity<>(favoriteDto, HttpStatus.OK);
    }


    @Operation(summary = "Create a new Favorite")
            @ApiResponse(responseCode = "201",
                    description = "Favorite is created",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FavoriteDto.class))})
    @PostMapping("/favorites")
    public ResponseEntity<HttpStatus> addNewFavorite(@RequestBody FavoriteDto favoriteDto) {
        favoriteService.saveFavorite(favoriteDto);
        logger.info(NEW_Favorite_LOG, favoriteDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update an Favorite")
            @ApiResponse(responseCode = "200",
                    description = "Favorite was updated",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FavoriteDto.class))})
    @PutMapping("/favorites")
    public ResponseEntity<HttpStatus> updateFavorite(@RequestBody FavoriteDto favoriteDto) {
        favoriteService.updateFavorite(favoriteDto);
        logger.info(Favorite_UPDATED_LOG, favoriteDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete an Favorite by its ID")
    @ApiResponse(responseCode = "200",
            description = "Favorite was updated",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FavoriteDto.class)))
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<HttpStatus> deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
        logger.info(DELETE_Favorite, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}