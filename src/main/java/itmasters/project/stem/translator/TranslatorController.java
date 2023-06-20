package itmasters.project.stem.translator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/private/translate")
@Slf4j
public class TranslatorController {

    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @PostMapping
    public HttpEntity<?> translate(@RequestParam String text) {
        try {
            return ResponseEntity.status(201).body(translatorService.translate(text));
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
