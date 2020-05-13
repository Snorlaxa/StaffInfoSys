package snorlaxa.com.infosys.personnel.security;

import lombok.Data;

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
}
