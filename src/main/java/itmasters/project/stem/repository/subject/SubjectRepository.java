package itmasters.project.stem.repository.subject;

import itmasters.project.stem.entity.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select s.subjectNameUz from subject as s where s.id = :subjectId")
    String findSubjectNameUzBySubjectId(Integer subjectId);
    @Query("select s.subjectNameEn from subject as s where s.id = :subjectId")
    String findSubjectNameEnBySubjectId(Integer subjectId);
}