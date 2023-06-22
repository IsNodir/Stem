package itmasters.project.stem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import itmasters.project.stem.entity.Subject;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

    private String topicName;

    private String coins;

    private double quizResult;

    private Integer subjectId;

}