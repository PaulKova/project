package com.amr.project.webapp.controller;

import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.model.dto.PersonalDataDto;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/personalData")
@CrossOrigin
public class PersonalDataController {

    private static final Logger logger = LoggerFactory.getLogger(PersonalDataController.class);


    private static final String GET_PERSONALDATA_LOG = "PersonalData:{} is get";
    private static final String PERSONALDATA_LOG_CONFIRMED = "PersonalData:{} is confirmed";
    private static final String PERSONALDATA_LOG_REJECTED = "PersonalData:{} is rejected";
    private static final String DELETE_PERSONALDATA = "Deleted PersonalData id: {}";
    private static final String NEW_PERSONALDATA_LOG = "New PersonalData was created id:{}";


    private final PersonalDataService personalDataService;
    private final PersonalDataMapper personalDataMapper;


    @Operation(summary = "Create a new PersonalData")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PersonalData was created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class)))})
    @PostMapping("/newPersonalData")
    public ResponseEntity<HttpStatus> addNewPersonalData(@RequestBody PersonalDataDto personalDataDto) {
        personalDataService.savePersonalData(personalDataDto);
        logger.info(NEW_PERSONALDATA_LOG, personalDataDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Getting all personalData")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of personalData created",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))}),
            @ApiResponse(responseCode = "400", description = "No any personalData was found", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<PersonalDataDto>> getAllPersonalData() {
        List<PersonalDataDto> personalData = personalDataService.getAllPersonalData();
        return new ResponseEntity<>(personalData, HttpStatus.OK);
    }


    @Operation(summary = "Get personalData by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the personalData", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))}),
            @ApiResponse(responseCode = "404", description = "personalData not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<PersonalDataDto> getPersonalDataById(@PathVariable Long id) {
        PersonalDataDto personalDataDto = personalDataService.getPersonalDataById(id);
        logger.info(GET_PERSONALDATA_LOG, id);
        return new ResponseEntity<>(personalDataDto, HttpStatus.OK);
    }




    @Operation(summary = "Create response for user's identification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))),
            @ApiResponse(responseCode = "404", description = "Not all information about user was given", content = @Content)})
    @PostMapping("/changeStatusUser/confirm")
    public ResponseEntity<String> confirmPersonalData (@RequestBody PersonalDataDto personalDataDto) {
        try {
            personalDataService.changeStatusToConfirmed(personalDataDto.getId());
            logger.info(PERSONALDATA_LOG_CONFIRMED, personalDataDto.getId());
            return new ResponseEntity<>("Статус успешно обновлен", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Произошло исключение", HttpStatus.BAD_REQUEST);
        }
    }



    @Operation(summary = "Create response for user's identification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))),
            @ApiResponse(responseCode = "404", description = "Not all information about user was given", content = @Content)})
    @PostMapping("/changeStatusUser/reject")
    public ResponseEntity<String> rejectPersonalData (@RequestBody PersonalDataDto personalDataDto) {
        try {
            personalDataService.changeStatusToRejected(personalDataDto.getId());
            logger.info(PERSONALDATA_LOG_REJECTED, personalDataDto.getId());
            return new ResponseEntity<>("Статус успешно обновлен", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Произошло исключение", HttpStatus.BAD_REQUEST);
        }
    }



    @Operation(summary = "Delete a personalData by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PersonalData was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonalDataDto.class))),
            @ApiResponse(responseCode = "404", description = "PersonalData not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePersonalData(@PathVariable Long id) {
        personalDataService.deletePersonalData(id);
        logger.info(DELETE_PERSONALDATA, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
