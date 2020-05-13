package snorlaxa.com.infosys.personnel.system.dao;

import snorlaxa.com.infosys.personnel.system.po.UserPo;
import snorlaxa.com.infosys.personnel.security.SysUser;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 11:06
 */
public interface UserDao {
    SysUser getUserByName(String name);
    void insertUser(UserPo userPo);
}
