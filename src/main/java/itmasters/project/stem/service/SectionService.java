package itmasters.project.stem.service;

import itmasters.project.stem.entity.Section;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.entity.attachment.Picture;
import itmasters.project.stem.entity.attachment.PictureAttachment;
import itmasters.project.stem.entity.attachment.ThreeDGraphics;
import itmasters.project.stem.entity.attachment.ThreeDGraphicsAttachment;
import itmasters.project.stem.payload.section.SectionDTO;
import itmasters.project.stem.payload.section.SectionResponse;
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

    public List<Section> getAllSectionByTopicId(Integer topicId) {
        Optional<Topic> selectedTopicId = topicRepository.findById(topicId);
        if (selectedTopicId.isEmpty()) {
            throw new RuntimeException();
        }
        return sectionRepository.getAllSectionByTopicId(topicId);
    }

    public Section getSectionById(Integer sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow();
    }

    public SectionResponse createSection(Integer topicId, SectionDTO sectionDTO) throws IOException {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }
        Section section = new Section();
        section.setTitleUz(sectionDTO.getTitleUz());
        section.setTitleEn(sectionDTO.getTitleEn());
        section.setTextUz(sectionDTO.getTextUz());
        section.setTextEn(sectionDTO.getTextEn());
        section.setVideoUrl(sectionDTO.getVideoUrl());
        section.setPictureUrl(sectionDTO.getPictureUrl());
        section.setThreeDGraphicsUrl(sectionDTO.getThreeDGraphicsUrl());
        section.setTopic(optionalTopic.get());
        Section savedSection = sectionRepository.save(section);

//        Picture picture1 = new Picture();
//        picture1.setFileOriginalName(sectionDTO.getPicture().getOriginalFilename());
//        picture1.setContentType(sectionDTO.getPicture().getContentType());
//        picture1.setSize(sectionDTO.getPicture().getSize());
//        picture1.setSection(savedSection);
//        Picture savedPicture = pictureRepository.save(picture1);
//
//        PictureAttachment pictureAttachment = new PictureAttachment();
//        pictureAttachment.setBytes(sectionDTO.getPicture().getBytes());
//        pictureAttachment.setPicture(savedPicture);
//        pictureAttachmentRepository.save(pictureAttachment);
//
//        ThreeDGraphics threeDGraphics = new ThreeDGraphics();
//        threeDGraphics.setFileOriginalName(sectionDTO.getThreeDGraphics().getOriginalFilename());
//        threeDGraphics.setContentType(sectionDTO.getThreeDGraphics().getContentType());
//        threeDGraphics.setSize(sectionDTO.getThreeDGraphics().getSize());
//        threeDGraphics.setSection(savedSection);
//        ThreeDGraphics savedThreeDGraphics = threeDGraphicsRepository.save(threeDGraphics);
//
//        ThreeDGraphicsAttachment threeDGraphicsAttachment = new ThreeDGraphicsAttachment();
//        threeDGraphicsAttachment.setBytes(sectionDTO.getThreeDGraphics().getBytes());
//        threeDGraphicsAttachment.setThreeDGraphics(savedThreeDGraphics);
//        threeDGraphicsAttachmentRepository.save(threeDGraphicsAttachment);

//        Picture picture = pictureAttachmentRepository.getPictureAndPictureAttachmentByPictureId(picture1.getId());
        return SectionResponse.builder()
                .titleUz(section.getTitleUz())
                .titleEn(section.getTitleEn())
                .textUz(section.getTextUz())
                .textEn(section.getTextEn())
                .videoUrl(section.getVideoUrl())
                .topic(topicId)
                .pictureUrl(section.getPictureUrl())
                .threeDGraphicsUrl(section.getThreeDGraphicsUrl())
//                .picture(picture1)
//                .threeDGraphics(threeDGraphics)
                .build();
    }

    public Section updateSection(Integer sectionId, SectionDTO sectionDTO) throws IOException {
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if (optionalSection.isEmpty()) {
            throw new IOException();
        }
        Section updatedSection = optionalSection.get();
        updatedSection.setTitleUz(sectionDTO.getTitleUz());
        updatedSection.setTitleEn(sectionDTO.getTitleEn());
        updatedSection.setTextUz(sectionDTO.getTextUz());
        updatedSection.setTextEn(sectionDTO.getTextEn());
        updatedSection.setVideoUrl(sectionDTO.getVideoUrl());
        updatedSection.setPictureUrl(sectionDTO.getPictureUrl());
        updatedSection.setThreeDGraphicsUrl(sectionDTO.getThreeDGraphicsUrl());
        Section savedSection = sectionRepository.save(updatedSection);

//        Integer pictureBySectionId = pictureRepository.findPictureBySectionId(sectionId);
//        if (pictureBySectionId.equals(0)) {
//            throw new IOException();
//        }
//        Picture picture1 = pictureRepository.findById(pictureBySectionId).orElseThrow();
//        picture1.setFileOriginalName(sectionDTO.getPicture().getOriginalFilename());
//        picture1.setContentType(sectionDTO.getPicture().getContentType());
//        picture1.setSize(sectionDTO.getPicture().getSize());
//        picture1.setSection(savedSection);
//        Picture savedPicture = pictureRepository.save(picture1);
//
//        Integer pictureAttachmentId = pictureAttachmentRepository.findPictureAttachmentByPictureId(pictureBySectionId);
//        if (pictureAttachmentId.equals(0)) {
//            throw new IOException();
//        }
//        PictureAttachment pictureAttachment = pictureAttachmentRepository.findById(pictureAttachmentId).orElseThrow();
//        pictureAttachment.setBytes(sectionDTO.getPicture().getBytes());
//        pictureAttachment.setPicture(savedPicture);
//        pictureAttachmentRepository.save(pictureAttachment);
//
//        Integer threeDGraphicsBySectionId = threeDGraphicsRepository.findThreeDGraphicsBySectionId(sectionId);
//        if (threeDGraphicsBySectionId.equals(0)) {
//            throw new IOException();
//        }
//        ThreeDGraphics threeDGraphics = threeDGraphicsRepository.findById(threeDGraphicsBySectionId).orElseThrow();
//        threeDGraphics.setFileOriginalName(sectionDTO.getThreeDGraphics().getOriginalFilename());
//        threeDGraphics.setContentType(sectionDTO.getThreeDGraphics().getContentType());
//        threeDGraphics.setSize(sectionDTO.getThreeDGraphics().getSize());
//        threeDGraphics.setSection(savedSection);
//        ThreeDGraphics savedThreeDGraphics = threeDGraphicsRepository.save(threeDGraphics);
//
//        Integer threeDGraphicsAttachmentByThreeDGraphicsId =
//                threeDGraphicsAttachmentRepository.findThreeDGraphicsAttachmentByThreeDGraphicsId(threeDGraphicsBySectionId);
//        ThreeDGraphicsAttachment threeDGraphicsAttachment =
//                threeDGraphicsAttachmentRepository.findById(threeDGraphicsAttachmentByThreeDGraphicsId).orElseThrow();
//        threeDGraphicsAttachment.setBytes(sectionDTO.getThreeDGraphics().getBytes());
//        threeDGraphicsAttachment.setThreeDGraphics(savedThreeDGraphics);
//        threeDGraphicsAttachmentRepository.save(threeDGraphicsAttachment);

        return savedSection;
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