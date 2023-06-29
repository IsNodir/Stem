package itmasters.project.stem.payload.takenSubject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TakenSubjectEn {

    private Integer id;

    private String isCompleted;

    private Consumer user;

    private CourseEn subject;

    public TakenSubjectEn(
            Integer id,
            Boolean isCompleted,
            Integer userId,
            String firstName,
            String lastName,
            Integer subjectId,
            int subject_coins,
            double subject_price,
            int subject_streak,
            Date subject_streak_first_day,
            String subject_subject_name_en
    ) {
        this.id = id;
        this.isCompleted = isCompleted ? "true" : "false";
        this.user = new Consumer(userId, firstName, lastName);
        this.subject = new CourseEn(subjectId, subject_coins, subject_price, subject_streak,
                subject_streak_first_day, subject_subject_name_en);
    }


}
