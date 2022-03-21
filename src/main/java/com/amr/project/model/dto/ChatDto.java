package com.amr.project.model.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private Long id;
    private Long hash;

    private List<MessageDto> messages;
    private List<UserDto> users;

}