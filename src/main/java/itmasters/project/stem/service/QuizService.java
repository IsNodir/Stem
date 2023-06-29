package itmasters.project.stem.service;

import itmasters.project.stem.entity.*;
import itmasters.project.stem.payload.quiz.FinishedQuiz;
import itmasters.project.stem.payload.quiz.QuizDTO;
import itmasters.project.stem.payload.quiz.QuizEn;
import itmasters.project.stem.payload.quiz.QuizResultDTO;
import itmasters.project.stem.repository.QuizRepository;
import itmasters.project.stem.repository.SubjectRepository;
import itmasters.project.stem.repository.TopicProgressRepository;
import itmasters.project.stem.repository.TopicRepository;
import itmasters.project.stem.security.config.JwtService;
import itmasters.project.stem.security.user.User;
import itmasters.project.stem.security.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.BufferOverflowException;
import java.util.*;

@Service
@Slf4j
public class QuizService {

    private final QuizRepository quizRepository;
    private final TopicRepository topicRepository;
    private final TopicProgressRepository topicProgressRepository;
    private final JwtService jwtService;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public QuizService(
            QuizRepository quizRepository,
            TopicRepository topicRepository,
            TopicProgressRepository topicProgressRepository,
            JwtService jwtService,
            SubjectRepository subjectRepository,
            UserRepository userRepository
    ) {
        this.quizRepository = quizRepository;
        this.topicRepository = topicRepository;
        this.topicProgressRepository = topicProgressRepository;
        this.jwtService = jwtService;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Integer sectionId) {
        return quizRepository.findById(sectionId).orElseThrow();
    }

    public Map<String, Object> getQuizResult(
            Integer topicId,
            List<FinishedQuiz> finishedQuiz,
            String language,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);

        List<QuizResultDTO> quizResult;
        if (language.equals("uz")) {
            quizResult = quizRepository.getCorrectAnswersUz(topicId);
        } else if (language.equals("en")) {
            quizResult = quizRepository.getCorrectAnswersEn(topicId);
        } else {
            throw new RuntimeException();
        }

        int correctCounter = 0;
        for (int i = 0; i < quizResult.size(); i++) {
            for (int j = 0; j < finishedQuiz.size(); j++) {
                if (quizResult.get(i).getId().equals(finishedQuiz.get(j).getId())) {
                    if (quizResult.get(i).getCorrectAnswer().equals(finishedQuiz.get(j).getUserAnswer())) {
                        quizResult.get(i).setCorrect(true);
                        correctCounter++;
                    } else {
                        quizResult.get(i).setCorrect(false);
                    }
                }
            }
        }

        int result = (correctCounter / quizResult.size()) * 100;

        Integer subjectId = topicRepository.findSubjectByTopicId(topicId);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        Integer userId = userRepository.findIdByUsername(username);
        User user = userRepository.findUserByUserId(userId);
        if (result >= 80) {
            user.setCoins(user.getCoins() + result / 2);
        }
        List<Quiz> quizList = quizRepository.findByTopicId(topicId);

        TakenSubject takenSubject = new TakenSubject();
        takenSubject.setCompleted(false);
        takenSubject.setSubject(subject);
        takenSubject.setUser(user);

        TopicProgress topicProgress = new TopicProgress();
        topicProgress.setResult(result);
        topicProgress.setCompleted(result >= 80);
        topicProgress.setTopic(topicRepository.findById(topicId).orElseThrow());
        topicProgress.setQuiz(quizList);
        topicProgress.setTakenSubject(takenSubject);
        topicProgressRepository.save(topicProgress);

        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("answers", quizResult);
        returnResult.put("result", result);
        return returnResult;

    }

    public TopicProgress getQuizResultsAndSuggestTopics(Integer topicId) {

        topicProgressRepository.findByTopicId(topicId);
        TopicProgress topicProgress = new TopicProgress();

        List<Integer> failedSections = new ArrayList<>();
        if (topicProgress.getResult() <= 80) {
            failedSections.add(0);
        }
        System.out.println("failedSections = " + failedSections);
        return null;
    }

    public Quiz createQuiz(Integer topicId, QuizDTO quizDTO) {

        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }

        List<QuizEn> quizEn = quizRepository.getEnLanguage(optionalTopic.get().getId());
        System.out.println("quizEn.size() = " + quizEn.size());
        if (quizEn.size() > 5) {
            throw new BufferOverflowException();
        }

        Quiz quiz = new Quiz();
        quiz.setQuestionUz(quizDTO.getQuestionUz());
        quiz.setQuestionEn(quizDTO.getQuestionEn());
        quiz.setCorrectAnswerUz(quizDTO.getCorrectAnswerUz());
        quiz.setCorrectAnswerEn(quizDTO.getCorrectAnswerEn());
        quiz.setAnswer1Uz(quizDTO.getAnswer1Uz());
        quiz.setAnswer1En(quizDTO.getAnswer1En());
        quiz.setAnswer2Uz(quizDTO.getAnswer2Uz());
        quiz.setAnswer2En(quizDTO.getAnswer2En());
        quiz.setAnswer3Uz(quizDTO.getAnswer3Uz());
        quiz.setAnswer3En(quizDTO.getAnswer3En());
        quiz.setAnswer4Uz(quizDTO.getAnswer4Uz());
        quiz.setAnswer4En(quizDTO.getAnswer4En());
        quiz.setTopic(optionalTopic.get());

        return quizRepository.save(quiz);
    }

    public Object checkLanguage(Integer topicId, String language) {
        return language.equals("uz") ? quizRepository.getUzLanguage(topicId) :
                (language.equals("en") ? quizRepository.getEnLanguage(topicId) : null);
    }

    public Quiz updateQuiz(Integer quizId, QuizDTO quizDTO) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isEmpty()) {
            throw new RuntimeException();
        }
        Quiz updatedQuiz = optionalQuiz.get();
        updatedQuiz.setQuestionUz(quizDTO.getQuestionUz());
        updatedQuiz.setQuestionEn(quizDTO.getQuestionEn());
        updatedQuiz.setCorrectAnswerUz(quizDTO.getCorrectAnswerUz());
        updatedQuiz.setCorrectAnswerEn(quizDTO.getCorrectAnswerEn());
        updatedQuiz.setAnswer1Uz(quizDTO.getAnswer1Uz());
        updatedQuiz.setAnswer1En(quizDTO.getAnswer1En());
        updatedQuiz.setAnswer2Uz(quizDTO.getAnswer2Uz());
        updatedQuiz.setAnswer2En(quizDTO.getAnswer2En());
        updatedQuiz.setAnswer3Uz(quizDTO.getAnswer3Uz());
        updatedQuiz.setAnswer3En(quizDTO.getAnswer3En());
        updatedQuiz.setAnswer4Uz(quizDTO.getAnswer4Uz());
        updatedQuiz.setAnswer4En(quizDTO.getAnswer4En());
        return quizRepository.save(updatedQuiz);
    }

    public String deleteQuiz(Integer sectionId) {
        Optional<Quiz> optionalSection = quizRepository.findById(sectionId);
        if (optionalSection.isEmpty()) {
            throw new RuntimeException();
        }
        quizRepository.deleteById(optionalSection.get().getId());
        return "Quiz deleted successfully";
    }

    public Subject getQuizBySubjectId(Integer subjectId, String language) {
        return subjectRepository.findById(subjectId).orElseThrow();
//        return null;
    }

    public Topic getAllQuizByTopicId(Integer topicId, String language) {
        Topic topic = topicRepository.findById(topicId).orElseThrow();
        return topic;
    }

}