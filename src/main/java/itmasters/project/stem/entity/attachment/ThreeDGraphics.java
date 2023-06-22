package itmasters.project.stem.entity.attachment;


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
@Entity(name = "three_d_graphics")
public class ThreeDGraphics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileOriginalName;

    private String contentType;

    private long size;

    @OneToOne
    private Section section;

}
