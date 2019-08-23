package cn.com.ss.customer.generate.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.util
 * @date 2018-05-25 22:45
 */
public class DateUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static  String getCurrentDate(){
        return format.format(new Date());
    }
}
