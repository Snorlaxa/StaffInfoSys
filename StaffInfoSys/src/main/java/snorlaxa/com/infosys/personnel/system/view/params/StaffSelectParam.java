package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/17 20:44
 */
@Data
public class StaffSelectParam {
    private String id;
    private String jobId;
    private String name;
    private Integer gender;
    private Integer age;
    private Integer degree;
    private String city;
    private Integer workingYears;
    private Integer employStatus;
    private Integer interviewTimes;
}
