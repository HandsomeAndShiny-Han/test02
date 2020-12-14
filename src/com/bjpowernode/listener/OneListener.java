package com.bjpowernode.listener;

import com.bjpowernode.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: untitledJDBC
 * @description:
 * @author: 玉树临风的寒
 * @create: 2020-11-12 18:32:40
 **/
public class OneListener implements ServletContextListener {
    //在Tomcat启动的时候 预先创建20个Connection,在userDao.add方法执行
    //时 将实现创建好的Connection交给add方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JdbcUtil  util = new JdbcUtil();
        Map map = new HashMap();
        for (int i = 0; i <20 ; i++) {
            Connection con =util.getCon();
            //System.out.println("在Http服务器启动时 创建了一个Connection"+con);
            map.put(con,true);//表示这个通道处于空闲状态，false表示通道正在被使用
        }
        //为了在http服务器运行期间时 一直都可以使用这20个connection，将connection
        //保存到全局作用域对象中
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1",map);
    }
    //在http服务器关闭的时候，将20个全局作用域对象Connection销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       ServletContext application= sce.getServletContext();
       Map map=(Map)application.getAttribute("key1");
       Iterator it =map.keySet().iterator();
       while (it.hasNext()){
          Connection con=(Connection)it.next();
          if (con!=null){
             // System.out.println(con+"已被销毁");
          }
       }
    }
}
