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

    @GetMapping("/{id}")
    public HttpEntity<?> getSectionById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(sectionService.getSectionById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public HttpEntity<?> addSection(@PathVariable Integer id, @RequestBody SectionDTO sectionDTO) {
        try {
            return ResponseEntity.status(201).body(sectionService.createSection(id, sectionDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateSection(@PathVariable Integer id, @RequestBody SectionDTO sectionDTO) {
        try {
            return ResponseEntity.status(202).body(sectionService.updateSection(id, sectionDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSection(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(sectionService.deleteSection(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}