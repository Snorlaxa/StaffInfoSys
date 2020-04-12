package snorlaxa.com.infosys.personnel.system.view.vo;

import lombok.Data;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/4 20:34
 */
@Data
public class StaffVo {
    private String id;
    private String name;
    private Integer gender;
    private Integer age;
    private Integer degree;
    private Integer workingYears;
    private String interviewComments;
    private String city;
    private String jobName;
}
