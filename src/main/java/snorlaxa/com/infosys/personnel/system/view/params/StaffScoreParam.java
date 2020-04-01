package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 16:36
 */
@Data
public class StaffScoreParam {
    @NotEmpty(message = "StaffID 不能为空")
    private String staffId;
    @NotEmpty(message = "AbilityID 不能为空")
    private String abilityId;
    private Integer score;
}
