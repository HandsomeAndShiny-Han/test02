package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class UserUpdateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        String userId,userName,password,sex,email;
        UserDao dao = new UserDao();

        int result = 0;
        PrintWriter out = null;
            //1.调用请求对象 读取请求头中的参数
        userId=req.getParameter("userId");
        userName= req.getParameter("userName");
        password= req.getParameter("password");
        sex=req.getParameter("sex");
        email=req.getParameter("email");
        System.out.println(userId+userName+sex+email+password);
        //2.[调用Dao]将用户编号填充到update命令并发送到数据库服务器
        Users user=new Users(null,userName,password,sex,email);
        result=dao.update(user,Integer.parseInt(userId));
        //System.out.println(userId);
        //3.[调用响应对象]将处理结果以二进制写入到响应体中，交给浏览器
        resp.setContentType("text/html;charset=utf-8");
        out =resp.getWriter();
        //System.out.println(result);
        if(result==1){
            req.setAttribute("info","信息修改成功");
        }else {
            req.setAttribute("info","信息修改失败");
        }
        req.getRequestDispatcher("/info.jsp").forward(req,resp);
    }
        /*if(result==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息修改成功</font>");
        }*/
    }

