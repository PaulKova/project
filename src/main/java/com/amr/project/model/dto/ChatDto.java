package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private Long id;
    private Long hash;

    private List<MessageDto> messages;
    private List<UserDto> users;

}