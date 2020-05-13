package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.system.dao.UserDao;
import snorlaxa.com.infosys.personnel.system.po.UserPo;
import snorlaxa.com.infosys.personnel.system.service.UserService;
import snorlaxa.com.infosys.personnel.security.SysUser;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 11:05
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userDao.getUserByName(username);
//        log.info("================"+user.getUsername());
        if (user != null) {
            List<GrantedAuthority> authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new User(username,new BCryptPasswordEncoder().encode(user.getPassword()),authorities);
        } else {
            throw new UsernameNotFoundException("用户: " + username + " 不存在!");
        }
    }

    @Override
    public String upsertUser(UserPo userPo) {
        if(null == userPo.getId()){
            userPo.setId(UUIDUtil.getUUID());
        }
        userDao.insertUser(userPo);
        return userPo.getId();
    }
}
