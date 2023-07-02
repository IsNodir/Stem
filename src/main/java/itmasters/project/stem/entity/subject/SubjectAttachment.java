package itmasters.project.stem.entity.subject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subject_attachment")
public class SubjectAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileOriginalName;

    private long size;

    private String contentType;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "subjectAttachment")
    private SubjectAttachmentContent subjectAttachmentContent;

}