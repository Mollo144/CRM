package com.meng.crm.web.filter;

import com.meng.crm.settings.domain.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断是否登录过得过滤器
        System.out.println("进入验证登录过滤器");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //不应该拦截
        String path=request.getServletPath();
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            //直接放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //验证登录
            HttpSession session=request.getSession();
            Users user= (Users) session.getAttribute("user");
            if(user!=null){
                //登陆过，直接进入，放行
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                //重定向到登录页
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }
    }
}
