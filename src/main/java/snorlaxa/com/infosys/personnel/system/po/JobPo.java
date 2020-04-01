package snorlaxa.com.infosys.personnel.system.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/25 22:57
 */
@Data
public class JobPo {
    private String id;
    private String name;
    private Integer number;
    private String requirement;
    private Integer currentNumber;
    private Integer salary;
    private String departmentId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
