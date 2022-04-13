package com.amr.project.webapp.controller;

import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.model.dto.PersonalDataDto;
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


}
