package itmasters.project.stem.repository;

import itmasters.project.stem.entity.Quiz;
import itmasters.project.stem.payload.quiz.QuizResultDTO;
import itmasters.project.stem.payload.quiz.QuizEn;
import itmasters.project.stem.payload.quiz.QuizUz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Query("select new itmasters.project.stem.payload.quiz.QuizUz(q.id, q.questionUz, q.answer1Uz, q.answer2Uz, q.answer3Uz, q.answer4Uz) from quiz as q where q.topic.id = :topicId")
    List<QuizUz> getUzLanguage(Integer topicId);

    @Query("select new itmasters.project.stem.payload.quiz.QuizEn(q.id, q.questionEn, q.answer1En, q.answer2En, q.answer3En, q.answer4En) from quiz as q where q.topic.id = :topicId")
    List<QuizEn> getEnLanguage(Integer topicId);

    @Query("select new itmasters.project.stem.payload.quiz.QuizResultDTO(q.id, q.correctAnswerUz) from quiz as q where q.topic.id = :topicId")
    List<QuizResultDTO> getCorrectAnswersUz(Integer topicId);

    @Query("select new itmasters.project.stem.payload.quiz.QuizResultDTO(q.id, q.correctAnswerEn) from quiz as q where q.topic.id = :topicId")
    List<QuizResultDTO> getCorrectAnswersEn(Integer topicId);

    @Query("select q from quiz as q where q.topic.id = :topicId")
    List<Quiz> findByTopicId(Integer topicId);
}