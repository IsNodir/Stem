package itmasters.project.stem.service;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.payload.TopicDTO;
import itmasters.project.stem.repository.SubjectRepository;
import itmasters.project.stem.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;
    private final SubjectRepository subjectRepository;

    public TopicService(
            TopicRepository topicRepository,
            SubjectRepository subjectRepository
    ) {
        this.topicRepository = topicRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Integer topicId) {
        return topicRepository.findById(topicId).orElseThrow();
    }

    public Topic createTopic(Integer subjectId, TopicDTO topicDTO) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        Topic topic = new Topic();
        topic.setTopicName(topicDTO.getTopicName());
        topic.setCoins(topicDTO.getCoins());
        topic.setQuizResult(topicDTO.getQuizResult());
        topic.setSubject(optionalSubject.get());
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Integer topicId, TopicDTO topicDTO) {

        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }
        Topic updatedTopic = optionalTopic.get();
        updatedTopic.setTopicName(topicDTO.getTopicName());
        updatedTopic.setCoins(topicDTO.getCoins());
        updatedTopic.setQuizResult(topicDTO.getQuizResult());
        return topicRepository.save(updatedTopic);
    }

    public String deleteTopic(Integer topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }
        topicRepository.deleteById(optionalTopic.get().getId());
        return "Topic deleted successfully";
    }

}