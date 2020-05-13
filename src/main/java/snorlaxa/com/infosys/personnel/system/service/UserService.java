package snorlaxa.com.infosys.personnel.system.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import snorlaxa.com.infosys.personnel.system.po.UserPo;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 11:04
 */
public interface UserService extends UserDetailsService {
    String upsertUser(UserPo userPo);
}
