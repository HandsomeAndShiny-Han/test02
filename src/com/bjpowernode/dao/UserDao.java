package com.bjpowernode.dao;

import com.bjpowernode.entity.Users;
import com.bjpowernode.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-09 16:30:20
 **/
public class UserDao {
    private JdbcUtil util = new JdbcUtil();
    //用户的注册
    //JDBC规范中 Connection的创建与销毁最浪费时间
    public int add(Users user, HttpServletRequest req){
        String sql = "insert into users(userName,password,sex,email)"+
                "values(?,?,?,?)";
        int result =0;
        PreparedStatement ps =  util.createStatement(sql,req);

        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result= ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close(req);
        }
        return result;
    }

    public int add(Users user){
        String sql = "insert into users(userName,password,sex,email)"+
                "values(?,?,?,?)";
        int result =0;
        PreparedStatement ps =  util.createStatement(sql);

        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result= ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }
    //查询所有用户信息
    public List findAll(){
        String sql = "select * from users";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        List userList= new ArrayList();
        try {
            rs = ps.executeQuery();
            while (rs.next()){
                Integer userId = rs.getInt("userId");
                String  userName= rs.getString("userName");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                Users  users = new Users(userId,userName,password,sex,email);
                userList.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }return userList;
    }
    //根据用户编号删除用户信息
    public int delete(String userId){
        String sql= "delete from users where userId=?";
        int result = 0;
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setInt(1,Integer.valueOf(userId));
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }return result;

    }

    //根据用户编号 修改用户信息
    public  int update(Users user,int userId){
        String sql = "update users set userName=?,password=?,sex=?,email=? " +
                "where userId=?";
        int result = 0;
        PreparedStatement ps = util.createStatement(sql);
        try {

            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            ps.setInt(5, userId);
            result= ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return result;

    }

    //登录验证
    public int login(String userName,String password){
        String sql="select count(*) from users where userName=? and password=?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        int result = 0;
        try {
            ps.setString(1,userName);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
              result=  rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }
}
