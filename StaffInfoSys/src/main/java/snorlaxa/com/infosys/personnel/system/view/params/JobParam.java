package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 18:01
 */
@Data
public class JobParam {
    private String id;
    @NotEmpty(message = "岗位名称不能为空！")
    private String name;
    private Integer number;
    @NotEmpty(message = "岗位要求不能为空！")
    private String requirement;
    private Integer currentNumber;
    private Integer salary;
    @NotEmpty(message = "部门id不能为空！")
    private String departmentId;
}
