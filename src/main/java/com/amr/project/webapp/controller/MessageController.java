package com.amr.project.webapp.controller;

import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.ChatNotification;
import com.amr.project.model.entity.Message;
import com.amr.project.service.abstracts.ChatService;
import com.amr.project.service.abstracts.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/messages")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    private static final String MESSAGE_SENT_LOG = "Message:{} is sent to User:{}";

    private final ChatService chatService;
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        Chat chat = chatService.getChatBySenderAndRecipient(message.getSender(), message.getRecipient());

        message.setChat(chat);

        Message savedMessage = messageService.save(message);

        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(message.getRecipient().getId()),
                "/messages",
                new ChatNotification(
                        savedMessage.getId(),
                        savedMessage.getSender().getId(),
                        savedMessage.getSender().getUsername())
        );

        log.info(MESSAGE_SENT_LOG, savedMessage, message.getRecipient());
    }

    @Operation(summary = "Getting messages by chatId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the chat", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageDto.class))}),
            @ApiResponse(responseCode = "404", description = "Chat not found", content = @Content)})
    @GetMapping("/{chatId}")
    public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable("chatId") Long chatId) {
        Optional<List<MessageDto>> messageDtoList = messageService.getAllMessagesByChatId(chatId);
        if (messageDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messageDtoList.get());
    }
}
