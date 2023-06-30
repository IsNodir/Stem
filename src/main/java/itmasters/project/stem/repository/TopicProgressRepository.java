package itmasters.project.stem.repository;

import itmasters.project.stem.entity.TopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicProgressRepository extends JpaRepository<TopicProgress, Integer> {

    @Query("select tp from topic_progress as tp where tp.topic.id = :topicId")
    TopicProgress findByTopicId(Integer topicId);

    @Query("select tp from topic_progress as tp where tp.topic.id = :topicId and tp.userId = :userId and tp.isCompleted = true")
    Optional<TopicProgress> findByTopicIdAndUserId(Integer topicId, Integer userId);

    @Query("select tp from topic_progress as tp where tp.topic.id = :topicId and tp.userId = :userId")
    TopicProgress findByTopicIdAndUserIdForTakeResult(Integer topicId, Integer userId);

    @Query("select tp from topic_progress as tp where tp.topic.id = :topicId and tp.userId = :userId")
    TopicProgress findByTopicAndUser_Id(Integer topicId, Integer userId);


}