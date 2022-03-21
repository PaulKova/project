package com.amr.project.webapp.controller;


import com.amr.project.model.dto.ReviewDto;
import com.amr.project.service.abstracts.ReviewService;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
    private static final String ID = "reviewId";
    private static final String NEW_REVIEW_LOG = "New review was created (id:{})";
    private static final String REVIEW_UPDATED_LOG = "Review:id{} was updated";
    private static final String GET_REVIEWS_LOG = "{} reviews has been loaded";
    private static final String DELETE = "Deleted Review (id:{})";
    private static final String NO_PARAMETER_LOG = "No any parameter for method getVisibleReview() (URL:/review/reviews/), " +
            "must be {userid} OR {shopid} OR {itemid}";

    private final ReviewService reviewService;



    //Обработка запроса на вывод списка ревью для модерации (флаг isModerated=false)
    @Operation(summary = "Get all reviews for moderating (Authority: MODERATOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all reviews",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReviewDto.class))}),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)})
    @GetMapping("/reviews")
    //@PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<ReviewDto>> getReviews() {
        List<ReviewDto> reviews = reviewService.getAllNoModeratedReview();
        logger.info(GET_REVIEWS_LOG, reviews.size());
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



    //Обработка запроса на вывод списка ревью для определенного Пользователя/Магазина/Товара(задается в параметрах URL)
    //Выдается список только тех ревью, которые прошли модерацию и приняты
    //(флаги isModerated=true, isModerateAccept=true)
    @Operation(summary = "Get all reviews for viewing by User Id OR Shop Id OR Item Id (Authority: no authority")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all reviews",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReviewDto.class))}),
            @ApiResponse(responseCode = "400", description = "No any parameter", content = @Content),
            @ApiResponse(responseCode = "404", description = "No any Review was found", content = @Content)})
    @GetMapping("/reviews/{userid}{shopid}{itemid}")
    public ResponseEntity<List<ReviewDto>> getVisibleReview(
            @RequestParam(name = "userid", required = false, defaultValue = "-1") Long userId,
            @RequestParam(name = "shopid", required = false, defaultValue = "-1") Long shopId,
            @RequestParam(name = "itemid", required = false, defaultValue = "-1") Long itemId) {

        List<ReviewDto> reviews;
        if (userId >= 0) {
            reviews = reviewService.getAllUserReviewById(userId);
        } else if (shopId >= 0) {
            reviews = reviewService.getAllShopReviewById(shopId);
        } else if (itemId >= 0) {
            reviews = reviewService.getAllItemReviewById(itemId);
        } else {
            logger.error(NO_PARAMETER_LOG);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info(GET_REVIEWS_LOG, reviews.size());
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



    //Новое ревью(отзыв) создается любым авторизованным пользователем.
    //После создания ревью должно пройти модерацию, только после этого оно может быть отражено в общем доступе
    //на странице соответствующего адресата (Пользователя (как продавца), Магазина, Товара)
    @Operation(summary = "Create a new Review (Authority: USER)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Review is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReviewDto.class)))
    })
    @PostMapping("/new")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> newReview(@RequestBody ReviewDto reviewDto) {
        Long id = reviewService.saveReviewAsNoModerated(reviewDto).getId();
        logger.info(NEW_REVIEW_LOG, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Update an Review by ID (Authority: MODERATOR)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReviewDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Review not found",
                    content = @Content)
    })
    @PutMapping("/")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> updateReview(@RequestBody ReviewDto reviewDto) {
        reviewService.updateReview(reviewDto);
        logger.info(REVIEW_UPDATED_LOG, reviewDto.getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @Operation(summary = "Delete an Review by ID (Authority: ADMIN, MODERATOR)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReviewDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Review not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ResponseEntity<Long> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        logger.info(DELETE, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
    
}
