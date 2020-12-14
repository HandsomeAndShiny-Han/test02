package com.bjpowernode.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;


/**
 * 将 JDBC下相关对象创建 与销毁 功能封装到方法
 *  1. JDBC规范 开发步骤
 *      1 注册驱动
 *      2 连接数据库
 *      3 创建数据库操作对象
 *      4 执行sql
 *      5 操作返回结果集
 *      6 关闭资源
 */
public class JdbcUtil {
    private Connection con = null;
    private  PreparedStatement ps = null;
   //通过全局作用域对象得到Connection---------start
    //重载getCon方法
    public Connection getCon(HttpServletRequest req){
        //1.通过请求对象 得到全局作用域对象
       ServletContext application = req.getServletContext();
       //2.从全局作用域对象中 得到map
        Map map = (Map)application.getAttribute("key1");
        //3.从map中得到处于空闲状态的 Connection
       Iterator it= map.keySet().iterator();
       while (it.hasNext()){
           con =(Connection) it.next();
           boolean flag=(boolean)map.get(con);
           if(flag==true){
               map.put(con,false);
               break;
           }
       }return con;
    }

    public PreparedStatement createStatement(String sql,HttpServletRequest req){

        Connection con=getCon(req);
        try {
            //System.out.println(con);
            ps=con.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }
    public void close(HttpServletRequest req){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        ServletContext application = req.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        map.put(con,true);

    }

    //通过全局作用域对象得到Connection---------start
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Driver接口实现类被注册了");
    }
    //封装Connection对象创建细节
    public Connection getCon(){

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3309/bjpowernode",
                     "root","123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection对象创建失败了....");
        }
        return con;
    }
    //封装PreparedStatement对象创建细节
    public PreparedStatement createStatement(String sql){

        //Connection con=creatCon();
        try {
            ps=getCon().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }
    //封装PrepareStarement对象与Connection对象销毁细节
    public void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //多态  封装PrepareStarement对象与Connection对象与ResultSet对象销毁细节
    public void close(ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

}
