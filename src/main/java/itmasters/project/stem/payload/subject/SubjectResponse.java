package itmasters.project.stem.payload.subject;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {

    private Integer id;

    private String subjectNameEn;

    private int topicCount;

//    private byte[] subjectLogo;

}