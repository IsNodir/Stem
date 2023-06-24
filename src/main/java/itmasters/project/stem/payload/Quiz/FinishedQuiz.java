package itmasters.project.stem.payload.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinishedQuiz {

    private Integer id;

    private String userAnswer;
}
