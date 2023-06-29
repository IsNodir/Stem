package itmasters.project.stem.payload.takenSubject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseEn {

    private Integer subjectId;

    private int subject_coins;

    private double subject_price;

    private int subject_streak;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date subject_streak_first_day;

    private String subject_subject_name_en;

}