package itmasters.project.stem.payload.takenSubject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakenSubjectDTO {

    private boolean isCompleted = false;

    private Integer userId;

    private Integer subjectId;

}