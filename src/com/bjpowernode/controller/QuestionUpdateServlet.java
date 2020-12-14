package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-14 11:52:15
 **/
public class QuestionUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title, optionA, optionB, optionC, optionD, answer, questionId;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        int result = 0;
        //1.调用请求对象 读取当前请求头 参数信息
        title = req.getParameter("title");
        optionA = req.getParameter("optionA");
        optionB = req.getParameter("optionB");
        optionC = req.getParameter("optionC");
        optionD = req.getParameter("optionD");
        answer = req.getParameter("answer");
        questionId = req.getParameter("questionId");
        //2.调用Dao实现更新操作
        question = new Question(Integer.valueOf(questionId), title, optionA, optionB, optionC, optionD, answer);
        result=dao.update(question);
        //3.调用info.jsp将结果写入响应体中
        if (result == 1) {
            req.setAttribute("info", "试题更新成功");
        } else {
            req.setAttribute("info", "试题更新失败");
        }
        req.getRequestDispatcher("/info.jsp").forward(req, resp);
    }

}

