package itmasters.project.stem.entity.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import itmasters.project.stem.entity.Topic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String subjectNameUz;

    private String subjectNameEn;

    private double price;

    private int streak;

    private int coins;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date streakFirstDay;

    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    private SubjectAttachment subjectAttachment;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Topic> topics;

}