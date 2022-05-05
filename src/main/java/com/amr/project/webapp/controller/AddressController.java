package com.amr.project.webapp.controller;

import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.service.abstracts.AddressService;
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
@RequestMapping("/api/address")
@Slf4j
@CrossOrigin
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Getting all adresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all addresses", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressDto.class))}),
            @ApiResponse(responseCode = "404", description = "No any Countries found", content = @Content)})
    @GetMapping("/adresses")
    public ResponseEntity<List<AddressDto>> getAllAdresses() {
        List<AddressDto> adresses = addressService.getAllAddresses();
        return new ResponseEntity<>(adresses, HttpStatus.OK);
    }

    @Operation(summary = "Getting Address by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Country", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressDto.class))}),
            @ApiResponse(responseCode = "404", description = "Country not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAdress(@PathVariable(name = "id") Long id) {
        AddressDto addressDto = addressService.getAddressById(id);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }
}
