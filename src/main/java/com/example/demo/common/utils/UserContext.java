package com.example.demo.common.utils;

/**
 * @author CTL
 * <p>通过token解析用户信息类 </p>
 * 创建日期：2020-10-21 09:56
 */
public final class UserContext {
    private static final ThreadLocal<String> user = new ThreadLocal<String>();

    public static void add(String userName){
        user.set(userName);
    }

    public static  void remove(){
        user.remove();
    }
    /**
     * @return 当前登录用户的用户名
     */
    public static String getCurrentUserName(){
        return user.get();
    }
}
