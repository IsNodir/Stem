package itmasters.project.stem.service;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.entity.TakenSubject;
import itmasters.project.stem.payload.TakenSubjectDTO;
import itmasters.project.stem.repository.SubjectRepository;
import itmasters.project.stem.repository.TakenSubjectRepository;
import itmasters.project.stem.security.user.User;
import itmasters.project.stem.security.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TakenSubjectService {

    private final TakenSubjectRepository takenSubjectRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public TakenSubjectService(
            TakenSubjectRepository takenSubjectRepository,
            UserRepository userRepository,
            SubjectRepository subjectRepository
    ) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.takenSubjectRepository = takenSubjectRepository;
    }

    public List<TakenSubject> getAllTakenSubjects() {
        return takenSubjectRepository.findAll();
    }

    public List<TakenSubject> getCurrentTakenSubjects(Integer userId) {
        return takenSubjectRepository.findByUserIdAndIsCompleted(userId);
    }

    public TakenSubject getTakenSubjectById(Integer subjectId) {
        return takenSubjectRepository.findById(subjectId).orElseThrow();
    }

    public TakenSubject createTakenSubject(TakenSubjectDTO takenSubjectDTO) {
        User optionalUser = userRepository.findById(takenSubjectDTO.getUserId()).orElseThrow();
        Subject optionalSubject = subjectRepository.findById(takenSubjectDTO.getSubjectId()).orElseThrow();
        TakenSubject takenSubject = new TakenSubject();
        takenSubject.setCompleted(false);
        takenSubject.setUser(optionalUser);
        takenSubject.setSubject(optionalSubject);
        return takenSubjectRepository.save(takenSubject);
    }

    public TakenSubject updateTakenSubject(Integer takenSubjectId) {
        Optional<TakenSubject> optionalTakenSubject = takenSubjectRepository.findById(takenSubjectId);

        if (optionalTakenSubject.isEmpty()) {
            throw new RuntimeException();
        }

        TakenSubject updatedTakenSubject = optionalTakenSubject.get();
        updatedTakenSubject.setSubject(optionalTakenSubject.get().getSubject());
        updatedTakenSubject.setUser(optionalTakenSubject.get().getUser());
        updatedTakenSubject.setCompleted(true);

        return takenSubjectRepository.save(updatedTakenSubject);
    }

    public String deleteTakenSubject(Integer subjectId) {
        Optional<TakenSubject> optionalSubject = takenSubjectRepository.findById(subjectId);

        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }

        takenSubjectRepository.deleteById(optionalSubject.get().getId());

        return "Taken subject deleted successfully";
    }


}
