package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class MessageDto {
    private Long id;
    private Date date;
    private String textMessage;
    private boolean viewed;

    //@JsonManagedReference
    private UserDto user;
    //@JsonManagedReference
    private ChatDto chat;

}
