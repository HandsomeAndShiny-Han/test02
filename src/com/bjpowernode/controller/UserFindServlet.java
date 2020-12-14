package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class UserFindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        PrintWriter out = null;
        UserDao dao = new UserDao();
        //索要当前用户的HttpSession
       /* HttpSession session= req.getSession(false);
        if(session==null){
            resp.sendRedirect("/myWeb/login_error.html");
            return;
        }*/

        //提供服务
        //1. 调用Dao将查询命令推送到数据库服务器上 得到所有的用户信息[List]
        List<Users> userList = dao.findAll();
        //2. [调用响应对象]将用户信息结合<table>标签命令 以二进制的形式写入到响应体
        resp.setContentType("text/html;charset=utf-8");
        out = resp.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        for (Users users : userList) {
            out.print("<tr>");
            out.print("<td>" + users.getUserId() + "</td>");
            out.print("<td>" + users.getUserName() + "</td>");
            out.print("<td>" + users.getPassword() + "</td>");
            out.print("<td>" + users.getSex() + "</td>");
            out.print("<td>" + users.getEmail() + "</td>");
            out.print("<td><a href='/myWeb/user/delete?userId="+users.getUserId()
                    +"'>删除用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}