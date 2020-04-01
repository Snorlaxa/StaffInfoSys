package snorlaxa.com.infosys.personnel.utils;

import snorlaxa.com.infosys.personnel.base.Result;

/**
 * Result 工具类
 * @author snorlaxa
 * @date 2019/11/19 17:37
 */
public class Results {
    protected Results(){}

    /**
     * success
     * ==================================================================================================
     */
    public static Result getNewResult(){
        return new Result();
    }

    public static Result getNewResult(boolean success){
        return new Result(success);
    }

    public static Result success(){
        return new Result();//默认true
    }
    public static Result success(String msg){
        return new Result(true,null,msg);
    }

    public static Result success(String code,String msg){
        return new Result(true,code,msg);
    }

    public static Result successWithData(Object data){
        return new Result(true,null,null, data);
    }

    public static Result successWithData(Object data,String msg){
        return new Result(true,null,msg,data);
    }

    public static Result successWithData(Object data,String msg,String code){
        return new Result(true,code,msg,data);
    }

    public static Result successWithStatus(Integer status){
        return new Result(true,status);
    }

    public static Result successWithStatus(Integer status,String msg){
        return new Result(true, status,null,msg);
    }

    public static Result successWithStatus(Integer status,String msg,String code){
        return new Result(true, status,code,msg);
    }
    /**
     * fail
     * ==================================================================================================
     */

    public static Result fail(){
        return new Result(false);
    }

    public static Result fail(String msg){
        return new Result(false,null,msg);
    }

    public static Result fail(String code,String msg){
        return new Result(false,code,msg);
    }

    public static Result failWithStatus(Integer status){
        return new Result(false,status);
    }

    public static Result failWithStatus(Integer status,String msg){
        return new Result(false,status,null,msg);
    }

    public static Result failWithStatus(Integer status,String msg,String code){
        return new Result(false,status,code,msg);
    }

    public static Result failWithData(Object data){
        return new Result(false,null,null,data);
    }

    public static Result failWithData(Object data,String msg){
        return new Result(false,null,msg,data);
    }

    public static Result failWithData(Object data,String msg,String code){
        return new Result(false,code,msg,data);
    }
}
