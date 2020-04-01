package snorlaxa.com.infosys.personnel.contants;

import snorlaxa.com.infosys.personnel.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author snorlaxa
 * @date 2019/11/19 18:06
 */
public enum BaseEnums implements BaseEnum<String,String> {
    SUCCESS("request.success","请求成功"),
    FAILURE("request.failure","请求失败"),
    OPERATION_SUCCESS("operation.success","操作成功"),
    OPERATION_FAILURE("operation.failure","操作失败"),
    ERROR("system.error","系统异常"),
    NOT_FOUND("not_found","请求资源不存在"),
    FORBIDDEN("forbidden","无访问权限"),
    VERSION_NOT_MATCH("record_not_exists_or_version_not_match","记录不存在或版本不匹配"),
    PARAMETER_NOT_NULL("parameter_not_be_null","参数不能为空");

    private String code;
    private String desc;
    private static Map<String,String> contantsMap =new HashMap<>();

    //使用map维护所有常量
    static {
        for(BaseEnum<String,String> e : BaseEnums.values()){
            contantsMap.put(e.code(),e.desc());
        }
    }

    BaseEnums(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    public static String desc(String code){
        return contantsMap.get(code);
    }
}
