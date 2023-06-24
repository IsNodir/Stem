package itmasters.project.stem.payload.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizUz {

    private Integer id;

    private String questionUz;

    private String answer1Uz;

    private String answer2Uz;

    private String answer3Uz;

    private String answer4Uz;

}