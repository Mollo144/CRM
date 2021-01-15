package com.meng.settings.test;

import com.meng.crm.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
        //验证失效时间
        //失效时间
        /*String expireTime="2020-10-10 10:10:10";
        //当前时间
        String currentTime= DateTimeUtil.getSysTime();
        int count=expireTime.compareTo(currentTime);//正数没过期，负数过期
        System.out.println(count);*/

        //验证锁定
        /*String lockState="0";
        if ("0".equals(lockState)){
            System.out.println("账号已锁定");
        }*/

        //浏览器端的ip地址
        String ip="192.168.1.1";
        //允许访问的ip
        String allowips="192.168.1.1,192.168.1.2";
        if (allowips.contains(ip)){
            System.out.println("允许登录");
        }else {
            System.out.println("限制访问，请联系管理员");
        }

    }
}
