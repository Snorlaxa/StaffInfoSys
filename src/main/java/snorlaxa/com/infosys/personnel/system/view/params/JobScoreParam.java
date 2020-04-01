package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 16:38
 */
@Data
public class JobScoreParam {
    @NotEmpty(message = "JobID 不能为空")
    private String jobId;
    @NotEmpty(message = "AbilityID 不能为空")
    private String abilityId;
    private Integer score;
}
