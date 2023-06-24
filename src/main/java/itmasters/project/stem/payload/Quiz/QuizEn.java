package itmasters.project.stem.payload.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizEn {

    private Integer id;

    private String questionEn;

    private String answer1En;

    private String answer2En;

    private String answer3En;

    private String answer4En;

}