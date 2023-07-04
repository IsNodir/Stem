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
