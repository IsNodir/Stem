package itmasters.project.stem.controller;

import itmasters.project.stem.payload.TopicDTO;
import itmasters.project.stem.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topic")
@Slf4j
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{subjectId}")
    public HttpEntity<?> getAllTopic(@PathVariable Integer subjectId) {
        try {
            return ResponseEntity.ok(topicService.getAllTopicBySubject(subjectId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public HttpEntity<?> getTopicById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(topicService.getTopicById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{subjectId}")
    public HttpEntity<?> addTopic(@PathVariable Integer subjectId, @RequestBody TopicDTO topicDTO) {
        try {
            return ResponseEntity.status(201).body(topicService.createTopic(subjectId, topicDTO));
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