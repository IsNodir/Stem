package itmasters.project.stem.service;

import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.entity.TakenSubject;
import itmasters.project.stem.payload.takenSubject.TakenSubjectDTO;
import itmasters.project.stem.payload.takenSubject.TakenSubjectUz;
import itmasters.project.stem.repository.SubjectRepository;
import itmasters.project.stem.repository.TakenSubjectRepository;
import itmasters.project.stem.security.config.JwtService;
import itmasters.project.stem.security.user.User;
import itmasters.project.stem.security.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtService jwtService;

    public TakenSubjectService(
            TakenSubjectRepository takenSubjectRepository,
            UserRepository userRepository,
            SubjectRepository subjectRepository,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.takenSubjectRepository = takenSubjectRepository;
        this.jwtService = jwtService;
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

    public Object createTakenSubject(TakenSubjectDTO takenSubjectDTO) {
        User user = userRepository.findById(takenSubjectDTO.getUserId()).orElseThrow();
        Subject subject = subjectRepository.findById(takenSubjectDTO.getSubjectId()).orElseThrow();
        Optional<TakenSubject> byUserIdAndSubjectId = takenSubjectRepository.findByUserIdAndSubjectId(user.getId(), subject.getId());
        if (byUserIdAndSubjectId.isPresent()) return "This subject is taken";
        List<TakenSubject> takenSubjectsByUser = takenSubjectRepository.findTakenSubjectsByUserId(user.getId());
        if (takenSubjectsByUser.size() >= 2) {
            return "You can take only two subject at the same time. After finished them, you can take another subject";
        }
        TakenSubject takenSubject = new TakenSubject();
        takenSubject.setCompleted(false);
        takenSubject.setUser(user);
        takenSubject.setSubject(subject);
        return takenSubjectRepository.save(takenSubject);
    }

    //TODO: check the course for completed
    public TakenSubject updateTakenSubject(Integer takenSubjectId) {
        Optional<TakenSubject> optionalTakenSubject = takenSubjectRepository.findById(takenSubjectId);
        if (optionalTakenSubject.isEmpty()) throw new RuntimeException();
        TakenSubject updatedTakenSubject = optionalTakenSubject.get();
        updatedTakenSubject.setSubject(optionalTakenSubject.get().getSubject());
        updatedTakenSubject.setUser(optionalTakenSubject.get().getUser());
        updatedTakenSubject.setCompleted(true);
        return takenSubjectRepository.save(updatedTakenSubject);
    }

    public String deleteTakenSubject(Integer subjectId) {
        Optional<TakenSubject> optionalSubject = takenSubjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) throw new RuntimeException();
        takenSubjectRepository.deleteById(optionalSubject.get().getId());
        return "Taken subject deleted successfully";
    }

    public Object getTakenSubjects(String language, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Integer userId = userRepository.findIdByUsername(username);
        return language.equals("uz") ? takenSubjectRepository.findAllTakenSubjectsByUserIdUz(userId) :
                language.equals("en") ? takenSubjectRepository.findAllTakenSubjectsByUserIdEn(userId) : null;
    }

    public List<TakenSubjectUz> getTakenSubjectsUz(String language, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Integer userId = userRepository.findIdByUsername(username);
        return takenSubjectRepository.findAllTakenSubjectsByUserIdUz(userId);
    }

    public List<TakenSubject> getCompletedSubjects(String language, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Integer userId = userRepository.findIdByUsername(username);
        return takenSubjectRepository.findCompletedSubjectsByUserId(userId);
    }

    public Object getAllCompletedSubjects(String language, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Integer userId = userRepository.findIdByUsername(username);
        return language.equals("uz") ? takenSubjectRepository.findAllCompletedSubjectsByUserIdUz(userId) :
                language.equals("en") ? takenSubjectRepository.findAllCompletedSubjectsByUserIdEn(userId) : null;
    }

}