package itmasters.project.stem.repository;

import itmasters.project.stem.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    @Query("select s from section as s where s.topic.id = :topicId")
    List<Section> getAllSectionByTopicId(Integer topicId);
}