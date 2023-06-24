package itmasters.project.stem.service;

import itmasters.project.stem.entity.Quiz;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.payload.Quiz.FinishedQuiz;
import itmasters.project.stem.payload.Quiz.QuizDTO;
import itmasters.project.stem.payload.Quiz.QuizEn;
import itmasters.project.stem.payload.Quiz.QuizResult;
import itmasters.project.stem.repository.QuizRepository;
import itmasters.project.stem.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.BufferOverflowException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    QuizResult quizResult;

    public Map<String, Object> getQuizResult(Integer topicId, List<FinishedQuiz> finishedQuiz, String language) {

        List<QuizResult> quizResult;

        if(language.equals("uz")) {
            quizResult = quizRepository.getCorrectAnswersUz(topicId);
        } else if (language.equals("en")) {
            quizResult = quizRepository.getCorrectAnswersEn(topicId);
        } else {
            throw new RuntimeException();
        }

        double correctCounter = 0;

        for (int i = 0; i < quizResult.size(); i++) {

            for (int j = 0; j < finishedQuiz.size(); j++) {

                if (quizResult.get(i).getId() == finishedQuiz.get(j).getId()) {

                    if(quizResult.get(i).getCorrectAnswer().equals(finishedQuiz.get(j).getUserAnswer())) {
                        quizResult.get(i).setCorrect(true);
                        correctCounter++;
                    } else {
                        quizResult.get(i).setCorrect(false);
                    }
                }

            }

        }

        double result = correctCounter/quizResult.size();

        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("answers", quizResult);
        returnResult.put("result", result);
        return returnResult;
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
        return language.equals("uz") ? quizRepository.getUzLanguage(topicId) : (language.equals("en") ? quizRepository.getEnLanguage(topicId) : null);
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

}