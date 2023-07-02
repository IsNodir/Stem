package itmasters.project.stem.entity.subject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subject_attachment_content")
public class SubjectAttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] bytes;

    @OneToOne
    @JoinColumn(name = "subject_attachment_id")
    private SubjectAttachment subjectAttachment;

}