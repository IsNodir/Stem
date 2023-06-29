package itmasters.project.stem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String questionUz;

    private String questionEn;

    private String correctAnswerUz;

    private String correctAnswerEn;

    private String answer1Uz;

    private String answer1En;

    private String answer2Uz;

    private String answer2En;

    private String answer3Uz;

    private String answer3En;

    private String answer4Uz;

    private String answer4En;

    @ManyToOne
    @JsonIgnore
    private Topic topic;

}