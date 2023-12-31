package itmasters.project.stem.controller;

import itmasters.project.stem.payload.quiz.FinishedQuiz;
import itmasters.project.stem.payload.quiz.QuizDTO;
import itmasters.project.stem.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@Slf4j
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{language}/{topicId}")
    public HttpEntity<?> getQuiz(
            @PathVariable Integer topicId,
            @PathVariable String language
    ) {
        try {
            return ResponseEntity.ok(quizService.checkLanguage(topicId, language));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{language}/{topicId}/topicId")
    public HttpEntity<?> getAllQuizByTopicId(
            @PathVariable Integer topicId,
            @PathVariable String language
    ) {
        try {
            return ResponseEntity.ok(quizService.getAllQuizByTopicId(topicId, language));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{language}/{subjectId}/subject")
    public HttpEntity<?> getAllQuizBySubjectId(
            @PathVariable Integer subjectId,
            @PathVariable String language
    ) {
        try {
            return ResponseEntity.ok(quizService.getQuizBySubjectId(subjectId, language));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getQuizById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(quizService.getQuizById(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{topicId}/result")
    public HttpEntity<?> getQuizResultByTopicId(
            @PathVariable Integer topicId,
            HttpServletRequest request
    ) {
        try {
            return ResponseEntity.ok(quizService.getQuizResultsAndSuggestTopics(topicId, request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{topicId}")
    public HttpEntity<?> addQuiz(@PathVariable Integer topicId,
                                 @RequestBody QuizDTO quizDTO) {
        try {
            return ResponseEntity.status(201).body(quizService.createQuiz(topicId, quizDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{language}/check/{topicId}")
    public HttpEntity<?> checkQuiz(@PathVariable Integer topicId,
                                   @PathVariable String language,
                                   @RequestBody List<FinishedQuiz> finishedQuiz,
                                   HttpServletRequest request) {
        try {
            return ResponseEntity.status(201).body(quizService.getQuizResult(topicId, finishedQuiz, language, request));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateQuiz(@PathVariable Integer id,
                                    @RequestBody QuizDTO quizDTO) {
        try {
            return ResponseEntity.status(202).body(quizService.updateQuiz(id, quizDTO));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteQuiz(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(quizService.deleteQuiz(id));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}