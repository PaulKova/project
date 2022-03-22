package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private Long id;
    private String reason;
    private String fullText;
    private LocalDateTime dateTime;
    private String username;

    @JsonManagedReference
    private ShopDto shop;
    @JsonManagedReference
    private UserDto user;

}