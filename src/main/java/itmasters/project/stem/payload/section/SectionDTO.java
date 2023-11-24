package itmasters.project.stem.payload.section;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    private String titleUz;

    private String titleEn;

    private String textUz;

    private String textEn;

    private String videoUrl;

    private Integer topic;

//    private MultipartFile picture;

//    private MultipartFile threeDGraphics;

    private String pictureUrl;

    private String threeDGraphicsUrl;

}