package itmasters.project.stem.payload.takenSubject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IsCompletedResponse {

    private String userName;

    private String subjectName;

    private boolean isCompleted;

}
