package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-13 21:51:47
 **/
public class QuestionFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        //1.调用Dao推送查询命令 得到所有的试题
        List list=dao.findAll();
        //2.将得到试题添加到请求作用域对象 作为共享数据
        req.setAttribute("key",list);
        //3.通过请求转发 向Tomcat申请一个 questions.jsp文件将结果和html标签写入响应体中
        req.getRequestDispatcher("/questions.jsp").forward(req,resp);


    }
}
