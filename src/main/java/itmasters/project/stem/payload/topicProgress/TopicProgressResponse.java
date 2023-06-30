package itmasters.project.stem.payload.topicProgress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicProgressResponse {

    private Integer result;

    private Integer userId;

    private boolean isCompleted;

}