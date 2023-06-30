package itmasters.project.stem.controller;

import itmasters.project.stem.service.LeaderBoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leader-board")
@Slf4j
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public HttpEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(leaderBoardService.getAllUsers());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user-profile")
    public HttpEntity<?> getUserProfile(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(leaderBoardService.getUserProfile(request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}