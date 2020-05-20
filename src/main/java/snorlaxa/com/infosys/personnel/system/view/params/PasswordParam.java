package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/20 23:05
 */
@Data
public class PasswordParam {
    private String username;
    private String password;
    private String newPassword;
}
