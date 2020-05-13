package snorlaxa.com.infosys.personnel.system.po;

import lombok.Data;


/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 11:25
 */
@Data
public class UserPo {
    private String id;
    private String username;
    private String password;
    private String role;
}
