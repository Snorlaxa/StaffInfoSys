package snorlaxa.com.infosys.personnel.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 11:12
 */
@Data
public class SysUser {
    private String id;
    private String username;
    private String password;
    private String role;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}
