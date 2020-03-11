package com.cong.config;

import org.springframework.context.annotation.Configuration;


/**
 * 传进来的参数做的切换
 * @author DELL
 *
 */
@Configuration
public class DataSourceContextHolder {
	/**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "master";
    public static final String DEFAULT_SEC_DS = "slave";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }

//原文链接：https://blog.csdn.net/NeverSayCode/article/details/88871224
}
