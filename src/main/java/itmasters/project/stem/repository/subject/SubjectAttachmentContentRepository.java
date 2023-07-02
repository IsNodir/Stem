package itmasters.project.stem.repository.subject;

import itmasters.project.stem.entity.subject.SubjectAttachment;
import itmasters.project.stem.entity.subject.SubjectAttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectAttachmentContentRepository extends JpaRepository<SubjectAttachmentContent, Integer> {
}
