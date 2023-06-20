package itmasters.project.stem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private String correctAnswer;

    private String incorrectAnswer1;

    private String incorrectAnswer2;

    private String incorrectAnswer3;

    @ManyToOne
    private Topic topic;

}
