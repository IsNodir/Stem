package itmasters.project.stem.controller;

import itmasters.project.stem.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@Slf4j
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/uz")
    public HttpEntity<?> uzChat(@RequestParam String message) {
        try {
            return ResponseEntity.status(201).body(chatService.uzChat(message));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/en")
    public HttpEntity<?> enChat(@RequestParam String message) {
        try {
            return ResponseEntity.status(201).body(chatService.enChat(message));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
