package com.amr.project.model.dto;

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

    private List<MessageDto> messages;
    private List<UserDto> users;

}