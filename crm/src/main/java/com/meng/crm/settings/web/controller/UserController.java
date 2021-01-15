package com.meng.crm.settings.web.controller;

import com.meng.crm.settings.domain.Users;
import com.meng.crm.settings.service.UserService;
import com.meng.crm.settings.service.impl.UserServiceImpl;
import com.meng.crm.utils.MD5Util;
import com.meng.crm.utils.PrintJson;
import com.meng.crm.utils.ServiceFactory;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path=request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            //xxx(request,response);
            login(request,response);
        }else if("/settings/user/xxx.do".equals(path)){
            //xxx(request,response);
        }
    }

    private void login(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入验证登录操作");
        String loginAct=request.getParameter("loginAct");
        String loginPwd=request.getParameter("loginPwd");
        //密码转换
        loginPwd=MD5Util.getMD5(loginPwd);
        //获取浏览器端ip地址
        String ip=request.getRemoteAddr();
        System.out.println("----------ip:"+ip);

        //代理类,代理实现类，返回接口
        UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());
        try{
            Users user=us.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);

            //运行此处表示成功,返回json
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            e.printStackTrace();
            String msg=e.getMessage();
            //返回json，表示登陆失败及其原因
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }






        

    }
}
