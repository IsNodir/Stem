package itmasters.project.stem.repository;

import itmasters.project.stem.entity.TakenSubject;
import itmasters.project.stem.payload.takenSubject.TakenSubjectEn;
import itmasters.project.stem.payload.takenSubject.TakenSubjectUz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TakenSubjectRepository extends JpaRepository<TakenSubject, Integer> {
    @Query("select t from taken_subject as t where t.user.id = :userId and t.isCompleted = false")
    List<TakenSubject> findByUserIdAndIsCompleted (Integer userId);
    @Query("select ts from taken_subject as ts where ts.user.id = :userId and ts.subject.id = :subjectId")
    Optional<TakenSubject> findByUserIdAndSubjectId(Integer userId, Integer subjectId);
    @Query("select ts from taken_subject as ts where ts.user.id = :userId and ts.isCompleted = false")
    List<TakenSubject> findTakenSubjectsByUserId(Integer userId);
    @Query("select ts from taken_subject as ts where ts.user.id = :userId order by ts.id")
    List<TakenSubject> findAllTakenSubjectsByUserId(Integer userId);
    @Query("select t from taken_subject as t where t.user.id = :userId and t.isCompleted = true")
    List<TakenSubject> findCompletedSubjectsByUserId(Integer userId);
    @Query("select new itmasters.project.stem.payload.takenSubject.TakenSubjectUz(" +
            "ts.id, ts.isCompleted, ts.user.id, ts.user.firstName, ts.user.lastName, " +
            "ts.subject.id, ts.subject.coins, ts.subject.price, ts.subject.streak, " +
            "ts.subject.streakFirstDay, ts.subject.subjectNameUz) " +
            "from taken_subject as ts where ts.user.id = :userId order by ts.id")
    List<TakenSubjectUz> findAllTakenSubjectsByUserIdUz(Integer userId);
    @Query("select new itmasters.project.stem.payload.takenSubject.TakenSubjectEn(" +
            "ts.id, ts.isCompleted, ts.user.id, ts.user.firstName, ts.user.lastName, " +
            "ts.subject.id, ts.subject.coins, ts.subject.price, ts.subject.streak, " +
            "ts.subject.streakFirstDay, ts.subject.subjectNameEn) " +
            "from taken_subject as ts where ts.user.id = :userId order by ts.id")
    List<TakenSubjectEn> findAllTakenSubjectsByUserIdEn(Integer userId);
    @Query("select new itmasters.project.stem.payload.takenSubject.TakenSubjectUz(" +
            "ts.id, ts.isCompleted, ts.user.id, ts.user.firstName, ts.user.lastName, " +
            "ts.subject.id, ts.subject.coins, ts.subject.price, ts.subject.streak, " +
            "ts.subject.streakFirstDay, ts.subject.subjectNameUz) " +
            "from taken_subject as ts where ts.user.id = :userId and ts.isCompleted = true order by ts.id")
    List<TakenSubjectUz> findAllCompletedSubjectsByUserIdUz(Integer userId);
    @Query("select new itmasters.project.stem.payload.takenSubject.TakenSubjectEn(" +
            "ts.id, ts.isCompleted, ts.user.id, ts.user.firstName, ts.user.lastName, " +
            "ts.subject.id, ts.subject.coins, ts.subject.price, ts.subject.streak, " +
            "ts.subject.streakFirstDay, ts.subject.subjectNameEn) " +
            "from taken_subject as ts where ts.user.id = :userId and ts.isCompleted = true order by ts.id")
    List<TakenSubjectEn> findAllCompletedSubjectsByUserIdEn(Integer userId);

    @Query(nativeQuery = true, value = "select new itmasters.project.stem.payload.takenSubject.TakenSubjectUz(" +
            "ts.id                      as id," +
            "ts.isCompleted             as subject_completed," +
            "ts.user.id                 as user_id, " +
            "ts.user.firstName          as first_name," +
            "ts.user.lastName           as last_name," +
            "ts.subject.id              as subject_id," +
            "ts.subject.coins           as subject_coins," +
            "ts.subject.price           as subject_price," +
            "ts.subject.streak          as subject_streak," +
            "ts.subject.streakFirstDay  as subject_streak_first_day," +
            "ts.subject.subjectNameUz   as subject_name_uz)" +
            " from taken_subject as ts where ts.user.id = :userId order by ts.id")
    List<TakenSubjectUz> findAllTakenSubjectsByUserIdUz1(Integer userId);

}