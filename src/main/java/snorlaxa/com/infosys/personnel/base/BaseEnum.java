package snorlaxa.com.infosys.personnel.base;

/**
 * 用于描述编码的含义
 * @author snorlaxa
 * @date 2019/11/19 18:02
 */
public interface BaseEnum<K, V> {
    /**
     * 获取编码
     * @return 编码
     */
    K code();

    /**
     * 获取含义
     * @return 含义
     */
    V desc();
}
