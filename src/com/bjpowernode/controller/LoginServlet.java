package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String userName,password;
        UserDao dao = new UserDao();
        int result=0;
        //1. 调用请求对象对请求体使用 utf-8字符集进行重新编译
        req.setCharacterEncoding("utf-8");
        //2. 调用请求对象读取请求参数信息
        userName = req.getParameter("userName");
        password = req.getParameter("password");
        //3.调用Dao将查询验证信息推送到数据库服务器上
        result=dao.login(userName,password);
        System.out.println(result);
        //4.调用响应对象，根据验证结果将不同资源文件地址写入到响应头，交给浏览器
        if(result==1){//说明用户存在
            //在判定来访用户合法后 ，通过请求对象向Tomcat申请为当前用户创建一个Session
            HttpSession session=req.getSession();
            resp.sendRedirect("/myWeb/index.html");
        }else {
            resp.sendRedirect("/myWeb/login_error.html");

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
