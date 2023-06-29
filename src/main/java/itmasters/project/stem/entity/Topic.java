package itmasters.project.stem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String topicNameUz;

    private String topicNameEn;

    private String coins;

    @ManyToOne
    @JsonIgnore
    private Subject subject;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Quiz> quiz;

}