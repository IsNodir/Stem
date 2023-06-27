package itmasters.project.stem.translator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TranslatorService {

    private final AuthenticationHeader authHeader;

    public TranslatorService(AuthenticationHeader authHeader) {
        this.authHeader = authHeader;
    }

    public List<String> translateUzEn(String text) {
        try {
            TranslatorResponse translatorResponse = authHeader.UzEn(text);
            return translatorResponse.getText();
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }

    public List<String> translateEnUz(String text) {
        try {
            TranslatorResponse translatorResponse = authHeader.EnUz(text);
            return translatorResponse.getText();
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }

}