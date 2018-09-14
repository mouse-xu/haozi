package cn.xm.dao;

import cn.xm.entity.Collect;
import cn.xm.entity.Product;
import cn.xm.util.JDBC_Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDao extends BassDao {
    //搜索商品
    public List<Map<String,Object>> queryProduct(String keyword,Integer index,Integer size) {
        List<Object> params = new ArrayList<>();
        String key = "%"+keyword+"%";
        String key2 = "%"+keyword+"%";
        String sql = "select * from product p \n" +
                "INNER JOIN product_image i ON\n" +
                "p.p_id = i.p_id\n" +
                "inner Join category c ON\n" +
                "p.c_id=c.c_id\n" +
                "where p.c_id in (\n" +
                "select a.c_id from product a\n" +
                "inner join category b\n" +
                "on a.c_id = b.c_id\n" +
                "where a.p_name like ?\n" +
                "GROUP BY c_id\n" +
                ")"+
                "OR p.c_id in (select c_id FROM\n" +
                "category where c_name like ?\n" +
                ")";
        sql+="limit ?,?";
        params.add(key);
        params.add(key2);
        params.add((index-1)*size);
        params.add(size);
        return super.querySome(sql,params.toArray());
    }
    //搜索商品按价格排序
    public List<Map<String,Object>> queryProPrice(String keyword,Integer index,Integer size){
        List<Object> params = new ArrayList<>();
        String key = "%"+keyword+"%";
        String key2 = "%"+keyword+"%";
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i ON\n" +
                "p.p_id= i.p_id\n" +
                "INNER JOIN category c ON\n" +
                "p.c_id = c.c_id\n" +
                "where p.c_id in (\n" +
                "select a.c_id from product a\n" +
                "inner join category b\n" +
                "on a.c_id = b.c_id\n" +
                "where a.p_name like ?\n" +
                "GROUP BY c_id\n" +
                ")\n" +
                "OR p.c_id in (select c_id FROM\n" +
                "category where c_name like ?\n" +
                ")"+
                "ORDER BY p.shop_price\n" +
                "DESC\n" +
                "limit ?,?";
        params.add(key);
        params.add(key2);
        params.add((index-1)*size);
        params.add(size);
        return super.querySome(sql,params.toArray());
    }
    //搜索商品按点赞排序
    public List<Map<String,Object>> queryProCom(String keyword,Integer index,Integer size){
        List<Object> params = new ArrayList<>();
        String key = "%"+keyword+"%";
        String key2 = "%"+keyword+"%";
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i ON\n" +
                "p.p_id= i.p_id\n" +
                "INNER JOIN category c ON\n" +
                "p.c_id = c.c_id\n" +
                "where p.c_id in (\n" +
                "select a.c_id from product a\n" +
                "inner join category b\n" +
                "on a.c_id = b.c_id\n" +
                "where a.p_name like ?\n" +
                "GROUP BY c_id\n" +
                ")\n" +
                "OR p.c_id in (select c_id FROM\n" +
                "category where c_name like ?\n" +
                ")"+
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "limit ?,?";
        params.add(key);
        params.add(key2);
        params.add((index-1)*size);
        params.add(size);
        return super.querySome(sql,params.toArray());
    }
    //获得商品总数
    public int getCount(String keyword){
        String key = "%"+keyword+"%";
        String key2 = "%"+keyword+"%";
        String sql = "select count(1)  c from product p \n" +
                "INNER JOIN product_image i ON\n" +
                "p.p_id = i.p_id\n" +
                "inner Join category c ON\n" +
                "p.c_id=c.c_id\n" +
                "where p.c_id in (\n" +
                "select a.c_id from product a\n" +
                "inner join category b\n" +
                "on a.c_id = b.c_id\n" +
                "where a.p_name like ?\n" +
                "GROUP BY c_id \n" +
                ")"+
                "OR p.c_id in (select c_id FROM\n" +
                "category where c_name like ?\n" +
                ")";

        Map<String,Object> map = super.querySome(sql,key,key2).get(0);
        return  Integer.valueOf(map.get("c").toString());
    }
    //根据商品ID获取指定商品详细信息------尤增加
    public Product queryProduct (Integer p_id){
        String sql = "select * from product where p_id = ?";
        return super.queryOne(Product.class,sql,p_id );
    }
    //根据商品ID获取指定商品库存量------尤增加
    public Integer queryCount (Integer p_id) {
        String sql = "select inventory from product where p_id = ?";
        return (Integer) super.queryField(sql,p_id );
    }
    //根据商品ID，修改指定商品库存量------尤增加
    public int updateCount (Integer p_id,Integer count)  {
        String sql = "update product set inventory = inventory+?  where p_id = ?";
        return  super.update(sql,count,p_id );
    }

    //根据商品ID，修改指定商品库存量------尤增加
    public int updateCount (Connection conn,Integer p_id, Integer count)  {
        String sql = "update product set inventory = inventory+?  where p_id = ?";
        return  super.update(conn,sql,count,p_id );
    }
    //根据商品ID，查询具体商品信息------尤增加
    public Product queryOne (Integer p_id) {
        String sql = "select * from product where p_id = ?";
        return  super.queryOne(Product.class, sql, p_id);
    }
    //根据商品ID，查询具体商品信息------尤增加
    public List<Product> queryPro (String argument) {
        String sql = "select * from product where p_name like ?";
        String str = "%"+argument+"%";
//        System.out.println(str);
        return super.queryAll(Product.class, sql, str);
    }
    //收藏商品
    public int collectPro(Connection conn,String p_name,String userName){
        String sql = "INSERT into collection\n" +
                "(p_id,u_id)\n" +
                "VALUES (\n" +
                "(select p_id from product\n" +
                "where p_name=?),\n" +
                "(select u_id from `user`\n" +
                "where userName=?)\n" +
                ")";
        return super.update(conn,sql,p_name,userName);
    }
    //收藏商品
    public int collectPro2(Connection conn,Integer p_id,Integer u_id){
        String sql = "INSERT into collection  (p_id,u_id) VALUES (?,?)";
        return super.update(conn,sql,p_id,u_id);
    }
    //查找收藏
    public List<Map<String,Object>> queryCollection(String userName){
        String sql = "select * from collection c\n" +
                "INNER JOIN product p\n" +
                "on c.p_id = p.p_id\n" +
                "INNER JOIN product_image i\n" +
                "on c.p_id = i.p_id\n" +
                "where u_id = (\n" +
                "select u_id from `user`\n" +
                "where userName=?\n" +
                ")";
        return super.querySome(sql,userName);
    }
    //删除收藏
    public int clearColl(String p_name,String userName){
        String sql = "DELETE from collection\n" +
                "where p_id = (\n" +
                "select p_id from product\n" +
                "where p_name = ?\n" +
                ")\n" +
                "and u_id = (\n" +
                "SELECT u_id from `user`\n" +
                "where userName = ?\n" +
                ")";
        return super.update(JDBC_Util.getConn(),sql,p_name,userName);
    }
    //查找单个收藏
    public List<Map<String,Object>> queryOnePro(String p_name,String userName){
        String sql = "select * from collection\n" +
                "where p_id = (\n" +
                "select p_id from product\n" +
                "where p_name = ?\n" +
                ")\n" +
                "and u_id = (\n" +
                "select u_id from `user`\n" +
                "where userName = ?\n" +
                ")";
        return super.querySome(sql,p_name,userName);
    }
    //查找单个商品
    public List<Map<String,Object>> querypro(String p_name){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "where p_name = ?";
        return super.querySome(sql,p_name);
    }
    //查找单个收藏2
    public List<Collect> queryOnePro2(Integer p_id,Integer u_id){
        String sql = "select * from collection where p_id = ? and u_id = ?";
        return super.queryOne(Collect.class,sql,p_id,u_id );
    }
    //查找收藏数量
    public Object queryCountCollection(Integer u_id){
        String sql = "select count(1) count from collection where u_id = ?";
        return super.queryObject(sql, "count", u_id);
    }
    //首页商品展示(手机)
    public List<Map<String,Object>> queryAllP(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id=1\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页商品展示(家电热门)
    public List<Map<String,Object>> queryAllE(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id IN (2,3,4,5,7,8)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示(家电影音)
    public List<Map<String,Object>> queryAllT(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id = 2\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示(电脑)
    public List<Map<String,Object>> queryAllC(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id = 3\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示（家居）
    public List<Map<String,Object>> queryAllJ(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id in (7,8,10,11,12,13)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示（智能）
    public List<Map<String,Object>> queryAllZ(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id in (11,12,13)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示（搭配）
    public List<Map<String,Object>> queryAllD(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id in (9)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示（出行）
    public List<Map<String,Object>> queryAllCX(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id in (14)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示（手机配件）
    public List<Map<String,Object>> queryAllSP(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id in (15)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    //首页展示（周边）
    public List<Map<String,Object>> queryAllZB(){
        String sql = "select * from product p\n" +
                "INNER JOIN product_image i\n" +
                "on p.p_id = i.p_id\n" +
                "INNER JOIN category c\n" +
                "on p.c_id = c.c_id\n" +
                "where p.c_id in (16)\n" +
                "ORDER BY p.comments\n" +
                "DESC\n" +
                "LIMIT 8";
        return super.querySome(sql);
    }
    public static void main(String[] args) {
        ProductDao dao = new ProductDao();

            System.out.println(dao.queryCountCollection(1030));

    }
//推荐商品
    public List<Product> chooseTen(Integer num) {
        String sql = "select * from product order by comments limit ? , 10";
        return super.queryAll(Product.class, sql, num);
    }
}
