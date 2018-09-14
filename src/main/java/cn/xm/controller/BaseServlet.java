package cn.xm.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获得传递地址url
        String url = request.getRequestURI();
//        System.out.println("url:"+url);
        //获得相应Servlet类名
        String servletName = url.substring(1,2).toUpperCase()+url.substring(2,url.lastIndexOf("/"))+"Servlet";
//        System.out.println("控制层类名:"+servletName);
        //获得相应方法名
        String methodName = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
//        System.out.println("方法名:"+methodName);
        try {
            //获得Servlet类
            Class clazz = Class.forName("cn.xm.controller."+servletName);
            //根据类型创建一个对象
            Object object  = clazz.newInstance();
            //获得相应方法
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //执行相应方法
            method.invoke(object,request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
