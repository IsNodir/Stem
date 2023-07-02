package itmasters.project.stem.payload;

import itmasters.project.stem.payload.quiz.QuizDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

    private String topicNameUz;

    private String topicNameEn;

    private String coins;

    private Integer subjectId;

    private List<QuizDTO> quiz;

}