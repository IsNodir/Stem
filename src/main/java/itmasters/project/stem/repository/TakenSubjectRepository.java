package itmasters.project.stem.repository;

import itmasters.project.stem.entity.TakenSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TakenSubjectRepository extends JpaRepository<TakenSubject, Integer> {
    @Query("select t from taken_subject as t where t.user.id = :userId and t.isCompleted = false")
    List<TakenSubject> findByUserIdAndIsCompleted (Integer userId);
    @Query("select ts from taken_subject as ts where ts.user.id = :userId and ts.subject.id = :subjectId")
    boolean findByUserIdAndSubjectId(Integer userId, Integer subjectId);
}