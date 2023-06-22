package itmasters.project.stem.service;

import itmasters.project.stem.entity.Section;
import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.payload.SectionDTO;
import itmasters.project.stem.repository.SectionRepository;
import itmasters.project.stem.repository.SubjectRepository;
import itmasters.project.stem.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SectionService {

    private final TopicRepository topicRepository;
    private final SubjectRepository subjectRepository;
    private final SectionRepository sectionRepository;

    public SectionService(
            TopicRepository topicRepository,
            SubjectRepository subjectRepository,
            SectionRepository sectionRepository
    ) {
        this.topicRepository = topicRepository;
        this.subjectRepository = subjectRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getAllSection() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Integer sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow();
    }

    public Section createSection(Integer topicId, SectionDTO sectionDTO) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }
        Section section = new Section();
        section.setTitle(sectionDTO.getTitle());
        section.setText(sectionDTO.getText());
        section.setVideoUrl(sectionDTO.getVideoUrl());
        section.setTopic(optionalTopic.get());
        return sectionRepository.save(section);
    }

    public Section updateSection(Integer sectionId, SectionDTO sectionDTO) {
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if (optionalSection.isEmpty()) {
            throw new RuntimeException();
        }
        Section updatedSection = optionalSection.get();
        updatedSection.setTitle(sectionDTO.getTitle());
        updatedSection.setText(sectionDTO.getText());
        updatedSection.setVideoUrl(sectionDTO.getVideoUrl());
        return sectionRepository.save(updatedSection);
    }

    public String deleteSection(Integer sectionId) {
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if (optionalSection.isEmpty()) {
            throw new RuntimeException();
        }
        sectionRepository.deleteById(optionalSection.get().getId());
        return "Section deleted successfully";
    }

}