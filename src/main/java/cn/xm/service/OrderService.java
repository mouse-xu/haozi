package cn.xm.service;

import cn.xm.dao.OrdersDao;
import cn.xm.dao.ProductDao;
import cn.xm.dao.ServiceProductDao;
import cn.xm.dao.ShoppingCarProductDao;
import cn.xm.entity.Orders;
import cn.xm.entity.Product;
import cn.xm.entity.ServiceProduct;
import cn.xm.util.JDBC_Util;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderService {
    OrdersDao od = new OrdersDao();
    ShoppingCarProductDao scpd = new ShoppingCarProductDao();
    ProductDao pd = new ProductDao();
    ServiceProductDao spd = new ServiceProductDao();
    //根据u_id获取所有订单信息
    public List<Orders> queryAll(Integer u_id,Integer orderState) throws Exception {
        List<Orders> list = new ArrayList<>();
        if(orderState==-1){
            list = od.queryAllOrders(u_id);
        }else{
            list = od.queryAllOrders(u_id, orderState);
        }
        if(list==null){
            throw new Exception("0");
        }
        return list ;
    }
    //插入一个订单
    public int insertOrders(Orders orders) throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateStr = sdf.format(date);
        orders.setOrderCreatTime(dateStr);

        Integer u_id = orders.getU_id();

        //根据u_id获取相应用户的所有购物车中选中商品信息
        List<Map<String,Object>> list = scpd.queryChooseProInfo(u_id);
        List<Map<String,Object>> list1 = scpd.queryChooseSerInfo(u_id);
        Connection conn = JDBC_Util.getConn();
        try{
            conn.setAutoCommit(false);
            od.insertOrders(orders,conn);

            scpd.deleteShoppingCar(u_id, conn);
            conn.commit();
            JDBC_Util.closeAll(conn,null,null);
            conn = null;
            ////获取订单编号,插入详细订单
            Integer o_id = od.queryMax0_id(u_id);
            insertOrderDetails(o_id,list,list1);
            return 1;
        }catch (Exception e){
            throw new Exception("0");
        }

    }
    public void insertOrderDetails(Integer o_id,List<Map<String,Object>> list,List<Map<String,Object>> list1 ) throws Exception {
        //主产品
        if(list==null){
            //没有查询到订单信息
            throw new Exception("0");
        }else {
            for(Map<String ,Object> map:list){
                int num = od.insertOrderDetails((Integer)map.get("count"),(Integer)map.get("p_id"),0,o_id);
                if(num!=1){
                    //插入订单详情失败
                    throw new Exception("0");
                }
            }
        }
        //服务产品
        if(list1==null){
            //没有查询到订单信息
            throw new Exception("0");
        }else {
            for(Map<String ,Object> map:list1){
                int num = od.insertOrderDetails((Integer)map.get("count"),0,(Integer)map.get("ser_id"),o_id);
                if(num!=1){
                    //插入订单详情失败
                    throw new Exception("0");
                }
            }
        }
    }
    //
    public List<Map<String,Object>> showOrderDetails (Integer o_id) throws Exception {
        List<Map<String,Object>> list = od.queryAllOrderDetails2(o_id);
//        System.out.println(list);
        if(list==null){
            //没有查询到指定订单
            throw new Exception("0");
        }
        for(Map<String,Object> map:list){
            Integer p_id = (Integer)map.get("p_id");
            Integer ser_id = (Integer)map.get("ser_id");
            if(p_id!=0){
                Product pro = pd.queryOne(p_id);
                map.put("image",pro.getImage());
                map.put("proName",pro.getP_name());
                map.put("proPrice",pro.getShop_price());
            }else{
                ServiceProduct sp = spd.queryOne(ser_id);
                map.put("image", sp.getSer_picture());
                map.put("proName",sp.getSer_name());
                map.put("proPrice",sp.getSer_priceNow());
            }
        }
        return list;
    }
    //查询订单
    public List<Orders> queryOrders(String argument,Integer u_id) throws Exception {
        //根据商品名搜索订单
        List<Product> list = new ArrayList<>();
        list = pd.queryPro(argument);
//        System.out.println("list:"+list);
        List<Map<String,Object>> list1 = new ArrayList<>();
        List<Map<String,Object>> listTemp = new ArrayList<>();
        if(list!=null){

            for (Product pro:list){
                listTemp = od.queryOrders(u_id,pro.getP_id());
                if(listTemp!=null){
                    for(Map<String,Object> map:listTemp){
                        list1.add(map);
                    }
                }
            }
//            System.out.println("list1:"+list1);
        }
        //根据商品编号搜索订单
        boolean flag = true;
        List<Map<String,Object>> list2 = new ArrayList<>();
        Integer p_id = -1;
        try {
             p_id = Integer.valueOf(argument);
            list2 = od.queryOrders(u_id,p_id);
//            System.out.println("list2:"+list2);
        } catch (NumberFormatException e) {
            flag = false;
        }
        //根据订单号搜索订单
        Integer o_id = -1;
        Orders orders = new Orders();
        if(flag){
             o_id = p_id;
            orders = od.queryOne(u_id, o_id);
//            System.out.println("orders:"+orders);
        }
        //将三种方式获得的订单号合并
        Set<Integer> set = new HashSet<>();
        if(list1.size()>0){
            for(Map<String,Object> map:list1){
                Integer o_id1 = (Integer)map.get("o_id");
                set.add(o_id1);
            }
        }
        if(list2.size()>0){
            for(Map<String,Object> map:list2){
                Integer o_id2 = (Integer)map.get("o_id");
                set.add(o_id2);
            }
        }
        if(orders!=null&&orders.getO_id()!=null){
            set.add(orders.getO_id());
        }
        if(set.size()<=0){
            //没有符合查询条件的订单
            throw new Exception("0");
        }
//        System.out.println("set:"+set);
        //根据订单号查询相应订单
        List<Orders> listResult = new ArrayList<>();
        for(Integer id:set){
            Orders order = od.queryOrder(id);
            listResult.add(order);
        }
        return listResult;
    }
    //订单取消后，重置订单包含的主商品数量
    public int storeReset(Integer o_id) throws Exception {
        //根据o_id查出此订单下所有主商品信息
        OrdersDao od = new OrdersDao();
        List<Map<String,Object>> list = od.queryAllOrderDetails2(o_id);
        Connection conn = JDBC_Util.getConn();
        conn.setAutoCommit(false);
        try {
            for(Map<String,Object>map:list){
                Integer p_id = (Integer)map.get("p_id");
                Integer count = (Integer)map.get("count");
//          System.out.println(p_id+" "+count);
                pd.updateCount(conn,p_id,count);
            }
            od.modifiedOrdersState(o_id);
            conn.commit();
        }catch (Exception e){
            conn.rollback();
           throw new Exception("0");
        }
        JDBC_Util.closeAll(conn, null, null);
        conn=null;
        return 1;
    }
    //查询已评价商品
    public List<Map<String,Object>> queryordercoms(String userName) throws Exception{
        List list = new ArrayList();
        list = od.queryordercom(userName);
        if(list!=null||list.size()>0){
            return list;
        }else{
            throw new Exception("无已评价商品");
        }
    }
    //查询待评价商品
    public List<Map<String,Object>> queryorderco(String userName) throws Exception{
        List list = new ArrayList();
        list = od.queryorderc(userName);
//        System.out.println(list);
        if(list!=null||list.size()>0){
            return list;
        }else{
            throw new Exception("无待评价商品");
        }
    }
    //变更订单评价状态
    public int changeComSta(Integer od_id) throws Exception{
        int result = 0;
        result = od.changeComState(JDBC_Util.getConn(),od_id);
        if(result<=0){
            throw new Exception("变更失败");
        }
        return result;
    }
    //确认收货订单信息修改
    public int sureGot(Integer o_id) throws Exception {
        Connection conn= JDBC_Util.getConn();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateStr = sdf.format(date);
        try {
            conn.setAutoCommit(false);
            //修改订单状态为已收货
           od.sureGot(conn,o_id,dateStr);
           //确认收货后修改详细订单中相关主产品的评论状态为未评论
            od.sureGot2(conn, o_id);
            conn.commit();
        } catch (Exception e) {
            System.out.println("确认收货订单修改出现错误！");
                conn.rollback();
            throw new Exception("0");
        }finally {
            JDBC_Util.closeAll(conn, null, null);
            conn = null;
        }
        return 1;
    }
    public static void main(String[] args) throws Exception {
      OrderService os = new OrderService();
        System.out.println(os.queryorderco("耗子"));
    }
}
