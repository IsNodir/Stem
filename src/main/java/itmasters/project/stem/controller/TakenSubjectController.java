package itmasters.project.stem.controller;

import itmasters.project.stem.payload.takenSubject.TakenSubjectDTO;
import itmasters.project.stem.service.TakenSubjectService;
import jakarta.servlet.http.HttpServletRequest;
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

    //berilmidi
    @GetMapping("/{language}")
    public HttpEntity<?> getAllTakenSubjects(@PathVariable String language) {
        try {
            return ResponseEntity.ok(takenSubjectService.getAllTakenSubjects());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //berilmidi
    @GetMapping("/{language}/{id}")
    public HttpEntity<?> getTakenSubjectById(@PathVariable String language, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(takenSubjectService.getTakenSubjectById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //berilmidi
    @GetMapping("/current")
    public HttpEntity<?> getCurrentTakenSubjects(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(takenSubjectService.getCurrentTakenSubjects(request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{language}/completed")
    public HttpEntity<?> getCompletedSubjects(@PathVariable String language, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(takenSubjectService.getAllCompletedSubjects(language, request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{language}/taken")
    public HttpEntity<?> getTakenSubjects(@PathVariable String language, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(takenSubjectService.getTakenSubjects(language, request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public HttpEntity<?> addTakenSubject(
            @RequestBody TakenSubjectDTO takenSubjectDTO,
            HttpServletRequest request
    ) {
        try {
            return ResponseEntity.status(201).body(takenSubjectService.createTakenSubject(takenSubjectDTO, request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //TODO: Implement with topic progress service
    @PutMapping("/private/complete/{takenSubjectId}")
    public HttpEntity<?> updateTakenSubject(@PathVariable Integer takenSubjectId, HttpServletRequest request) {
        try {
            return ResponseEntity.status(202).body(takenSubjectService.updateTakenSubject(takenSubjectId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{takenSubjectId}")
    public HttpEntity<?> deleteTakenSubject(@PathVariable Integer takenSubjectId) {
        try {
            return ResponseEntity.ok(takenSubjectService.deleteTakenSubject(takenSubjectId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}