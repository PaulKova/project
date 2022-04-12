package com.amr.project.model.entity;


import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ChatNotification {

    private Long id;
    private Long senderId;
    private String senderName;
}
