package itmasters.project.stem.service;

import itmasters.project.stem.entity.Quiz;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.payload.QuizDTO;
import itmasters.project.stem.repository.QuizRepository;
import itmasters.project.stem.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuizService {

    private final QuizRepository quizRepository;

    private final TopicRepository topicRepository;

    public QuizService(QuizRepository quizRepository, TopicRepository topicRepository) {
        this.quizRepository = quizRepository;
        this.topicRepository = topicRepository;
    }

    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Integer sectionId) {
        return quizRepository.findById(sectionId).orElseThrow();
    }

    public Quiz createQuiz(Integer topicId, QuizDTO quizDTO) {

        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException();
        }

        Quiz quiz = new Quiz();
        quiz.setQuestion(quizDTO.getQuestion());
        quiz.setCorrectAnswer(quizDTO.getCorrectAnswer());
        quiz.setIncorrectAnswer1(quizDTO.getIncorrectAnswer1());
        quiz.setIncorrectAnswer2(quizDTO.getIncorrectAnswer2());
        quiz.setIncorrectAnswer3(quizDTO.getIncorrectAnswer3());
        quiz.setTopic(optionalTopic.get());

        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Integer quizId, QuizDTO quizDTO) {

        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isEmpty()) {
            throw new RuntimeException();
        }

        Quiz updatedQuiz = optionalQuiz.get();
        updatedQuiz.setQuestion(quizDTO.getQuestion());
        updatedQuiz.setCorrectAnswer(quizDTO.getCorrectAnswer());
        updatedQuiz.setIncorrectAnswer1(quizDTO.getIncorrectAnswer1());
        updatedQuiz.setIncorrectAnswer2(quizDTO.getIncorrectAnswer2());
        updatedQuiz.setIncorrectAnswer3(quizDTO.getIncorrectAnswer3());

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
}
