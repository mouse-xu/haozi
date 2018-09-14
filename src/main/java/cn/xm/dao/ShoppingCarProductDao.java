package cn.xm.dao;

import cn.xm.entity.Product;
import cn.xm.entity.ShoppingCarProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ShoppingCarProductDao extends BassDao{
    //查询指定u_id用户下购物车中的商品
    public List<ShoppingCarProduct> queryAll(Integer u_id) {
        String sql = "select * from ShoppingCarProduct where u_id = ?";
        return super.queryAll(ShoppingCarProduct.class,sql,u_id );
    }
    //查询指定u_id用户下购物车中的主商品相关信息
    public List<Map<String,Object>> querySomePro(Integer u_id) {
        String sql = "select car_id,p.p_id p_id,p_name,market_price,shop_price,image,count,state from ShoppingCarProduct s inner join product p on s.p_id = p.p_id where u_id = ?";
        return super.querySome(sql,u_id);
    }
    //查询指定u_id用户下购物车中对应商品种类伴随现行的服务产品相关信息
    public List<Map<String,Object>> querySomeSerPro (Integer p_id){
        String sql = "select ser_id,ser_name,ser_priceNow,ser_priceOld,ser_picture,ser_description from product p inner join serviceproduct s on p.c_id = s.c_id where ser_state=1 and p_id = ?";
        return super.querySome(sql,p_id);
    }
    //根据用户的u_id、p_id查询相应商品在购物车中的数量
    public Integer queryCountPro (Integer u_id,Integer p_id) throws SQLException {
        String sql = "select count from shoppingcarproduct where u_id = ? and p_id = ? ";
        return (Integer) super.queryField(sql,u_id,p_id);
    }
    //根据用户的u_id、p_id查询相应商品在购物车中的状态
    public Integer queryStatePro (Integer u_id,Integer p_id) throws SQLException {
        String sql = "select state from shoppingcarproduct where u_id = ? and p_id = ? ";
        return (Integer) super.queryField(sql,u_id,p_id);
    }
    //根据用户的u_id、p_id查询相应服务商品在购物车中的数量
    public Integer queryCountSer (Integer u_id,Integer ser_id) throws SQLException {
        String sql = "select count from shoppingcarproduct where u_id = ? and ser_id = ? ";
        return (Integer) super.queryField(sql,u_id,ser_id);
    }
    //根据用户的u_id、p_id查询相应服务商品在购物车中的状态
    public Integer queryStateSer (Integer u_id,Integer ser_id) throws SQLException {
        String sql = "select state from shoppingcarproduct where u_id = ? and ser_id = ? ";
        return (Integer) super.queryField(sql,u_id,ser_id);
    }

    //根据用户的u_id、p_id修改相应商品在购物车中的数量、状态
    public int modifiedInfoPro(Integer u_id,Integer p_id,Integer count,Integer state){
        String sql = "update shoppingcarproduct set count = ? ,state = ? where u_id = ? and p_id = ? ";
        return super.update(sql,count,state,u_id,p_id);
    }
    //根据用户的u_id、p_id修改相应商品在购物车中的数量、状态
    public int modifiedInfoPro2(Connection conn,Integer u_id,Integer p_id,Integer count,Integer state,String creatTime){
        String sql = "update shoppingcarproduct set count = ? ,state = ? ,creatTime = ?  where u_id = ? and p_id = ? ";
        return super.update(conn,sql,count,state,creatTime,u_id,p_id);
    }
    //点击添加购物车之后根据用户的u_id、p_id修改相应商品在购物车中的数量、状态
    public int modifiedCountPro(Integer u_id,Integer p_id){
        String sql = "update shoppingcarproduct set count = count+1  where u_id = ? and p_id = ? ";
        return super.update(sql,u_id,p_id);
    }
    //点击添加购物车之后根据用户的u_id、p_id修改相应商品在购物车中的数量、状态
    public int modifiedCountPro2(Integer u_id,Integer p_id,Integer count){
        String sql = "update shoppingcarproduct set count = ?  where u_id = ? and p_id = ? ";
        return super.update(sql,count,u_id,p_id);
    }
    //查询购物车中指定商品的假死状态的单子
    public List<ShoppingCarProduct> queryDeadPro( Integer p_id,String time){
        String sql = "select  * from shoppingcarproduct where  creatTime<? and state=1 and p_id= ? ";
        return super.queryAll(ShoppingCarProduct.class,sql,time,p_id);
    }
    //初始化购物车中一些假死状态的单子
    public int updateDeadPro(Connection conn,Integer car_id ){
        String sql = "update shoppingcarproduct set state = 0 ,creatTime = null where car_id =?";
        return super.update(conn,sql,car_id);
    }

    //根据用户的u_id、p_id修改相应服务商品在购物车中的数量、状态
    public int modifiedInfoSer(Integer u_id,Integer ser_id,Integer count,Integer state){
        String sql = "update shoppingcarproduct set count = ?,state = ? where u_id = ? and ser_id = ? ";
        return super.update(sql,count,state,u_id,ser_id);
    }
    //根据用户的u_id初始化相应商品在购物车中的状态
    public int initializePro(Integer u_id){
        String sql = "update shoppingcarproduct set state = 1  where u_id = ? and p_id!=0";
        return super.update(sql,u_id);
    }
    //根据用户的u_id初始化相应商品在购物车中的状态
    public int initializePro2(Integer u_id){
        String sql = "update shoppingcarproduct set state = 0  where u_id = ? and p_id!=0";
        return super.update(sql,u_id);
    }
    //根据用户的u_id删除相应服务商品在购物车中的记录
    public int deleteSer(Integer u_id){
        String sql = "delete from shoppingcarproduct  where u_id = ? and ser_id!=0";
        return super.update(sql,u_id);
    }
    //插入一条购物车主商品记录
    public int insertPro(ShoppingCarProduct scp){
        String sql = "insert into ShoppingCarProduct (u_id,p_id,count,state) values(?,?,?,?)";
        return super.update(sql,scp.getU_id(),scp.getP_id(),scp.getCount(),scp.getState());
    }
    //插入一条购物车主商品记录
    public int insertPro2(Connection conn,ShoppingCarProduct scp){
        String sql = "insert into ShoppingCarProduct (u_id,p_id,count,state,creatTime) values(?,?,?,?,?)";
        return super.update(conn,sql,scp.getU_id(),scp.getP_id(),scp.getCount(),scp.getState(),scp.getCreatTime());
    }
    //根据u_id,p_id查询是否有满足条件的商品
    public ShoppingCarProduct queryOnePro(Integer u_id,Integer p_id){
        String sql = "select * from shoppingCarProduct where u_id = ? and p_id = ?";
        return super.queryOne(ShoppingCarProduct.class, sql, u_id,p_id);
    }
    //根据u_id,ser_id查询是否有满足条件的商品
    public ShoppingCarProduct queryOneSer(Integer u_id,Integer ser_id){
        String sql = "select * from shoppingCarProduct where u_id = ? and ser_id = ?";
        return super.queryOne(ShoppingCarProduct.class, sql, u_id,ser_id);
    }
    //根据u_id获取所有已选中的购物车主商品
    public List<Map<String,Object>> queryChooseProInfo(Integer u_id){
        String sql = "select s.p_id p_id, p_name name,shop_price price,image,count from shoppingcarproduct s inner join product p on p.p_id= s.p_id where state = 1 and u_id = ?";
        return super.querySome(sql,u_id);
    }
    //根据u_id获取所有已选中的购物车服务商品
    public List<Map<String,Object>> queryChooseSerInfo(Integer u_id){
        String sql = "select s.ser_id,ser_name name,ser_priceNow price,ser_picture image,count from shoppingcarproduct s INNER join serviceproduct se on se.ser_id = s.ser_id where state = 1 and u_id = ?";
        return super.querySome(sql,u_id);
    }
    //根据u_id获取所有购物车中主商品
    public List<Map<String,Object>> queryShoppingCarProInfo(Integer u_id){
        String sql = "select car_id, p_name name,shop_price price,image,count from shoppingcarproduct s inner join product p on p.p_id= s.p_id where u_id = ?";
        return super.querySome(sql,u_id);
    }
    //根据u_id获取所有购物车中服务商品
    public List<Map<String,Object>> queryShoppingCarSerInfo(Integer u_id){
        String sql = "select car_id,ser_name name,ser_priceNow price,ser_picture image,count from shoppingcarproduct s INNER join serviceproduct se on se.ser_id = s.ser_id where  u_id = ?";
        return super.querySome(sql,u_id);
    }
    //根据商品的购物车car_id删除指定商品
    public int deleteShoppingCar(Integer car_id){
        String sql = "delete  from shoppingcarproduct where car_id = ?";
        return super.update(sql,car_id);
    }
    //插入一条购物车服务商品记录
    public int insertSer(ShoppingCarProduct scp){
        String sql = "insert into ShoppingCarProduct (u_id,ser_id,count,state) values(?,?,?,?)";
        return super.update(sql,scp.getU_id(),scp.getSer_id(),scp.getCount(),scp.getState());
    }
    //根据商品的p_id、用户u_id增加指定主商品
    public int addShoppingCar(Integer u_id,Integer p_id){
        String sql = "insert into shoppingcarproduct (u_id,p_id,count,state) values(?,?,1,1)";
        return super.update(sql,u_id,p_id);
    }
    //根据u_id删除所有购物车中此u_id下状态为1的商品
    public int deleteShoppingCar(Integer u_id , Connection conn){
        String  sql = "delete  from shoppingcarproduct where u_id  = ? and state =  1";
        return super.update(conn,sql, u_id);
    }

    public static void main(String[] args)  {
        ShoppingCarProductDao scpd = new ShoppingCarProductDao();
        System.out.println(scpd.queryDeadPro(5, "2018年09月13日 15:15:16"));
    }
}
