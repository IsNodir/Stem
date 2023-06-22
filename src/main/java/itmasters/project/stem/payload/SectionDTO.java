package itmasters.project.stem.payload;

import itmasters.project.stem.entity.Topic;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    private String title;

    private String text;

    private String videoUrl;

    private Integer topic;

}