package cn.xm.dao;

import cn.xm.entity.Orders;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrdersDao extends BassDao{
    //根据u_id查询所有订单信息
    public List<Orders> queryAll(Integer u_id){
        String sql = "select * from Orders where u_id = ?";
        return super.queryAll(Orders.class,sql, u_id);
    }
    //插入一个订单
    public int insertOrders(Orders orders ,Connection conn){
        String sql = "insert into orders (u_id,r_id,orderPrice,orderCreatTime,orderState,orderPayTime) values(?,?,?,?,?,?)";
        return super.update(conn,sql,orders.getU_id(),orders.getR_id(),orders.getOrderPrice(),orders.getOrderCreatTime(),orders.getOrderState(),orders.getOrderPayTime());
    }
    //插入订单详情
    public int insertOrderDetails(Integer count,Integer p_id,Integer ser_id,Integer o_id){
        String sql = "insert into order_details (od_count,p_id,ser_id,o_id) values(?,?,?,?)";
        return super.update(sql,count,p_id,ser_id,o_id);
    }
    //根据订单用户u_id，订单生成时间组合查询返回订单编号
    public Integer queryField(Integer u_id,String orderCreatTime)  {
        String sql = "select o_id from orders where u_id = ? and orderCreatTime = ?";
        return (Integer) super.queryField(sql,u_id,orderCreatTime);
    }
    //查询最近创建订单编号
    public Integer queryMax0_id()  {
        String sql = "select MAX(o_id) o_id from orders";
        return (Integer) super.queryField(sql);
    }
    //查询指定用户最近创建订单编号
    public Integer queryMax0_id(Integer u_id)  {
        String sql = "select MAX(o_id) o_id from orders where u_id =?";
        return (Integer) super.queryField(sql,u_id);
    }
    //根据u_id查询所有其账号下订单
    /**
     *需要查询的表，及其字段属性
     * orders表中：orderState、orderCreatTime、o_id、orderPrice
     * receiver表中：receiverName
     * receiver
     */
    public List<Orders> queryAllOrders(Integer u_id){
        String sql = "select * from orders where u_id = ? and orderState!=3 order by o_id desc";
        return super.queryAll(Orders.class, sql,u_id);
    }
    //根据u_id查询所有其账号下所有符合状态需求的订单
    public List<Orders> queryAllOrders(Integer u_id,Integer orderState ){
        String sql = "select * from orders where u_id = ? and orderState = ?  order by o_id desc";
        return super.queryAll(Orders.class, sql,u_id,orderState);
    }
    //根据o_id查询订单详情表中所有其订单下所有符合要求的订单明细
    public List<Orders> queryAllOrderDetails(Integer o_id){
        String sql = "select * from order_details where o_id = ? ";
        return super.queryAll(Orders.class, sql,o_id);
    }
    //根据o_id查询需要的信息
   public List<Map<String,Object>> queryAllOrderDetails2(Integer o_id){
       String sql = "select receiverName ,receiverPhone,receiverAddress,od_count count,od.p_id p_id,od.ser_id ser_id ,o.o_id o_id,o.orderState orderState,o.orderCreatTime orderCreatTime,o.orderPayTime orderPayTime,o.orderFinishTime orderFinishTime,o.orderPrice orderPrice from orders o inner join receiver r on r.r_id = o.r_id inner join order_details od on od.o_id = o.o_id  where od.o_id =?";
        return super.querySome(sql,o_id);
   }
   //根据o_id,u_id查询需要的信息
   public Orders queryOne(Integer u_id,Integer o_id){
       String sql = "select * from orders where u_id =? and o_id=?";
        return super.queryOne(Orders.class,sql,u_id,o_id);
   }

   //根据u_id查询所有待评价商品的数量
    public Object queryCountNoComment(Integer u_id){
        String sql = "select count(1) count from orders o inner join order_details od on o.o_id = od.o_id where u_id = ? and od.com_State = 1 and od.ser_id = 0";
        return  super.queryObject(sql,"count",u_id);
    }
    //根据u_id查询所有待评价商品的数量
    public List<Map<String,Object>> queryOrders(Integer u_id,Integer p_id){
        String sql = "select o.o_id o_id from orders o\n" +
                "inner join order_details od on od.o_id = o.o_id\n" +
                "where p_id = ? and u_id =?";
        return  super.querySome(sql,p_id,u_id);
    }
    //根据o_id获取相应订单对象
    public Orders queryOrder(Integer o_id){
        String sql = "select * from orders where o_id = ?";
        return super.queryOne(Orders.class, sql, o_id);
    }
    //修改订单状态
    public int modifiedOrdersState(Integer o_id,String dateStr) {
        String sql = "update orders set orderState = 1,orderPayTime = ? where o_id = ?  ";
        return super.update(sql,dateStr,o_id);
    }
    //修改订单状态
    public int modifiedOrdersState(Integer o_id) {
        String sql = "update orders set orderState = 3  where o_id = ?  ";
        return super.update(sql,o_id);
    }

    //查询订单里面已评价商品
    public List<Map<String,Object>> queryordercom(String userName){
        String sql = "select * from product p\n" +
                "INNER JOIN order_details o\n" +
                "on p.p_id = o.p_id\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "where p.p_id = any(select p_id from order_details\n" +
                "where o_id = any(select o_id from orders\n" +
                "where u_id = (select u_id from `user`\n" +
                "where userName = ?\n" +
                ")\n" +
                ")\n" +
                ") and o.com_State = 2";
        return super.querySome(sql,userName);
    }
    //查询订单里面未评价商品
    public List<Map<String,Object>> queryorderc(String userName){
        String sql = "select * from product p\n" +
                "INNER JOIN order_details o\n" +
                "on p.p_id = o.p_id\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "where p.p_id in (select p_id from order_details\n" +
                "where o_id in (select o_id from orders\n" +
                "where u_id = (select u_id from `user`\n" +
                "where userName = ?\n" +
                ")\n" +
                ")\n" +
                ") and o.com_State = 1";
        return super.querySome(sql,userName);
    }
    //变更订单内的评论状态
    public int changeComState(Connection conn,Integer od_id){
        String sql = "UPDATE order_details SET\n" +
                "com_State = 2\n" +
                "where od_id = ?";
        return super.update(conn,sql,od_id);
    }
    //确认收货更改订单信息
    public int sureGot(Connection conn,Integer o_id,String dateStr){
        String sql = "update orders set orderState = 2 ,orderFinishTime = ? where o_id = ?";
        return super.update(conn, sql, dateStr,o_id);
    }
    //确认收货后修改详细订单中相关主产品的评论状态为未评论
    public int sureGot2(Connection conn,Integer o_id){
        String sql = "update order_details set com_State = 1 where o_id = ? and ser_id = 0";
        return super.update(conn, sql, o_id);
    }




    public static void main(String[] args) {
        OrdersDao od = new OrdersDao();
//        List<Map<String,Object>> list = od.queryOrders(1002, 1);
//        if(list.size()==0){
//            System.out.println(od.queryOrders(1002, 1));
//        }
        System.out.println(od.queryorderc("耗子"));

    }



}
