package itmasters.project.stem.controller;

import itmasters.project.stem.entity.TakenSubject;
import itmasters.project.stem.service.TakenSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/taken-subject")
@Slf4j
public class TakenSubjectController {

    private final TakenSubjectService takenSubjectService;

    public TakenSubjectController(TakenSubjectService takenSubjectService) {
        this.takenSubjectService = takenSubjectService;
    }

    @GetMapping
    public HttpEntity<?> getAllTakenSubjects() {
        try {
            return ResponseEntity.ok(takenSubjectService.getAllTakenSubjects());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getTakenSubjectById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(takenSubjectService.getTakenSubjectById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/current/{userId}")
    public HttpEntity<?> getCurrentTakenSubjects(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(takenSubjectService.getCurrentTakenSubjects(userId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public HttpEntity<?> addTakenSubject(@RequestBody TakenSubject takenSubject) {
        try {
            return ResponseEntity.status(201).body(takenSubjectService.createTakenSubject(takenSubject));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/private/complete/{id}")
    public HttpEntity<?> updateTakenSubject(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(202).body(takenSubjectService.updateTakenSubject(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTakenSubject(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(takenSubjectService.deleteTakenSubject(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
