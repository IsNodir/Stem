package itmasters.project.stem.service;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.payload.SubjectDTO;
import itmasters.project.stem.repository.SubjectRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Integer subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Integer subjectId, SubjectDTO subjectDTO) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        Subject updatedSubject = optionalSubject.get();
        updatedSubject.setSubjectName(subjectDTO.getSubjectName());
        updatedSubject.setPrice(subjectDTO.getPrice());
        updatedSubject.setStreak(subjectDTO.getStreak());
        updatedSubject.setStreakFirstDay(subjectDTO.getStreakFirstDay());
        updatedSubject.setCoins(subjectDTO.getCoins());
        return subjectRepository.save(updatedSubject);
    }

    public String deleteSubject(Integer subjectId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        subjectRepository.deleteById(optionalSubject.get().getId());
        return "Subject deleted successfully";
    }

}
