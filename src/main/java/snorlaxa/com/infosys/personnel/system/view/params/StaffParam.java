package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/24 21:18
 */
@Data
public class StaffParam {
    private String id;
    @NotEmpty(message = "姓名不能为空！")
    private String name;
    @NotEmpty(message = "性别不能为空！")
    private int gender;
    @NotEmpty(message = "年龄不能为空！")
    private int age;
    @NotEmpty(message = "学历不能为空！")
    private int degree;
    private String school;
    @NotEmpty(message = "工作年限不能为空！")
    private int workingYears;
    private String eduExp;
    private String jobExp;
    private String projectExp;
    @NotEmpty(message = "岗位id不能为空！")
    private String jobId;
    private int employStatus;
    private String interviewComments;
    private int interviewScore;
    private int probationScore;
    private String interviewers;
    @NotEmpty(message = "未上传简历！")
    private String resumePath;
    private String speciality;
    private int interviewTimes;
    @NotEmpty(message = "email不能为空！")
    private String email;
    @NotEmpty(message = "电话不能为空！")
    private String phone;
    @NotEmpty(message = "家庭地址不能为空！")
    private String address;
    @NotEmpty(message = "所在城市不能为空！")
    private String city;

    public StaffPo toStaff(){
        StaffPo staffPo = new StaffPo();
        if(StringUtils.isEmpty(id)){
            staffPo.setId(UUIDUtil.getUUID());
        }else{
            staffPo.setId(id);
        }
        staffPo.setName(name);
        staffPo.setGender(gender);
        staffPo.setAge(age);
        staffPo.setDegree(degree);
        staffPo.setSchool(school);
        staffPo.setWorkingYears(workingYears);
        staffPo.setEduExp(eduExp);
        staffPo.setJobExp(jobExp);
        staffPo.setProjectExp(projectExp);
        staffPo.setJobId(jobId);
        staffPo.setEmployStatus(employStatus);
        staffPo.setInterviewComments(interviewComments);
        staffPo.setInterviewScore(interviewScore);
        staffPo.setProbationScore(probationScore);
        staffPo.setInterviewers(interviewers);
        staffPo.setResumePath(resumePath);
        staffPo.setProbationScore(probationScore);
        staffPo.setSpeciality(speciality);
        staffPo.setInterviewTimes(interviewTimes);
        staffPo.setEmail(email);
        staffPo.setPhone(phone);
        staffPo.setAddress(address);
        staffPo.setCity(city);
        return staffPo;
    }
}
