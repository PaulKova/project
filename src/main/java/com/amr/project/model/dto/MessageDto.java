package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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

    @JsonManagedReference
    private UserDto user;
    @JsonManagedReference
    private ChatDto chat;

}
