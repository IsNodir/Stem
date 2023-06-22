package itmasters.project.stem.entity;

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
@Entity(name = "topic_course")
public class TopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int quizResult;

    private boolean isCompleted;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private TakenSubject takenSubject;

}