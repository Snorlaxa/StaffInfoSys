package snorlaxa.com.infosys.personnel.system.dto;

import lombok.Data;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/17 20:10
 */
@Data
public class StaffSelectDto {
    private String  id;
    private String jobId;
    private String name;
    private Integer gender;
    private Integer degree;
    private Integer age;
    private String city;
    private Integer workingYears;
    private Integer employStatus;
    private Integer interviewTimes;
}
