package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 17:42
 */
@Data
public class AbilityParam {
    private String id;
    @NotEmpty(message = "技能名不能为空！")
    private String name;
    @NotEmpty(message = "类别不能为空！")
    private String classify;
}
