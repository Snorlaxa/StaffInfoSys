package snorlaxa.com.infosys.personnel.exceptions;


/**
 * @author snorlaxa
 * @date 2019/11/20 17:24
 */
public class BaseException extends RuntimeException {
    /**
     * 错误编码
     */
    protected String code;
    public BaseException(){}

    public BaseException(String message){
        super(message);
    }

    public BaseException(String code, String message){
        super(message);
        this.code=code;
    }
}
