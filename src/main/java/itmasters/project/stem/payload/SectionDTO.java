package itmasters.project.stem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    private String titleUz;

    private String titleEn;

    private String textUz;

    private String textEn;

    private String videoUrl;

    private Integer topic;

    private MultipartFile picture;

    private MultipartFile threeDGraphics;

}