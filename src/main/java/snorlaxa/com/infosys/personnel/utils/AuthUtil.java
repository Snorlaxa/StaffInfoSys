package snorlaxa.com.infosys.personnel.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/13 14:49
 */
public class AuthUtil {
    public static String getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }
}
