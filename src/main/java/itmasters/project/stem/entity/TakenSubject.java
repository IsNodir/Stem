package itmasters.project.stem.entity;

import itmasters.project.stem.security.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "taken_subject")
public class TakenSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isCompleted = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Subject subject;

}