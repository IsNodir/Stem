package itmasters.project.stem.ai;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/private/ai")
public class AiController {

    public AiController(RestTemplate template) {
        this.template = template;
    }

    private final RestTemplate template;

    private final String model = "gpt-3.5-turbo";

    private final String apiURL = "https://api.openai.com/v1/chat/completions";

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        AiRequest aiRequest = new AiRequest(model, prompt);
        AiResponse aiResponse = template.postForObject(apiURL, aiRequest, AiResponse.class);
        assert aiResponse != null;
        return aiResponse.getChoices().get(0).getMessage().getContent();
    }
}