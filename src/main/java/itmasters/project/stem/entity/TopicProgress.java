package itmasters.project.stem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itmasters.project.stem.payload.quiz.QuizResultDTO;
import itmasters.project.stem.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topic_progress")
public class TopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int result;

    private boolean isCompleted;

    private Integer userId;

    @ManyToOne
    private Topic topic;

    @ManyToMany
    private List<Quiz> quiz;

//    @ManyToOne
//    private TakenSubject takenSubject;

}