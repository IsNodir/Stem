package itmasters.project.stem.repository;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query("select t.subject.id from topic as t where t.id = :topicId")
    Integer findSubjectByTopicId(Integer topicId);
}