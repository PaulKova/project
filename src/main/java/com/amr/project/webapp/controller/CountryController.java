package com.amr.project.webapp.controller;

import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.CountryService;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/country")
@Slf4j
@CrossOrigin
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "Getting all Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Countries", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CountryDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any Countries found", content = @Content)})
    @GetMapping("/")
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @Operation(summary = "Getting Country by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Country", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CountryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Country not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable(name = "id") Long id) {
        CountryDto countryDto = countryService.getCountryById(id);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }
}
