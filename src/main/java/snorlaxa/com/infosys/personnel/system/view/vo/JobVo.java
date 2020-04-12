package snorlaxa.com.infosys.personnel.system.view.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: snorlaxa
 * @Date: 2020/4/10 17:34
 */
@Data
public class JobVo {
    private String id;
    private String name;
    private Integer number;
    private String requirement;
    private Integer currentNumber;
    private Integer salary;
    private String department;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
