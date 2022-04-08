package com.amr.project.webapp.controller;


import com.amr.project.converter.mappers.CommentMapper;
import com.amr.project.model.dto.CommentDto;
import com.amr.project.model.entity.Comment;
import com.amr.project.service.abstracts.CommentService;
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
@RequestMapping("/api/comment")
@AllArgsConstructor
@CrossOrigin
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private static final String ID = "itemId";
    private static final String COMMENT_UPDATED_LOG = "Comment:{} was updated";
    private static final String GET_COMMENT_LOG = "Comment:{} is get";
    private static final String GET_COMMENTS_LOG = "{} Comment has been loaded";
    private static final String DELETE_COMMENT = "Deleted Comment id: {}";
    private static final String NEW_COMMENT_LOG = "New Comment was created id:{}";


    private final CommentService commentService;
    private final CommentMapper commentMapper;



    @Operation(summary = "get all comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> commentDto = commentService.getAllComments();
        logger.info(GET_COMMENTS_LOG, commentDto.size());
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }


    @Operation(summary = "get comment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content =
                    {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping("comments/{id}")
    public ResponseEntity<CommentDto> getUserById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getCommentById(id);
        logger.info(GET_COMMENT_LOG, id);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }




    @Operation(summary = "Create a new Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Comment is created",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class)))
    })
    @PostMapping( "/admin/create/comment")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> addNewComment(@RequestBody CommentDto commentDto) {
        commentService.saveComment(commentDto);
        logger.info(NEW_COMMENT_LOG, commentDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Operation(summary = "Update an Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comment was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Comment not found",
                    content = @Content)
    })
    @PutMapping("/comments")
    public ResponseEntity<HttpStatus> updateComment(@RequestBody CommentDto commentDto,
                                                 @RequestParam("file") MultipartFile file) throws IOException {

        Comment comment = commentMapper.toEntity(commentDto, new CycleAvoidingMappingContext());
        Optional<Comment> optionalComment = Optional.of(comment);
        if (optionalComment.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentService.updateComment(commentDto);
        logger.info(COMMENT_UPDATED_LOG, commentDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete an Comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comment was updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Comment not found",
                    content = @Content)
    })
    @DeleteMapping("/admin/delete/comment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        logger.info(DELETE_COMMENT, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
