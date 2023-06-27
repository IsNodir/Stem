package itmasters.project.stem.controller;

import itmasters.project.stem.entity.Section;
import itmasters.project.stem.payload.SectionDTO;
import itmasters.project.stem.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/section")
@Slf4j
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public HttpEntity<?> getAllSection() {
        try {
            return ResponseEntity.ok(sectionService.getAllSection());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{sectionId}")
    public HttpEntity<?> getSectionById(@PathVariable Integer sectionId) {
        try {
            return ResponseEntity.ok(sectionService.getSectionById(sectionId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{topicId}")
    public HttpEntity<?> getAllSectionByTopicId(@PathVariable Integer topicId) {
        try {
            return ResponseEntity.status(201).body(sectionService.getAllSectionByTopicId(topicId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{topicId}")
    public HttpEntity<?> addSection(@PathVariable Integer topicId, @RequestBody SectionDTO sectionDTO) {
        try {
            return ResponseEntity.status(201).body(sectionService.createSection(topicId, sectionDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{sectionId}")
    public HttpEntity<?> updateSection(@PathVariable Integer sectionId, @RequestBody SectionDTO sectionDTO) {
        try {
            return ResponseEntity.status(202).body(sectionService.updateSection(sectionId, sectionDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{sectionId}")
    public HttpEntity<?> deleteSection(@PathVariable Integer sectionId) {
        try {
            return ResponseEntity.ok(sectionService.deleteSection(sectionId));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}