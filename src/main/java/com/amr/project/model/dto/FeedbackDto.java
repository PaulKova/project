package com.amr.project.model.dto;

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

    private ShopDto shop;
    private UserDto user;

}