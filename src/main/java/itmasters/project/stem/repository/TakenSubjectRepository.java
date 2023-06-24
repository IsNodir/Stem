package itmasters.project.stem.repository;

import itmasters.project.stem.entity.Quiz;
import itmasters.project.stem.entity.TakenSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TakenSubjectRepository extends JpaRepository<TakenSubject, Integer> {

    /** DO NOT REMOVE */
    /** Should be implemented and used in TakenSubjectService in method getCurrentTakenSubjects() */
    @Query("select t from taken_subject as t where t.user.id = :userId and t.isCompleted = false")
    List<TakenSubject> findByUserIdAndIsCompleted (Integer userId);

}