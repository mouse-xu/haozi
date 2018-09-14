package cn.xm.controller;


import cn.xm.entity.Product;
import cn.xm.entity.ShoppingCarProduct;
import cn.xm.service.ProductService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServlet {
    //根据用户u_id获取其购物车中主商品相关信息
    public void querySomePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
        ProductService ps = new ProductService();
        try {
            //初始化购物车
            ps.initializeShoppingCar(u_id);
            List<Map<String, Object>> list = new ArrayList<>();
            list = ps.querySomePro(u_id);
            String listStr = JSON.toJSONString(list);
            response.getWriter().write(listStr);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }

    //根据用户p_id获取其购物车中的主商品种类获得相关现行的服务产品信息
    public void querySomeSerPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_idStr = request.getParameter("p_id");
//        System.out.println(p_idStr);
        Integer p_id = Integer.valueOf(p_idStr);
        ProductService ps = new ProductService();
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = ps.querySomeSerPro(p_id);
            String listStr = JSON.toJSONString(list);
            response.getWriter().write(listStr);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }

    //前台购物车界面点击去结算按钮后通过此方法改变购物车中相应产品状态及数量
    public  void changeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
        String p_idStr = request.getParameter("p_id");
        String ser_idStr = request.getParameter("ser_id");
        String count1 = request.getParameter("count");
        Integer count = Integer.valueOf(count1);
        ProductService ps = new ProductService();
//        System.out.println("u_idStr:"+u_idStr+" p_idStr:"+p_idStr+" ser_idStr:"+ser_idStr+" count1:"+count1);
//        //初始化购物车
        try {
            if (p_idStr != null) {
//                System.out.println("进主商品");
                Integer p_id = Integer.valueOf(p_idStr);
                ps.modifiedInfoPro(u_id, p_id, count);
            }else if(ser_idStr != null){
//                System.out.println("进服务商品");
                Integer ser_id = Integer.valueOf(ser_idStr);
                ps.modifiedInfoSer(u_id, ser_id, count);
            }
        } catch (Exception e) {
            //返回库存不足的产品p_id
            System.out.println("有问题，问题码："+e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }
    //查询购物车中状态为1的产品数据。
    public  void queryChooseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
        ProductService ps = new ProductService();
        try {
            List<Map<String,Object>> list = ps.queryChooseInfo(u_id);
//            System.out.println("11"+list);
            String listStr = JSON.toJSONString(list);
//            System.out.println("listStr:"+listStr);
            response.getWriter().write(listStr);
        } catch (Exception e) {
            System.out.println("e:"+e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }
    //查询购物车中所有产品的数据。
    public  void queryShoppingCarInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
//        System.out.println(u_id);
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = null;
        try {
            list = ps.queryShoppingCarInfo(u_id);
            //System.out.println("11"+list);
            String listStr = JSON.toJSONString(list);
            response.getWriter().write(listStr);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //根据商品的购物车car_id删除指定商品
    public  void deleteShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String car_idStr = request.getParameter("car_id");
        Integer car_id = Integer.valueOf(car_idStr);
//        System.out.println(car_id);
        ProductService ps = new ProductService();
        int num = ps.deleteShoppingCar(car_id);
        response.getWriter().write(num+"");
    }
    //点击购物车按钮将指定物品加入购物车
    public  void addShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_idStr = request.getParameter("u_id");
        String p_idStr = request.getParameter("p_id");

        ProductService ps = new ProductService();
        int num = 0;
        try {
            Integer p_id = Integer.valueOf(p_idStr);
            Integer u_id = Integer.valueOf(u_idStr);
            num = ps.addShoppingCar(u_id,p_id);
            response.getWriter().write(num+"");
            System.out.println("num:"+num);
        } catch (Exception e) {
            System.out.println("e.getMessage():"+e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }
    //收藏商品
    public  void collectProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_idStr = request.getParameter("p_id");
        Integer p_id = Integer.valueOf(p_idStr);
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
        ProductService ps = new ProductService();
        int result = ps.collectProduct(p_id,u_id);
        response.getWriter().write(result+"");
    }
    //查找收藏商品
    public  void queryCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryP(userName);
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //删除收藏商品
    public  void clearCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_name = request.getParameter("p_name");
        String userName = request.getParameter("userName");
        ProductService ps = new ProductService();
        int result = ps.clearC(p_name,userName);
        response.getWriter().write(result+"");
    }
    //查找单个商品
    public  void queryP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_name = request.getParameter("p_name");
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.querypros(p_name);
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页商品展示(手机)
    public  void queryAllpp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllPP();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页商品展示(家电热门)
    public  void queryAllee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllEE();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页商品展示（电视）
    public  void queryAlltv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllTV();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示（电脑）
    public  void queryAllcc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllCC();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示（家居）
    public  void queryAlljj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllJJ();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示（智能）
    public  void queryAllzz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllZZ();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示(搭配)
    public  void queryAlldd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllDD();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示（出行）
    public  void queryAllout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllCx();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示（手机配件）
    public  void queryAllsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllSp();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示(周边)
    public  void queryAllzb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            list = ps.queryAllZb();
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //首页展示(周边)
    public  void chooseTen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flagStr = request.getParameter("flag");
        Integer flag = 1;
        if("2".equals(flagStr)){
            flag = 2;
        }
        ProductService ps = new ProductService();
        List<Product> list = new ArrayList<>();
        try {
            list = ps.chooseTen(flag);
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
}
