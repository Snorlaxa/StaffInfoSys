package snorlaxa.com.infosys.personnel.system.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/16 22:10
 */
@Data
public class StaffPo {
    private String id;
    private String name;
    private int gender;
    private int age;
    private int degree;
    private String school;
    private int workingYears;
    private String eduExp;
    private String jobExp;
    private String projectExp;
    private String jobId;
    private int employStatus;
    private String interviewComments;
    private int interviewScore;
    private int probationScore;
    private String interviewers;
    private String resumePath;
    private String speciality;
    private int interviewTimes;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String jobName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @Override
    public boolean equals(Object object){
        if(object instanceof StaffPo){
            StaffPo staffPo = (StaffPo) object;
            return staffPo.getId() == id;
        }
        throw new IllegalArgumentException("This object is not StaffPo");
    }

    @Override
    public int hashCode(){
        int result = 17*id.hashCode();
        return result;
    }
}
