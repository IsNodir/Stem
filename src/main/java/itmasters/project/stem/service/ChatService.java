package itmasters.project.stem.service;

import itmasters.project.stem.ai.AiRequest;
import itmasters.project.stem.ai.AiResponse;
import itmasters.project.stem.translator.TranslatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ChatService {

    private final TranslatorService translatorService;

    private final RestTemplate template;

    public String text;

    public ChatService(TranslatorService translatorService, RestTemplate template) {
        this.translatorService = translatorService;
        this.template = template;
    }

    private final String aiModel = "gpt-3.5-turbo";

    private final String aiApiURL = "https://api.openai.com/v1/chat/completions";

    public List<String> uzChat (String message) {

        AiRequest aiRequest = new AiRequest(aiModel, translatorService.translateUzEn(message).get(0));
        AiResponse aiResponse = template.postForObject(aiApiURL, aiRequest, AiResponse.class);
        assert aiResponse != null;

        String enMessage = aiResponse.getChoices().get(0).getMessage().getContent();

        return translatorService.translateEnUz(enMessage);
    }

    public String enChat (String message) {

        AiRequest aiRequest = new AiRequest(aiModel, message);
        AiResponse aiResponse = template.postForObject(aiApiURL, aiRequest, AiResponse.class);
        assert aiResponse != null;

        return aiResponse.getChoices().get(0).getMessage().getContent();
    }

}
