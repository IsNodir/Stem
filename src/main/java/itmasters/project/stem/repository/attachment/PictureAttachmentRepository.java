package itmasters.project.stem.repository.attachment;

import itmasters.project.stem.entity.attachment.PictureAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PictureAttachmentRepository extends JpaRepository<PictureAttachment, Integer> {

    @Query("select pA from picture_attachment as pA where pA.picture.id = :pictureId")
    Integer findPictureAttachmentByPictureId(Integer pictureId);
}