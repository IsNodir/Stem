package itmasters.project.stem.repository;

import itmasters.project.stem.entity.TopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicProgressRepository extends JpaRepository<TopicProgress, Integer> {

    @Query("select tp from topic_progress as tp where tp.topic.id = :topicId")
    TopicProgress findByTopicId(Integer topicId);
}