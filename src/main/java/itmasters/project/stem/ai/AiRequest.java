package itmasters.project.stem.ai;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AiRequest {

    public AiRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    private String model;

    private List<Message> messages;

}
