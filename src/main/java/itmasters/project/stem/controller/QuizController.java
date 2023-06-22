package itmasters.project.stem.controller;

import itmasters.project.stem.payload.QuizDTO;
import itmasters.project.stem.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz")
@Slf4j
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public HttpEntity<?> getAllQuiz(@PathVariable String topic) {
        try {
            return ResponseEntity.ok(quizService.getAllQuiz());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getQuizById(@PathVariable Integer id,
                                     @PathVariable String topic) {
        try {
            return ResponseEntity.ok(quizService.getQuizById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public HttpEntity<?> addQuiz(@PathVariable Integer id,
                                 @RequestBody QuizDTO quizDTO,
                                 @PathVariable String topic) {
        try {
            return ResponseEntity.status(201).body(quizService.createQuiz(id, quizDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateQuiz(@PathVariable Integer id,
                                    @RequestBody QuizDTO quizDTO,
                                    @PathVariable String topic) {
        try {
            return ResponseEntity.status(202).body(quizService.updateQuiz(id, quizDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteQuiz(@PathVariable Integer id,
                                    @PathVariable String topic) {
        try {
            return ResponseEntity.ok(quizService.deleteQuiz(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
