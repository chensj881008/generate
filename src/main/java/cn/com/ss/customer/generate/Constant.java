package cn.com.ss.customer.generate;

import cn.com.ss.customer.generate.util.PropertiesLoader;

/**
 * @author chenshijie
 * @title 常量类
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate
 * @date 2018-05-22 22:50
 */
public class Constant {

    public static String PATH = PropertiesLoader.getProperty("config.path");

    public static String PREFIX = PropertiesLoader.getProperty("config.package");

    public static String DOMAIN_PACKAGE = PREFIX +"."+PropertiesLoader.getProperty("config.domain");

    public static String DAO_PACKAGE = PREFIX  +"."+PropertiesLoader.getProperty("config.dao");

    public static String DAOIMPL_PACKAGE = PREFIX +"."+PropertiesLoader.getProperty("config.dao") + ".impl";

    public static String MAPPER_PACKAGE = PropertiesLoader.getProperty("config.mapper");

    public static String SERVICE_PACKAGE = PREFIX  +"."+PropertiesLoader.getProperty("config.service");

    public static String SERVICEIMPL_PACKAGE = PREFIX +"."+PropertiesLoader.getProperty("config.serviceImpl");
}
