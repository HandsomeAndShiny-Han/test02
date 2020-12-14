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
 * @create: 2020-11-13 22:41:59
 **/
public class QuestionFindByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String questionId;
        Question question = null;
        QuestionDao dao = new QuestionDao();
        //1.调用请求对象读取请求头中的参数信息 得到试题编号
        questionId =req.getParameter("questionId");
        //2.调用Dao调用查询命令得到这个试题编号对应的试题信息
        // System.out.println(questionId+":questionId");
         question=dao.findById(questionId);
        //3.调用question_update.jsp将我们的试题信息写入到响应体里面
        req.setAttribute("key",question);
        req.getRequestDispatcher("/question_Update.jsp").forward(req,resp);

    }
}
