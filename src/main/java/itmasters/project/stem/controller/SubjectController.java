package itmasters.project.stem.controller;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.payload.subject.SubjectDTO;
import itmasters.project.stem.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public HttpEntity<?> getAllSubject() {
        try {
            return ResponseEntity.ok(subjectService.getAllSubject());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{language}/language")
    public HttpEntity<?> getAllSubjectByLanguage(@PathVariable String language) {
        try {
            return ResponseEntity.ok(subjectService.getAllSubjectByLanguage(language));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getSubjectById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public HttpEntity<?> addSubject(@RequestBody Subject subject) {
        try {
            return ResponseEntity.status(201).body(subjectService.createSubject(subject));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateSubject(@PathVariable Integer id, @RequestBody SubjectDTO subjectDTO) {
        try {
            return ResponseEntity.status(202).body(subjectService.updateSubject(id, subjectDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSubject(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(subjectService.deleteSubject(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}