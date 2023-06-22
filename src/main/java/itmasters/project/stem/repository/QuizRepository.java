package itmasters.project.stem.repository;

import itmasters.project.stem.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}