package com.bjpowernode.controller;

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
 * @create: 2020-11-16 15:25:22
 **/
public class ExamServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Question> questionList = null;
        int score=0;
        //1.从当前用户私人储物柜中 得到系统提供四道题目信息
        questionList=(List)session.getAttribute("key");
        //2.从当前请求包读取用户对于四道题目所给答案
        for(Question question :questionList){
            String answer = question.getAnswer();
            Integer questionId=question.getQuestionId();
            String userAnswer=req.getParameter("answer_"+questionId);
            //判分
            if(userAnswer.equals(answer)){
                score+=25;
            }
        }
        //3.判分
        //4.将分数写入request中，作为共享数据
        req.setAttribute("info","本次考试成绩："+score);
        //5.通过请求转发调用jsp将用户本次考试分数写入到响应体中
        req.getRequestDispatcher("info.jsp").forward(req,resp);

    }
}
