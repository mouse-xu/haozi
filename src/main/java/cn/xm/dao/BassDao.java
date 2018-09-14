package cn.xm.dao;

import cn.xm.entity.User;
import cn.xm.util.JDBC_Util;

import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BassDao {
    public Connection conn = null;
    public PreparedStatement pstm = null;
    public ResultSet rs = null;
    public ResultSetMetaData rsmd = null;

    /**
     * 通用单表所有查询
     * @param clazz:表对应的实体类类型
     * @param sql：需要执行的sql语句
     * @param obj：实体参数
     * @param <T>
     * @return
     */
    public <T>List<T> queryAll(Class clazz,String sql,Object... obj){
        List<T> list = new ArrayList<>();
        if(conn==null){
            conn = JDBC_Util.getConn();
        }
        try {
            pstm = conn.prepareStatement(sql);
            for(int i = 1 ;i<=obj.length;i++){
                pstm.setObject(i,obj[i-1]);
            }
            rs = pstm.executeQuery();
            rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while(rs.next()){
                Object object = clazz.newInstance();
                for(int i =0;i<count;i++){
                    String column = rsmd.getColumnLabel(i+1);
                    String setCol = "set"+column.substring(0,1).toUpperCase()+column.substring(1);
                    Field field = clazz.getDeclaredField(column);
                    Method method = clazz.getMethod(setCol,field.getType());
                    method.invoke(object,rs.getObject(column));
                }
                list.add((T)object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            JDBC_Util.closeAll(conn,pstm,rs);
            conn = null;
            pstm = null;
            rs = null;
        }
        return list;
    }

    /**
     * 通用单表查询
     * @param clazz：表对应的实体类类型
     * @param sql：需要执行的sql语句
     * @param obj：实体参数
     * @param <T>：表对应的实体类类型
     * @return
     */
    public <T>T queryOne(Class clazz,String sql,Object... obj){
        List<T> list = queryAll(clazz,sql,obj);
        if(list!=null&&list.size()>0){
           return list.get(0);
        }else{
            return null;
        }
    }

    /**
     * 通用多表联动查询
     * @param sql：查询语句
     * @param obj：参数列表
     * @return ：返回查询到的集合
     */
    public List<Map<String,Object>> querySome(String sql,Object...obj){
        List<Map<String,Object>> list = new ArrayList<>();
        if(conn==null){
            conn = JDBC_Util.getConn();
        }
        try {
            pstm = conn.prepareStatement(sql);
            for(int i = 1;i<=obj.length;i++){
                pstm.setObject(i,obj[i-1]);
            }
            rs = pstm.executeQuery();
            rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while(rs.next()){
                Map<String,Object> map = new HashMap<>();
                for(int i = 0;i<count;i++){
                    String column = rsmd.getColumnLabel(i+1);
                    map.put(column,rs.getObject(column));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC_Util.closeAll(conn,pstm,rs);
            conn = null ;
            pstm = null;
            rs = null;
        }
        return list;
    }

    /**
     * 通用修改数据--增、删、改
     * @param sql：查询语句
     * @param obj：参数列表
     * @return ：返回修改影响行数
     */
    public int update(String sql,Object... obj){
        if(conn==null){
            conn = JDBC_Util.getConn();
        }
        int result = this.update(conn,sql,obj);
        JDBC_Util.closeAll(conn,null,null);
        conn = null;
        return result;
    }

    /**
     * 修改数据--事务处理
     * @param conn：事务类调用此方法时，传递过来的conn连接
     * @param sql：查询语句
     * @param obj：参数列表
     * @return ：返回修改影响行数
     */
    public int update(Connection conn,String sql,Object...obj){
        int result = 0;
        try {
            pstm = conn.prepareStatement(sql);
            for(int i=1;i<=obj.length;i++){
                pstm.setObject(i,obj[i-1]);
            }
             result = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBC_Util.closeAll(null,pstm,null);
            pstm = null ;
        }
        return result;
    }
    //查询指定表中指定字段的值
    public Object queryField(String sql,Object...obj)  {
        if(conn == null){
            conn = JDBC_Util.getConn();
        }
        String column="";
        try {
            pstm = conn.prepareStatement(sql);
            for(int i=1; i<=obj.length; i++){
                pstm.setObject(i, obj[i-1]);
            }
            rs = pstm.executeQuery();
            rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while(rs.next()){
                column = rsmd.getColumnLabel(count);
                return  rs.getObject(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC_Util.closeAll(conn, pstm, rs);
            conn = null;
            pstm = null;
            rs = null;
        }
        return null;
    }
    //查询单个结果
    public Object queryObject(String sql,String str,Object... obj){
        if(conn == null){
            conn = JDBC_Util.getConn();
        };
        Object object= null;
        try {
            pstm = conn.prepareStatement(sql);
            for(int i=1;i<=obj.length;i++){
                pstm.setObject(i,obj[i-1] );
            }
            rs = pstm.executeQuery();
            while (rs.next()){
                object = rs.getObject(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC_Util.closeAll(conn, pstm, rs);
            conn= null;
            pstm=null;
            rs=null;
        }
        return object;
    }
    public static void main(String[] args) {
            BassDao bd = new BassDao();
            //查询所有
            String sql = "select * from user";
            List<User> list = bd.queryAll(User.class,sql);
            for(User user : list){
                System.out.println(user);
            }
            //查询具体
//            String sql = "select * from user where u_id = ?";
//            User user = bd.queryOne(User.class,sql,1001);
//            System.out.println(user);
            //多表联动查询
//            String sql = "select p.p_name,r.rate_name from product p inner join rate r on p.rate_id=r.rate_id ";
//            List<Map<String,Object>> list = bd.querySome(sql);
//            for(Map<String,Object> map:list){
//                System.out.println(map);
//            }
    }
}
