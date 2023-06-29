package itmasters.project.stem.payload.takenSubject;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Consumer {
    private Integer userId;
    private String firstName;
    private String lastName;
}