package cn.xm.service;

import cn.xm.dao.OrdersDao;
import cn.xm.dao.ProductDao;
import cn.xm.dao.ServiceProductDao;
import cn.xm.dao.ShoppingCarProductDao;
import cn.xm.entity.Collect;
import cn.xm.entity.Product;
import cn.xm.entity.ShoppingCarProduct;
import cn.xm.util.JDBC_Util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductService {
    ShoppingCarProductDao scpd = new ShoppingCarProductDao();
    ServiceProductDao spd = new ServiceProductDao();
    ProductDao pd = new ProductDao();
    //查询指定u_id用户下购物车中的主商品相关信息
    public List<ShoppingCarProduct> queryAll(Integer u_id) throws Exception {
        List<ShoppingCarProduct> list = new ArrayList<>();
        list = scpd.queryAll(u_id);
        if(list==null||list.size()<=0){
            throw new Exception("1");
        }else{
            return list;
        }

    }
    //查询指定u_id用户购物车中所有主商品的具体信息
    public List<Map<String,Object>> querySomePro(Integer u_id) throws Exception {
        List<Map<String,Object>> list = new ArrayList<>();
        list =scpd.querySomePro(u_id);
//        System.out.println(list);
        if(list.size()<=0){
            throw new Exception("1");
        }
        return list;
    }
    //根据指定p_id查询其购物车中所有主商品附带服务商品信息
    public List<Map<String,Object>> querySomeSerPro(Integer p_id) throws Exception {
        List<Map<String,Object>> list = scpd.querySomeSerPro(p_id);
        if(list.size()<=0){
            throw new Exception("1");
        }
        return list;
    }
    //根据用户的u_id、p_id修改相应商品在购物车中的数量、状态
    public void modifiedInfoPro(Integer u_id,Integer p_id,Integer count) throws Exception {
        queryAndReset(p_id);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateStr = sdf.format(date);
        Integer count1 = scpd.queryCountPro(u_id, p_id);
        Integer state = scpd.queryStatePro(u_id, p_id);
        Integer countP = pd.queryCount(p_id);
        ShoppingCarProduct scp = scpd.queryOnePro(u_id,p_id);
        int num = 0;
        Connection conn = JDBC_Util.getConn();
        conn.setAutoCommit(false);
       try{
           if(scp==null){
               if(countP<count){
                   //库存不足
                   throw new Exception("1");
               }
               //修改商品库存
               pd.updateCount(conn,p_id,-(count));
               //插入购物车记录
               ShoppingCarProduct scp1 = new ShoppingCarProduct(u_id, p_id,0,count,1,dateStr);
               num = scpd.insertPro2(conn,scp1);
               if(num<=0){
                   //插入失败
                   throw new Exception("2");
               }
           }else{
               if(state==0){
//                System.out.println("状态0");
                   if(count>countP){
                       //库存不足
                       throw new Exception("1");
                   }
                   //修改商品库存
                   pd.updateCount(conn,p_id,-(count));
                   //插入购物车记录
                   num = scpd.modifiedInfoPro2(conn,u_id,p_id,count,1,dateStr);
                   if(num<=0){
                       //修改失败
                       throw new Exception("2");
                   }
               }else {
                   count = count1+count;
                   if(count>=5){
                       count = 5;
                   }
                   if(count>countP){
                       //库存不足
                       throw new Exception("1");
                   }
                   //修改商品库存
                   pd.updateCount(conn,p_id,-(count));
                   //插入购物车记录
                   num = scpd.modifiedInfoPro2(conn,u_id,p_id,count,1,dateStr);
                   if(num<=0){
                       //修改失败
                       throw new Exception("2");
                   }
               }
           }
           conn.commit();
       }catch (Exception e){
           System.out.println("修改失败！");
           conn.rollback();
       }finally {
           JDBC_Util.closeAll(conn, null, null);
           conn = null ;
       }
    }
    //根据用户的u_id、ser_id修改相应服务商品在购物车中的数量、状态
    public  void modifiedInfoSer(Integer u_id,Integer ser_id,Integer count) throws Exception {

        ShoppingCarProduct scp = scpd.queryOneSer(u_id,ser_id);
        int num = 0;
        if(scp==null){
//            System.out.println("进入null");
            ShoppingCarProduct scp1 = new ShoppingCarProduct(u_id, 0,ser_id,count,1);
            num = scpd.insertSer(scp1);
//            System.out.println("num:"+num);
            if(num<=0){
                System.out.println("插入失败");
                //插入失败
                throw new Exception("2");
            }
        }else{
            Integer count1 = scpd.queryCountSer(u_id, ser_id);
            Integer state = scpd.queryStateSer(u_id, ser_id);
            if(state==0){
//                System.out.println("服务产品state为0");
                num = scpd.modifiedInfoSer(u_id,ser_id,count,1);
                if(num<=0){
                    //修改失败
                    throw new Exception("2");
                }
            }else{
//                System.out.println("服务产品state为1");
                Integer count2 = count1+count;
//                System.out.println("count2:"+count2);
                num = scpd.modifiedInfoSer(u_id,ser_id,count2,1);
                if(num<=0){
                    //修改失败
                    throw new Exception("2");
                }
            }

        }
    }
    //系统判断所有用户购物车中指定p_id主商品的选中状态的时间
    public void queryAndReset(Integer p_id) throws SQLException {
        Date date = new Date();
        Date date1 =new Date(date.getTime()-60*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateStr = sdf.format(date1);
       List<ShoppingCarProduct> list = scpd.queryDeadPro(p_id,dateStr);
       Connection conn = JDBC_Util.getConn();
       conn.setAutoCommit(false);
       if(list!=null){
           try{
               Integer count = 0;
               for(ShoppingCarProduct scp:list){
                   scpd.updateDeadPro(conn,scp.getCar_id());
                   count+=scp.getCount();
               }
               System.out.println("count:"+count);
               pd.updateCount(conn,p_id,count);
               conn.commit();
           }catch (Exception e){
               conn.rollback();
           }finally {
               JDBC_Util.closeAll(conn, null, null);
               conn = null ;
           }
       }
    }
    //初始化购物车
    public void initializeShoppingCar(Integer u_id) throws SQLException {
           int num = 0;
           //获得指定u_id下状态为1的主商品的p_id和count
           List<Map<String,Object>> list = scpd.queryChooseProInfo(u_id);
           for(Map<String,Object> map:list){
               Integer p_id = (Integer) map.get("p_id");
               Integer count = (Integer) map.get("count");
               //将相应的数量加至相应的商品库存上
               pd.updateCount(p_id,count);

           }
           num =  scpd.initializePro2(u_id);
//           System.out.println("初始化主商品："+num);
           num = scpd.deleteSer(u_id);
//           System.out.println("初始化服务商品："+num);

    }
    //根据用户u_id查询其账户下购物车中所有已选中的结算物品
    public List<Map<String,Object>> queryChooseInfo(Integer u_id) throws Exception {
        List<Map<String,Object>> list = new ArrayList<>();
        list = scpd.queryChooseProInfo(u_id);
        if(list==null){
            //没有主商品
            throw new Exception("1");
        }else {
            List<Map<String,Object>> list1 = new ArrayList<>();
            list1 = scpd.queryChooseSerInfo(u_id);
            if(list1!=null){
                for(Map<String,Object> map:list1){
                    list.add(map);
                }
            }
            return list;
        }
    }
    //根据用户u_id查询其账户下购物车中所有产品信息
    public List<Map<String,Object>> queryShoppingCarInfo(Integer u_id) throws Exception {
        List<Map<String,Object>> list = new ArrayList<>();
        list = scpd.queryShoppingCarProInfo(u_id);
        if(list==null){
            //没有主商品
            throw new Exception("1");
        }else {
            List<Map<String,Object>> list1 = new ArrayList<>();
            list1 = scpd.queryShoppingCarSerInfo(u_id);
            if(list1!=null){
                for(Map<String,Object> map:list1){
                    list.add(map);
                }
            }
            return list;
        }
    }
    public int deleteShoppingCar(Integer car_id){
        int num = scpd.deleteShoppingCar(car_id);
        return num ;
    }
    //添加购物车
    public int addShoppingCar(Integer u_id,Integer p_id) throws Exception {
        ShoppingCarProduct scp = scpd.queryOnePro( u_id, p_id);
        Integer count1 = scpd.queryCountPro(u_id, p_id);
        int num = 0 ;
        if(scp!=null) {
            if(count1+1>=5){
                count1 = 5;
            }else{
                count1++;
            }
            num = scpd.modifiedCountPro2(u_id, p_id,count1);
        }else {
            num = scpd.addShoppingCar(u_id, p_id);
            if(num<=0){
                //插入新记录失败
                throw new Exception("0");
            }
        }
        return num;
    }
    //收藏商品
    public int collectProduct(Integer p_id,Integer u_id){
        List<Collect> list = new ArrayList<>();
        list=pd.queryOnePro2(p_id,u_id);

        //判断藏品是否已经存在
        if(list==null||list.size()<=0) {
            return pd.collectPro2(JDBC_Util.getConn(), p_id, u_id);
        }
        return 0;
    }
    //搜索收藏
    public List<Map<String,Object>> queryP(String userName) throws Exception{
        List list = new ArrayList();
        list = pd.queryCollection(userName);
        if(list==null||list.size()<=0){
            throw new Exception("没有收藏商品");
        }
        return list;
    }
    //删除收藏
    public int clearC(String p_name,String userName){
        int result=pd.clearColl(p_name,userName);
        if(result==1){
            return result;
        }else{
            return 0;
        }
    }
    //查找单个产品
    public List<Map<String,Object>> querypros(String p_name) throws Exception{
        List<Map<String,Object>> list = new ArrayList();
        list = pd.querypro(p_name);
        if(list.size()<=0||list==null){
            throw new Exception("未找到");
        }
        return list;
    }
    //首页商品展示(手机)
    public List<Map<String,Object>> queryAllPP() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllP();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页商品展示(家电热门)
    public List<Map<String,Object>> queryAllEE() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllE();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（电视）
    public List<Map<String,Object>> queryAllTV() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllT();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（电脑）
    public List<Map<String,Object>> queryAllCC() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllC();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（家居）
    public List<Map<String,Object>> queryAllJJ() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllJ();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（智能）
    public List<Map<String,Object>> queryAllZZ() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllZ();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（搭配）
    public List<Map<String,Object>> queryAllDD() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllD();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（出行）
    public List<Map<String,Object>> queryAllCx() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllCX();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（手机配件）
    public List<Map<String,Object>> queryAllSp() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllSP();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //首页展示（周边）
    public List<Map<String,Object>> queryAllZb() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        list = pd.queryAllZB();
        if(list.size()<=0||list==null){
            throw new Exception("出现错误");
        }
        return list;
    }
    //推荐商品
    public List<Product> chooseTen(Integer flag) throws Exception {
        List<Product> list = new ArrayList<>();
        if(flag == 1){
            list = pd.chooseTen(0);
        }else if(flag==2){
            list = pd.chooseTen(10);
        }
        if(list==null){
            throw new Exception("没有搜索到推荐商品");
        }else {
            return list;
        }
    }
    public static void main(String[] args) throws Exception {
        ProductService ps = new ProductService();
        ProductService scps = new ProductService();
//        scps.deleteShoppingCar(9);
//        System.out.println();
        ps.queryAndReset(5);
    }


}
