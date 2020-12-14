package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-13 20:22:04
 **/
public class QuestionAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        int result = 0;
        //1.调用请求对象读取信息，得到新增信息内容
        title=req.getParameter("title");
        optionA=req.getParameter("optionA");
        optionB=req.getParameter("optionB");
        optionC=req.getParameter("optionC");
        optionD=req.getParameter("optionD");
        answer=req.getParameter("answer");
        //2.调用Dao对象将Insert命令推送到数据库中，并得到处理结果
        question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        result=dao.add(question,req);
        //3.通过请求转发，向Tomcat索要info.jsp将处理结果写入到响应体
        System.out.println(result);
        if(result==1){
            req.setAttribute("info","试题添加成功");
        }else {
            req.setAttribute("info","试题添加失败");
        }
        req.getRequestDispatcher("/info.jsp").forward(req,resp);
    }
}
