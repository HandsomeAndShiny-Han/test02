package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String questionId;
        QuestionDao dao = new QuestionDao();
        int result=0;
        PrintWriter out =null;
        //1.[调用请求对象]读取请求头中的参数(用户编号)
        questionId = req.getParameter("questionId");
        //2.[调用Dao]将用户编号填充到delete命令并发送到数据库服务器
        result= dao.delete(questionId);
        //3.[调用响应对象]将处理结果以二进制写入到响应体中，交给浏览器
        resp.setContentType("text/html;charset=utf-8");
        out=resp.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>试题信息删除成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>试题信息删除失败</font>");
        }
    }
}
