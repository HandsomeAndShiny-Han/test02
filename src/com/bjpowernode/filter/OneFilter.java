package com.bjpowernode.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-12 22:36:51
 **/
public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转换成子接口类型
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = null;
        //1.调用请求对象读取当前请求包中请求行中URL，了解用户访问的资源文件时谁
        String uri=req.getRequestURI();// 得到/网站名/资源文件名
        //  /myWeb/login.html or /myWeb/login or...
        //2.如果本次请求的资源文件 与登录相关 login相关文件 此时应该放行
        if(uri.indexOf("login")!=-1||"/myWeb/".equals(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.如果本次请求访问的是其他的资源文件，需要得到用户在服务端的HttpSession
        session = req.getSession(false);
        if(session!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //4.当程序走到这一步 说明是恶意访问行为 应当拒绝访问
        req.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);



        /*//1.拦截后，通过请求对象 向Tomcat索要当前用户的HttpSession
        HttpSession session =req.getSession(false);
        //2.判断来访用户身份合法性
        if(session==null){
           //HttpServletResponse resp =(HttpServletResponse)servletResponse;
           req.getRequestDispatcher("/login_error.html")
                   .forward(servletRequest,servletResponse);
           return;
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
*/
    }
}
