package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ChatDto;
import com.amr.project.service.abstracts.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ChatDto>> getAllChatOfUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(chatService.getAllChatsOfUser(userId));
    }
}
