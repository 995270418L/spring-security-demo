package com.steve.utils;

/**
 * @Author: steve
 * @Date: Created in 15:08 2018/1/30
 * @Description:
 * @Modified By:
 */
public class SecurityUtils {

    /**
     * 获取属性的get方法
     * @param name
     * @return
     */
    public static String getMethodName(String name) {
        return "get" + toLowerCaseFirstOne(name);
    }

    public static String toLowerCaseFirstOne(String str) {
        if (null != str && !"".equals(str)) {
            return str.length() == 1 ? str.toUpperCase() : Character.toUpperCase(str.charAt(0)) + str.substring(1);
        } else {
            return "";
        }
    }
}
