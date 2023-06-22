package itmasters.project.stem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private String question;

    private String correctAnswer;

    private String incorrectAnswer1;

    private String incorrectAnswer2;

    private String incorrectAnswer3;
}

