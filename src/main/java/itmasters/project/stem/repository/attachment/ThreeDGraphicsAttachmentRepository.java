package itmasters.project.stem.repository.attachment;

import itmasters.project.stem.entity.attachment.ThreeDGraphicsAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThreeDGraphicsAttachmentRepository extends JpaRepository<ThreeDGraphicsAttachment, Integer> {
    @Query("select th from three_d_graphics_attachment as th where th.threeDGraphics.id = :threeDGraphicId")
    Integer findThreeDGraphicsAttachmentByThreeDGraphicsId(Integer threeDGraphicId);
}