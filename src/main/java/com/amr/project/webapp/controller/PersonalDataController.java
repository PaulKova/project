package com.amr.project.webapp.controller;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.enums.PersonalDataStatus;
import com.amr.project.service.abstracts.PersonalDataService;


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
@CrossOrigin
public class PersonalDataController {

    private static final Logger logger = LoggerFactory.getLogger(PersonalDataController.class);


    private static final String GET_PERSONALDATA_LOG = "PersonalData:{} is get";
    private static final String PERSONALDATA_LOG_CONFIRMED = "PersonalData:{} is confirmed";
    private static final String PERSONALDATA_LOG_REJECTED = "PersonalData:{} is rejected";
    private static final String PERSONALDATA_UPDATED_LOG = "PersonalData was updated id:{}";
    private static final String DELETE_PERSONALDATA = "Deleted PersonalData id: {}";
    private static final String NEW_PERSONALDATA_LOG = "New PersonalData was created id:{}";
    private static final String PATCH_PERSONALDATA_LOG = "PersonalData:{} status is change";
    private static final String DELETE_PERSONALDATA_LOG = "Deleted PersonalData id: {}";


    private final PersonalDataService personalDataService;
    private final PersonalDataMapper personalDataMapper;


    @Operation(summary = "get all PersonalData with param")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the PersonalData", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))}),
            @ApiResponse(responseCode = "404", description = "PersonalData not found", content = @Content)})
    @GetMapping("/admin/personalData")
    public ResponseEntity<List<PersonalDataDto>> getAllPersonalDataWithParam(@RequestParam(name = "Status", defaultValue="WAITING") PersonalDataStatus personal_data_status) {
        List<PersonalDataDto> personalDataDto = personalDataService.getByStatus(personal_data_status);
        logger.info(GET_PERSONALDATA_LOG, personalDataDto.size());
        return new ResponseEntity<>(personalDataDto, HttpStatus.OK);
    }


    @Operation(summary = "Update an PersonalData")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "PersonalData was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonalDataDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "PersonalData not found",
                    content = @Content)
    })
    @PutMapping("/personalData")
    public ResponseEntity<HttpStatus> updatePersonalData(@RequestBody PersonalDataDto personalDataDto) {
        PersonalData personalData = personalDataMapper.toEntity(personalDataDto, new CycleAvoidingMappingContext());
        Optional<PersonalData> optionalPersonalData = Optional.of(personalData);
        if (optionalPersonalData.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        personalDataService.updatePersonalData(personalDataDto);
        personalDataService.setWaitingAndFalse(personalDataDto);
        logger.info(PERSONALDATA_UPDATED_LOG, personalDataDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Patch a personalData by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PersonalData was update status",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))),
            @ApiResponse(responseCode = "404", description = "PersonalData not found", content = @Content)})
    @PatchMapping("/admin/personalData/{id}")
    public ResponseEntity<Long> updateStatus(@PathVariable Long id,
                                             @RequestParam("Status") PersonalDataStatus personalDataStatus,
                                             @RequestParam(value = "Comment") String comment) {
        personalDataService.changeStatus(id, personalDataStatus, comment);
        logger.info(PATCH_PERSONALDATA_LOG, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @Operation(summary = "Delete a personalData by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PersonalData was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))),
            @ApiResponse(responseCode = "404", description = "PersonalData not found", content = @Content)})
    @DeleteMapping("/admin/personalData/{id}")
    public ResponseEntity<Long> deletePersonalData(@PathVariable Long id) {
        personalDataService.deletePersonalData(id);
        logger.info(DELETE_PERSONALDATA_LOG, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(summary = "Create a new PersonalData")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PersonalData was created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class)))})
    @PostMapping("/personalData")
    public ResponseEntity<HttpStatus> addNewPersonalData(@RequestBody PersonalDataDto personalDataDto) {
        personalDataService.savePersonalData(personalDataDto);
        logger.info(NEW_PERSONALDATA_LOG, personalDataDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get personalData by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the personalData", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))}),
            @ApiResponse(responseCode = "404", description = "personalData not found", content = @Content)})
    @GetMapping("/personalData/{id}")
    public ResponseEntity<PersonalDataDto> getPersonalDataById(@PathVariable Long id) {
        PersonalDataDto personalDataDto = personalDataService.getPersonalDataById(id);
        logger.info(GET_PERSONALDATA_LOG, id);
        return new ResponseEntity<>(personalDataDto, HttpStatus.OK);
    }

}
