package snorlaxa.com.infosys.personnel.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author snorlaxa
 * @date 2019/11/20 9:25
 */
public class Dates {
    public interface Pattern {
        /**
         * 常规时间
         */
        String DATE="yyyy-MM-dd";
        String DATETIME="yyyy-MM-dd HH:mm:ss";
        String DATETIME_MM="yyyy-MM-dd HH:mm";
        String DATETIME_SSS="yyyy-MM-dd HH:mm:ss.SSS";
        String TIME="HH:mm";
        String TIME_SS="HH:mm:ss";

        /**
         * 系统时间
         */
        String SYS_DATE="yyyy/MM/dd";
        String SYS_DATETIME="yyyy/MM/dd HH:mm:ss";
        String SYS_DATETIME_MM="yyyy/MM/dd HH:mm";
        String SYS_DATETIME_SSS="yyyy/MM/dd HH:mm:ss.SSS";

        /**
         * 无连接符模式
         */
        String NONE_DATE="yyyyMMdd";
        String NONE_DATETIME="yyyyMMddHHmmss";
        String NONE_DATETIME_MM="yyyyMMddHHmm";
        String NONE_DATETIME_SSS="yyyyMMddHHmmssSSS";

    }

    public static final String DEFFAULT_PATTERN= Pattern.DATETIME;

    public static final String[] parseDate={
            Pattern.DATE,
            Pattern.DATETIME,
            Pattern.DATETIME_MM,
            Pattern.DATETIME_SSS,
            Pattern.SYS_DATE,
            Pattern.SYS_DATETIME,
            Pattern.SYS_DATETIME_SSS
    };
    /**
     * 日期格式化为字符串_自选格式
     * @param date Date类型
     * @param pattern 格式化模式
     * @return 格式化后的日期
     */
    public static String DateToString(Date date, String pattern){
        if(date==null){
            return null;
        }

        pattern= StringUtils.isNoneBlank(pattern)?pattern:DEFFAULT_PATTERN;
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 日期格式化为字符串_默认格式
     * @param date
     * @return 格式化后日期
     */
    public static String DateToString(Date date){
        return DateToString(date,DEFFAULT_PATTERN);
    }

    /**
     * 字符串转换为日期_默认格式
     * @param date
     * @return
     */
    public static Date StringToDate(String date){
        if(date==null){
            return null;
        }
        try {
            return DateUtils.parseDate(date,parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为日期_自选格式
     * @param date
     * @param pattern
     * @return
     */
    public static Date StringToDate(String date,String pattern){
        if(StringUtils.isBlank(date)){
            return null;
        }
        String[] pats= StringUtils.isNotBlank(pattern)?new String[]{pattern}:parseDate;
        try {
            return DateUtils.parseDate(date,pats);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
