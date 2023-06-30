package itmasters.project.stem.payload.quiz;

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