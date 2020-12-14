package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class UserAddServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
            String userName,password,sex,email;
            UserDao dao = new UserDao();
            Users user = null;
            int result = 0;
            PrintWriter out = null;
            //1.[调用请求对象] 读取[请求体中的信息] 得到用户信息
        userName= req.getParameter("userName");
        password= req.getParameter("password");
        sex=req.getParameter("sex");
        email=req.getParameter("email");

            //2.[调用UserDao]将用户信息填充到INSERT命令并借助JDBC规范发送到数据库服务器
        user = new Users(null,userName,password,sex,email);
        Date startDate = new Date();
        result=dao.add(user,req);
        Date endDate = new Date();
        System.out.println("添加消耗时间="+(endDate.getTime()-startDate.getTime())+"毫秒");
            //3.[调用响应对象]将处理结果以二进制形式写入到响应体中
        resp.setContentType("text/html;charset=utf-8");
        out =resp.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
            //Tomcat赋值销毁[请求对象]和[响应对象]
            //Tomcat赋值将Http响应协议包推送到发起请求的浏览器上
            //浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
            //浏览器将编译后的结果 在窗口中展示给用户[结束]

    }
}
