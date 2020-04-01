package snorlaxa.com.infosys.personnel.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 前端返回对象
 * @author snorlaxa
 * @date 2019/11/19 17:20
 */
@Slf4j
@Data
public class Result implements Serializable {
    private boolean success=true;
    private Integer status;
    private String code;
    private String msg;
    private Object data;
    public Result(){}
    public Result(boolean success){
        this.success=success;
    }
    public Result(boolean success,Integer status){
        this.success=success;
        this.status=status;
    }

    public Result(boolean success,String code,String msg){
        this.success=success;
        this.code=code;
        this.msg=msg;
    }
    public Result(boolean success,Integer status,String code,String msg){
        this.success=success;
        this.code=code;
        this.status=status;
        this.msg=msg;
    }
    public Result(boolean success,String code,String msg,Object data){
        this(success,code,msg);
        this.data=data;
    }

    public boolean isSuccess() {
        return success;
    }
}
