package com.amr.project.webapp.controller;

import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/city")
@Slf4j
@CrossOrigin
public class CityController {

    private final CityService cityService;

    @Operation(summary = "Getting all Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Cities", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CityDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any shop found", content = @Content)})
    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @Operation(summary = "Getting City by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the City", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CityDto.class))}),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable(name = "id") Long id) {
        CityDto cityDto = cityService.getCityById(id);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }


}
