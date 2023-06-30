package itmasters.project.stem.repository;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query("select t.subject.id from topic as t where t.id = :topicId")
    Integer findSubjectByTopicId(Integer topicId);
    @Query("select t from topic as t where t.subject.id = :subjectId")
    List<Topic> findAllTopicsBySubjectId(Integer subjectId);
    @Query("select t.topicNameUz from topic as t where t.id = :topicId")
    String findTopicNameUzByTopicId(Integer topicId);
    @Query("select t.topicNameEn from topic as t where t.id = :topicId")
    String findTopicNameEnByTopicId(Integer topicId);
}