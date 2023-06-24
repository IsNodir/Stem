package itmasters.project.stem.payload.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private String questionUz;

    private String questionEn;

    private String correctAnswerUz;

    private String correctAnswerEn;

    private String answer1Uz;

    private String answer1En;

    private String answer2Uz;

    private String answer2En;

    private String answer3Uz;

    private String answer3En;

    private String answer4Uz;

    private String answer4En;

}