package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 18:14
 */
@Data
public class DepartmentParam {
    private String id;
    @NotEmpty(message = "部门名称不能为空！")
    private String name;
    private Integer staffNum;
}
