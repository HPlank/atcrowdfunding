package com.atguigu.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartSystemListener implements ServletContextListener {
    /**
     * 在服务器启动时  创建application对象是需要执行此次方法
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.将项目上下下文路径（request.getContextPath()）放到application域中
        ServletContext application = servletContextEvent.getServletContext();
        String contexPath = application.getContextPath();
        application.setAttribute("APP_PATH",contexPath);
        System.out.println("APP_PATH");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
