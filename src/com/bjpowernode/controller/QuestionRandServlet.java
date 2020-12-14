package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @program: EL工作空间
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-16 14:02:05
 **/
public class QuestionRandServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        List questionList = null;
        HttpSession session = req.getSession(false);
            //1.调用Dao对象随机从 question表中拿出四道题
        questionList=dao.findRand();
            //2.将四道题目添加到request中 作为共享数据
       session.setAttribute("key",questionList);
            //3.通过请求转发 申请调用exam.jsp将四道题目写入到响应体中
        req.getRequestDispatcher("/exam.jsp").forward(req,resp);
    }
}
