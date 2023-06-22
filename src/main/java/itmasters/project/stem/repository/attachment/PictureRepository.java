package itmasters.project.stem.repository.attachment;

import itmasters.project.stem.entity.attachment.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    @Query("select p from picture as p where p.section.id = :sectionId")
    Integer findPictureBySectionId(Integer sectionId);
}