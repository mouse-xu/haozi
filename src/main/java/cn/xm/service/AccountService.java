package cn.xm.service;

import cn.xm.dao.OrdersDao;
import cn.xm.dao.ProductDao;
import cn.xm.entity.Collect;
import cn.xm.entity.Orders;
import cn.xm.entity.Product;
import cn.xm.entity.User;
import cn.xm.dao.UserDao;
import cn.xm.util.JDBC_Util;
import cn.xm.util.PageUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AccountService {
    UserDao ud = new UserDao();
    ProductDao fd = new ProductDao();
    /**
     * 登陆
     */
    public User login(User user) throws Exception {
        User us = ud.queryOne(user);
        if(us==null){
            throw new Exception("账号不存在");
        }else if(!us.getPassWord().equals(user.getPassWord())){
            throw new Exception("账号或密码错误");
        }else{
            return us;
        }
    }

    /**
     * 注册
     */
    public User register1(User user) throws Exception {
        User us = ud.queryOne(user);
        if(us!=null){
            throw new Exception("此账号已存在");
        }else{
            return us;

        }
    }
    public User register(User user) throws Exception {
        User us = ud.queryOne(user);
        if(us!=null){
            throw new Exception("此账号已存在");
        }else{
           us =  ud.register(user);
            return us;
        }
    }
    /**
     * 修改账户信息
     */
    public int modified(User user1,User user2,String passWordOld) throws Exception {
         user1 = ud.queryOne(user1);
        if(user1==null){
            throw new Exception("请登录，再修改！");
        }
//        System.out.println("passWordOld:"+passWordOld);
        if(passWordOld!=""&&passWordOld!=null){
            String passWord = ud.queryPassWord(user1);
//            System.out.println(passWord);
            if(!passWord.equals(passWordOld)){
                //老密码错误
                throw new Exception("404");
            }
        }else {
            user2.setPassWord(null);
        }
        User us = null;
        user2.setU_id(user1.getU_id());
        if(user2.getEmail()!=null){
            us = ud.queryOne(new User(null,user2.getEmail(),null,user1.getPassWord()));
            if(us!=null){
                throw new Exception("88");
            }
        }
        int num = ud.modified(user2);
        if(num == 0){
            throw new Exception(num+"");
        }
        return num;
    }
    //个人中心主页展示账号相关主要信息
    public Map<String,Object> showCenter(Integer u_id) throws Exception {
        //根据u_id查询账号相关信息
        User user = new User();
        user.setU_id(u_id);
        user = ud.queryOne(user);
        if(user==null){
            //账号不存在
            throw new Exception("0");
        }
        //根据u_id查询本账号下所有订单信息
        OrdersDao od = new OrdersDao();
        List<Orders> list = od.queryAllOrders(u_id );
        if(list==null){
            //此账号还没有买过任何东西，没有订单
            throw new Exception("0");
        }
        int countWaitPay = 0;
        int countWaitReceive = 0;
        for(Orders o:list){
            if(o.getOrderState()==0){
                countWaitPay++;
            }else if(o.getOrderState()==1){
                countWaitReceive++;
            }
        }
        String countNoComment = od.queryCountNoComment(u_id).toString();
        ProductDao pd = new ProductDao();
        String countCollection = pd.queryCountCollection(u_id).toString();
        Map<String,Object> map = new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("userPhone",user.getPhone());
        map.put("email",user.getEmail() );
        map.put("countWaitPay",countWaitPay );
        map.put("countWaitReceive",countWaitReceive );
        map.put("countNoComment",countNoComment );
        map.put("countCollection",countCollection );
        map.put("userimg",user.getUserimg() );
        return map;
    }
    //商品查找
    public PageUtil queryProducts(String keywords, Integer index, Integer size) throws Exception{
        List list = new ArrayList<>();
        list=fd.queryProduct(keywords,index,size);
        int total = fd.getCount(keywords);
        PageUtil<Product> pageUtil = new PageUtil<>();
        pageUtil.index=index;
        pageUtil.size=size;
        pageUtil.total=total;
        pageUtil.page = (total-1)/size+1;
        pageUtil.list = list;
        if(list.size()==0){
            throw new Exception("没找到");
        }
        return pageUtil;
    }
    //商品查找，按价格排序
    public PageUtil querProductPrice(String keyword,Integer index,Integer size) throws Exception{
        List list = new ArrayList();
        list = fd.queryProPrice(keyword,index,size);
        int total = fd.getCount(keyword);
        PageUtil<Product> pageUtil = new PageUtil<>();
        pageUtil.index = index;
        pageUtil.size = size;
        pageUtil.total = total;
        pageUtil.list = list;
        pageUtil.page = (total-1)/size+1;
        if(list.size()==0){
            throw  new Exception("没找到");
        }
        return pageUtil;
    }
    //商品查找按点赞排序
    public PageUtil querProductcomment(String keyword,Integer index,Integer size) throws Exception{
        List list = new ArrayList();
        list = fd.queryProCom(keyword,index,size);
        int total = fd.getCount(keyword);
        PageUtil<Product> pageUtil = new PageUtil<>();
        pageUtil.index = index;
        pageUtil.size = size;
        pageUtil.total = total;
        pageUtil.list = list;
        pageUtil.page = (total-1)/size+1;
        if(list.size()==0){
            throw  new Exception("没找到");
        }
        return pageUtil;
    }
    //修改密保
    public int updatemi(String question, String answer, String userName) throws Exception{
        int result = 0;
        UserDao ud = new UserDao();
        result = ud.updateMibao(JDBC_Util.getConn(),question,answer,userName);
        if(result<=0){
            throw new Exception("修改失败");
        }
        return result;
    }
    //修改昵称
    public int updateNa(String userName,String oldUserName) throws Exception{
        System.out.println("userName:"+userName+" oldUserName:"+oldUserName);
        Connection conn = JDBC_Util.getConn();
        int result = ud.updateName(JDBC_Util.getConn(),userName,oldUserName);
        if(result<=0){
            throw new Exception("修改失败");
        }
        JDBC_Util.closeAll(conn,null ,null );
        conn=null;
        return result;
    }
    public static void main(String[] args) throws Exception {
        AccountService as = new AccountService();
        System.out.println(as.updateNa("bbbbb","aaaaa"));
    }

}
