package itmasters.project.stem.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class LeaderBoardDTO {

    private String firstName;

    private String lastName;

    private Integer coins;

    public LeaderBoardDTO(
            String firstName,
            String lastName,
            Integer coins
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.coins = coins;
    }

}