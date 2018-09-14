package cn.xm.controller;


import cn.xm.dao.OrdersDao;
import cn.xm.entity.Orders;
import cn.xm.service.OrderService;
import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.security.auth.callback.CallbackHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdersServlet {
    OrderService os = new OrderService();
    //插入订单
    public void insertOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String u_idStr = request.getParameter("u_id");
        String r_idStr = request.getParameter("r_id");
        String orderPriceStr = request.getParameter("orderPrice");
//        System.out.println("u_idStr:"+u_idStr+" r_idStr:"+r_idStr+" orderPriceStr:"+orderPriceStr);
        try {
            Integer u_id = Integer.valueOf(u_idStr);
            Integer r_id = Integer.valueOf(r_idStr);
            Double orderPrice = Double.valueOf(orderPriceStr);
            Orders od = new Orders(u_id,r_id,orderPrice,0);
//            System.out.println("orders:"+od);
            int num = os.insertOrders(od);
            if(num==1){
                OrdersDao odd = new OrdersDao();
                num = odd.queryMax0_id(u_id);
            }
            response.getWriter().write(num+"");
        }catch (ClassCastException e){
            response.getWriter().write("0");
        }catch (Exception e) {
//            System.out.println(e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }
    //指定展示相应订单
    public void showOrdersInfo (HttpServletRequest request,HttpServletResponse response) throws IOException {
        String u_idStr = request.getParameter("u_id");
        String orderStateStr = request.getParameter("orderState");
//        System.out.println("u_idStr:"+u_idStr+" orderStateStr:"+orderStateStr);
        try {
            List<Orders> list = new ArrayList<>();
            Integer u_id = Integer.valueOf(u_idStr);
            Integer orderState = Integer.valueOf(orderStateStr);
            list = os.queryAll(u_id, orderState);
            String listStr = JSON.toJSONString(list);
            response.getWriter().write(listStr);
        }catch (ClassCastException e){
            response.getWriter().write("0");
        }catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //查询订单详情
    public void showOrderDetails (HttpServletRequest request,HttpServletResponse response) throws IOException {
        String o_idStr = request.getParameter("o_id");
//        System.out.println(o_idStr);
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            Integer o_id = Integer.valueOf(o_idStr);
            list = os.showOrderDetails(o_id);
//            System.out.println(list);
            String listStr = JSON.toJSONString(list);
            response.getWriter().write(listStr);
        }catch (ClassCastException e){
            response.getWriter().write("0");
//            System.out.println(0);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
//            System.out.println(e.getMessage());
        }
    }
    //输入条件查询订单
    public void queryOrdersInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String u_idStr = request.getParameter("u_id");
        String argument = request.getParameter("argument");
        List<Orders> list = new ArrayList<>();
        try {
            Integer u_id = Integer.valueOf(u_idStr);
            list = os.queryOrders(argument, u_id);
            String listStr = JSON.toJSONString(list);
            response.getWriter().write(listStr);
        } catch (ClassCastException e){
            response.getWriter().write("0");
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            response.getWriter().write(e.getMessage());
        }

    }
    //付款成功修改数据库状态
    public void modifiedOrdersState(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String o_idStr = request.getParameter("out_trade_no");
//        System.out.println("out_trade_no:"+o_idStr);
        Integer o_id = Integer.valueOf(o_idStr);
        OrdersDao od = new OrdersDao();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateStr = sdf.format(date);
//        System.out.println("dateStr:"+dateStr);
        int num = od.modifiedOrdersState(o_id,dateStr);
//        System.out.println("num:"+num);
        if(num==0){
            response.getWriter().write(num);
        }else{
            response.sendRedirect("http://localhost:8080/k_file.html");
        }
    }
    //取消订单
    public void cancelOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String o_idStr = request.getParameter("o_id");
        Integer o_id = Integer.valueOf(o_idStr);
//        System.out.println(o_id);
        try {
            int num = os.storeReset(o_id);
            response.getWriter().write(num+"");
        } catch (Exception e) {
           response.getWriter().write(e.getMessage());
        }
    }
    //搜索已评价产品
    public void queryordercomment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        List list = new ArrayList();
        try {
            list = os.queryordercoms(userName);
            response.getWriter().write(JSON.toJSONString(list));
        }catch (Exception e){
            response.getWriter().write(e.getMessage());
        }
    }
    //搜索待评价产品
    public void queryordercomment2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        List list = new ArrayList();
        try {
            list = os.queryorderco(userName);
            response.getWriter().write(JSON.toJSONString(list));
        }catch (Exception e){
            response.getWriter().write(e.getMessage());
        }
    }
    //变更评价状态
    public void queryP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("od_id");
        Integer od_id = Integer.valueOf(id);
        try {
            int result = os.changeComSta(od_id);
            response.getWriter().write(result+"");
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //确认收货更改订单相应信息
    public void sureGot(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String o_idStr = request.getParameter("o_id");
        try {
            Integer o_id = Integer.valueOf(o_idStr);
            int num = os.sureGot(o_id);
            response.getWriter().write(num+"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
                response.getWriter().write(e.getMessage());

        }
    }
}

