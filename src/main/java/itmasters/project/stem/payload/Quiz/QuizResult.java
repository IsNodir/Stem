package itmasters.project.stem.payload.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResult {

    public QuizResult(Integer id, String correctAnswer) {
        this.id = id;
        this.correctAnswer = correctAnswer;
    }

    private Integer id;

    private String correctAnswer;

    private boolean isCorrect;

}