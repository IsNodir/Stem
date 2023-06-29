package itmasters.project.stem.payload.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizEn {

    private Integer id;

    private String questionEn;

    private String answer1En;

    private String answer2En;

    private String answer3En;

    private String answer4En;

    public QuizEn(Integer id, String questionEn, String answer1En, String answer2En, String answer3En, String answer4En) {
        this.id = id;
        this.questionEn = questionEn;
        this.answer1En = answer1En;
        this.answer2En = answer2En;
        this.answer3En = answer3En;
        this.answer4En = answer4En;
    }
}