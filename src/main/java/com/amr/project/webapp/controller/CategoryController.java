package com.amr.project.webapp.controller;


import com.amr.project.converter.mappers.CategoryMapper;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
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
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private static final String GET_CATEGORIES_LOG = "{} categories has been loaded";
    private static final String GET_CATEGORY_LOG = "Category:{} is get";
    private static final String NEW_CATEGORIES_LOG = "New Category was created id: {}";
    private static final String CATEGORIES_LOG = "Category:{} was updated";
    private static final String DELETE_CATEGORIES = "Category: id - {} was deleted";

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        logger.info(GET_CATEGORIES_LOG, categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }






    @Operation(summary = "get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/categories/{id}")
    public ResponseEntity<Optional<CategoryDto>> getCategory(@PathVariable(name = "id") Long id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        logger.info(GET_CATEGORY_LOG, categoryDto.getId());
        return new ResponseEntity<>(Optional.of(categoryDto), HttpStatus.OK);
    }






    @Operation(summary = "Create a new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Category is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDto.class)))
    })
    @PostMapping("/categories")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto);
        logger.info(NEW_CATEGORIES_LOG, categoryDto.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }






    @Operation(summary = "Update an Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Category was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Category not found",
                    content = @Content)
    })
    @PutMapping("/categories")
    public ResponseEntity<HttpStatus> editCategory( @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto, new CycleAvoidingMappingContext());
        categoryService.updateCategory(categoryDto);
        logger.info(CATEGORIES_LOG, categoryDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }





    @Operation(summary = "Delete an Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Category was deleted",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Category not found",
                    content = @Content)
    })
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        logger.info(DELETE_CATEGORIES, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
