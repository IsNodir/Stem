package itmasters.project.stem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itmasters.project.stem.entity.attachment.Picture;
import itmasters.project.stem.entity.attachment.ThreeDGraphics;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titleUz;

    private String titleEn;

    private String textUz;

    private String textEn;

    private String videoUrl;

    @ManyToOne
    @JsonIgnore
    private Topic topic;

    @OneToOne
    private Picture picture;

    @OneToOne
    private ThreeDGraphics threeDGraphics;

}