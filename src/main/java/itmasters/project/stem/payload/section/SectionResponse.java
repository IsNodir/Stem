package itmasters.project.stem.payload.section;

import itmasters.project.stem.entity.attachment.Picture;
import itmasters.project.stem.entity.attachment.ThreeDGraphics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponse {

    private String titleUz;

    private String titleEn;

    private String textUz;

    private String textEn;

    private String videoUrl;

    private Integer topic;

    private Picture picture;

    private ThreeDGraphics threeDGraphics;

}
