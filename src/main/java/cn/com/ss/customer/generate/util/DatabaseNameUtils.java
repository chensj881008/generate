package cn.com.ss.customer.generate.util;

import cn.com.ss.customer.generate.domain.TableInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author chenshijie
 * @title 字段名称处理
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.util
 * @date 2018-05-22 22:45
 */
public class DatabaseNameUtils {
    /**
     * 数据库字段转换
     * @param dbInfo 字段名称
     * @param type 类型 0 表名 1 字段名
     * @return
     */
    public static String convertFromDBToJava(String dbInfo,int type){
        String[] splitNames = dbInfo.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splitNames.length; i++) {
            String value = splitNames[i];
            if(i == 0 && type == 0){
                sb.append(value.substring(0,1).toUpperCase() + value.substring(1).toLowerCase());
            }else  if(i == 0 && type == 1){
                sb.append(value.toLowerCase());
            }else{
                sb.append(value.substring(0,1).toUpperCase() + value.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

}
