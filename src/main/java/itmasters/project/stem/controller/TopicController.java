package itmasters.project.stem.controller;

import itmasters.project.stem.payload.TopicDTO;
import itmasters.project.stem.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject/topic")
@Slf4j
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public HttpEntity<?> getAllTopic() {
        try {
            return ResponseEntity.ok(topicService.getAllTopic());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getTopicById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(topicService.getTopicById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public HttpEntity<?> addTopic(@PathVariable Integer id, @RequestBody TopicDTO topicDTO) {
        try {
            return ResponseEntity.status(201).body(topicService.createTopic(id, topicDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateTopic(@PathVariable Integer id, @RequestBody TopicDTO topicDTO) {
        try {
            return ResponseEntity.status(202).body(topicService.updateTopic(id, topicDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTopic(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(topicService.deleteTopic(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}