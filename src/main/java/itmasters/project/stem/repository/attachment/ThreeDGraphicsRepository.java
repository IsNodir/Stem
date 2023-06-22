package itmasters.project.stem.repository.attachment;

import itmasters.project.stem.entity.attachment.ThreeDGraphics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThreeDGraphicsRepository extends JpaRepository<ThreeDGraphics, Integer> {
    @Query("select th from three_d_graphics as th where th.section.id = :sectionId")
    Integer findThreeDGraphicsBySectionId(Integer sectionId);
}