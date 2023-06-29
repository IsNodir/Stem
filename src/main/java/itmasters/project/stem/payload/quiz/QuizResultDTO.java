package itmasters.project.stem.payload.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDTO {

    private Integer id;

    private String correctAnswer;

    private boolean isCorrect;

    public QuizResultDTO(Integer id, String correctAnswer) {
        this.id = id;
        this.correctAnswer = correctAnswer;
    }

}