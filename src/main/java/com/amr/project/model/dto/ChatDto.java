package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private Long id;
    private Long hash;

    @JsonBackReference
    private List<MessageDto> messages;
    @JsonBackReference
    private List<UserDto> users;

}