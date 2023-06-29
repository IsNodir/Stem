package itmasters.project.stem.payload.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class QuizUz {

    private Integer id;

    private String questionUz;

    private String answer1Uz;

    private String answer2Uz;

    private String answer3Uz;

    private String answer4Uz;

    public QuizUz(
            Integer id,
            String questionUz,
            String answer1Uz,
            String answer2Uz,
            String answer3Uz,
            String answer4Uz
    ) {
        this.id = id;
        this.questionUz = questionUz;
        this.answer1Uz = answer1Uz;
        this.answer2Uz = answer2Uz;
        this.answer3Uz = answer3Uz;
        this.answer4Uz = answer4Uz;
    }

}