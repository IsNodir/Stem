package itmasters.project.stem.payload.takenSubject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TakenSubjectUz {

    private Integer id;

    private String isCompleted;

    private Consumer user;

    private CourseUz subject;

    public TakenSubjectUz(
            Integer id,
            Boolean isCompleted,
            Integer userId,
            String firstName,
            String lastName,
            Integer subjectId,
            int subject_coins,
            double subject_price,
            int subject_streak,
            Date subject_streakFirstDay,
            String subject_subjectNameUz
    ) {
        this.id = id;
        this.isCompleted = isCompleted ? "true" : "false";
        this.user = new Consumer(userId, firstName, lastName);
        this.subject = new CourseUz(subjectId, subject_coins, subject_price, subject_streak, subject_streakFirstDay, subject_subjectNameUz);
    }

}