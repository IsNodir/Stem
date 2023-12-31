package itmasters.project.stem.translator;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthenticationHeader {

    public TranslatorResponse UzEn(String text) {

        final String key = "trnsl.1.1.20230610T083326Z.91a3793f9beee2a0.fe3528a48df9ef771c6d885d708ac8c33c234475";
        final String lang = "uz-en";
        final String baseUrl = String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s", key, text, lang);
        WebClient.Builder webClient = WebClient.builder();

        return webClient.build()
                .post()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(TranslatorResponse.class)
                .block();
    }

    public TranslatorResponse EnUz(String text) {

        final String key = "trnsl.1.1.20230704T102321Z.80a04aaf9f40adf8.d11e7af3845904f57514028d0c9cff4239438334";
        final String lang = "en-uz";
        final String baseUrl = String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s", key, text, lang);
        WebClient.Builder webClient = WebClient.builder();

        return webClient.build()
                .post()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(TranslatorResponse.class)
                .block();
    }

}
