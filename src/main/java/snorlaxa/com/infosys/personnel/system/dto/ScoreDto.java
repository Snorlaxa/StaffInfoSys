package snorlaxa.com.infosys.personnel.system.dto;

import lombok.Data;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 0:37
 */
@Data
public class ScoreDto {
    private String id;
    private String name;
    private String classify;
    private Integer score;

    @Override
    public boolean equals(Object scoreDto) {
        if (scoreDto instanceof ScoreDto) {
            ScoreDto anther = (ScoreDto) scoreDto;
            return this.id.equals(anther.getId())||this.name.equals(anther.getName());
        }
        return false;
    }
}
