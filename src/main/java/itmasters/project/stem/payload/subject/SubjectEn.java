package itmasters.project.stem.payload.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import itmasters.project.stem.payload.TopicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEn {

    private String subjectNameEn;

    private double price;

    private int streak;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date streakFirstDay;

    private int coins;

    private List<TopicDTO> topics;

}