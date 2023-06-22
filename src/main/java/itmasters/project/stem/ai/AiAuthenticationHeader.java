package itmasters.project.stem.ai;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiAuthenticationHeader {

    private final String aiApiKey = "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";

    @Bean
    public RestTemplate template(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + aiApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
