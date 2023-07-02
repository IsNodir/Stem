package itmasters.project.stem.entity.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itmasters.project.stem.entity.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileOriginalName;

    private String contentType;

    private long size;

    @OneToOne
    @JsonIgnore
    private Section section;

    @OneToOne
    private PictureAttachment pictureAttachment;

}