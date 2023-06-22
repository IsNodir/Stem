package itmasters.project.stem.service;

import itmasters.project.stem.entity.Section;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.entity.attachment.Picture;
import itmasters.project.stem.entity.attachment.PictureAttachment;
import itmasters.project.stem.entity.attachment.ThreeDGraphics;
import itmasters.project.stem.entity.attachment.ThreeDGraphicsAttachment;
import itmasters.project.stem.payload.SectionDTO;
import itmasters.project.stem.repository.SectionRepository;
import itmasters.project.stem.repository.TopicRepository;
import itmasters.project.stem.repository.attachment.PictureAttachmentRepository;
import itmasters.project.stem.repository.attachment.PictureRepository;
import itmasters.project.stem.repository.attachment.ThreeDGraphicsAttachmentRepository;
import itmasters.project.stem.repository.attachment.ThreeDGraphicsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SectionService {

    private final TopicRepository topicRepository;
    private final SectionRepository sectionRepository;
    private final PictureRepository pictureRepository;
    private final PictureAttachmentRepository pictureAttachmentRepository;
    private final ThreeDGraphicsRepository threeDGraphicsRepository;
    private final ThreeDGraphicsAttachmentRepository threeDGraphicsAttachmentRepository;

    public SectionService(
            TopicRepository topicRepository,
            SectionRepository sectionRepository,
            PictureRepository pictureRepository,
            PictureAttachmentRepository pictureAttachmentRepository,
            ThreeDGraphicsRepository threeDGraphicsRepository,
            ThreeDGraphicsAttachmentRepository threeDGraphicsAttachmentRepository
    ) {
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
        this.pictureRepository = pictureRepository;
        this.pictureAttachmentRepository = pictureAttachmentRepository;
        this.threeDGraphicsRepository = threeDGraphicsRepository;
        this.threeDGraphicsAttachmentRepository = threeDGraphicsAttachmentRepository;
    }

    public List<Section> getAllSection() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Integer sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow();
    }

    public Section createSection(Integer topicId, SectionDTO sectionDTO) throws IOException {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }
        Section section = new Section();
        section.setTitle(sectionDTO.getTitle());
        section.setText(sectionDTO.getText());
        section.setVideoUrl(sectionDTO.getVideoUrl());
        section.setTopic(optionalTopic.get());
        Section savedSection = sectionRepository.save(section);

        Picture picture1 = new Picture();
        picture1.setFileOriginalName(sectionDTO.getPicture().getOriginalFilename());
        picture1.setContentType(sectionDTO.getPicture().getContentType());
        picture1.setSize(sectionDTO.getPicture().getSize());
        picture1.setSection(savedSection);
        Picture savedPicture = pictureRepository.save(picture1);

        PictureAttachment pictureAttachment = new PictureAttachment();
        pictureAttachment.setBytes(sectionDTO.getPicture().getBytes());
        pictureAttachment.setPicture(savedPicture);
        pictureAttachmentRepository.save(pictureAttachment);

        ThreeDGraphics threeDGraphics = new ThreeDGraphics();
        threeDGraphics.setFileOriginalName(sectionDTO.getThreeDGraphics().getOriginalFilename());
        threeDGraphics.setContentType(sectionDTO.getThreeDGraphics().getContentType());
        threeDGraphics.setSize(sectionDTO.getThreeDGraphics().getSize());
        threeDGraphics.setSection(savedSection);
        ThreeDGraphics savedThreeDGraphics = threeDGraphicsRepository.save(threeDGraphics);

        ThreeDGraphicsAttachment threeDGraphicsAttachment = new ThreeDGraphicsAttachment();
        threeDGraphicsAttachment.setBytes(sectionDTO.getThreeDGraphics().getBytes());
        threeDGraphicsAttachment.setThreeDGraphics(savedThreeDGraphics);
        threeDGraphicsAttachmentRepository.save(threeDGraphicsAttachment);

        return savedSection;
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
        Section savedSection = sectionRepository.save(updatedSection);



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