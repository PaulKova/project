package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private Long id;
    private Long hash;

    @JsonBackReference
    private List<MessageDto> messages;
    @JsonBackReference
    private List<UserDto> users;

}