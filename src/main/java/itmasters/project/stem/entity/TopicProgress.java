package itmasters.project.stem.entity;

import itmasters.project.stem.payload.quiz.QuizResultDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topic_progress")
public class TopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int result;

    private boolean isCompleted;

    @ManyToOne
    private Topic topic;

    @ManyToMany
    private List<Quiz> quiz;

    @ManyToOne
    private TakenSubject takenSubject;

}