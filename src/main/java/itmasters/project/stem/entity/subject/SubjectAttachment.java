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
//@NamedEntityGraph(name = "subject-with-attachment-and-content",
//        attributeNodes = @NamedAttributeNode("subjectAttachmentContent"))
public class SubjectAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileOriginalName;

    private long size;

    private String contentType;

    @OneToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;

    @OneToOne(mappedBy = "subjectAttachment")
    private SubjectAttachmentContent subjectAttachmentContent;

}