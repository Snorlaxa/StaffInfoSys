package snorlaxa.com.infosys.personnel.utils;

import java.util.UUID;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/24 23:49
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
