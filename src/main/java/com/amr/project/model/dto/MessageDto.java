package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MessageDto {
    private Long id;
    private Date date;
    private String textMessage;
    private boolean viewed;


    private UserDto user;
    private ChatDto chat;

}
