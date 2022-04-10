package com.amr.project.webapp.controller;


import com.amr.project.converter.mappers.ContactFormMapper;
import com.amr.project.model.dto.ContactFormDto;
import com.amr.project.model.entity.ContactForm;
import com.amr.project.service.abstracts.ContactFormService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class ContactFormController {

    private static final Logger logger = LoggerFactory.getLogger(ContactFormController.class);
    private static final String ID = "itemId";
    private static final String CONTACT_FORM_UPDATED_LOG = "ContactForm:{} was updated";
    private static final String GET_CONTACT_FORM_LOG = "ContactForm:{} is get";
    private static final String GET_CONTACT_FORMS_LOG = "{} ContactForm has been loaded";
    private static final String DELETE_CONTACT_FORM = "Deleted ContactForm id: {}";
    private static final String NEW_CONTACT_FORM_LOG = "New ContactForm was created id:{}";


    private final ContactFormService contactFormService;
    private final ContactFormMapper contactFormMapper;



    @Operation(summary = "get all contactForms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContactFormDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/contactForms")
    public ResponseEntity<List<ContactFormDto>> getAllComments() {
        List<ContactFormDto> contactFormDto = contactFormService.getAllContactForms();
        logger.info(GET_CONTACT_FORMS_LOG, contactFormDto.size());
        return new ResponseEntity<>(contactFormDto, HttpStatus.OK);
    }


    @Operation(summary = "get contactForm by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the contactForm", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContactFormDto.class))}),
            @ApiResponse(responseCode = "404", description = "ContactForm not found", content = @Content)})
    @GetMapping("contactForms/{id}")
    public ResponseEntity<ContactFormDto> getUserById(@PathVariable Long id) {
        ContactFormDto contactFormDto = contactFormService.getContactFormById(id);
        logger.info(GET_CONTACT_FORM_LOG, id);
        return new ResponseEntity<>(contactFormDto, HttpStatus.OK);
    }




    @Operation(summary = "Create a new contactForm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "ContactForm is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactFormDto.class)))
    })
    @PostMapping( "/admin/create/comment")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> addNewComment(@RequestBody ContactFormDto contactFormDto) {
        contactFormService.saveContactForm(contactFormDto);
        logger.info(NEW_CONTACT_FORM_LOG, contactFormDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Operation(summary = "Update an contactForm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ContactForm was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactFormDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "ContactForm not found",
                    content = @Content)
    })
    @PutMapping("/contactForms")
    public ResponseEntity<HttpStatus> updateComment(@RequestBody ContactFormDto contactFormDto,
                                                 @RequestParam("file") MultipartFile file) throws IOException {

        ContactForm contactForm = contactFormMapper.toEntity(contactFormDto, new CycleAvoidingMappingContext());
        Optional<ContactForm> optionalСontactForm = Optional.of(contactForm);
        if (optionalСontactForm.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        contactFormService.updateContactForm(contactFormDto);
        logger.info(CONTACT_FORM_UPDATED_LOG, contactFormDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete an contactForm by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ContactForm was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactFormDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "ContactForm not found",
                    content = @Content)
    })
    @DeleteMapping("/admin/delete/contactForms/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        contactFormService.deleteContactFormById(id);
        logger.info(DELETE_CONTACT_FORM, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
