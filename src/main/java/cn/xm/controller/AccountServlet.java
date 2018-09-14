package cn.xm.controller;

import cn.xm.dao.ReceiverDao;
import cn.xm.dao.T_areaDao;
import cn.xm.dao.UserDao;
import cn.xm.dao.VerifyCodeDao;
import cn.xm.entity.Product;
import cn.xm.entity.Receiver;
import cn.xm.entity.T_area;
import cn.xm.entity.User;
import cn.xm.service.AccountService;
import cn.xm.service.VerifyCodeService;
import cn.xm.util.PageUtil;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.api.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountServlet extends HttpServlet {
    /**
     * 修改账户信息
     */
    public void modified(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user1 = new User();
         String u_idStr = request.getParameter("u_id");
         Integer u_id = Integer.valueOf(u_idStr);
         user1.setU_id(u_id);
        User user2 = new User();
        String passWordOld = request.getParameter("passWordOld");
        user2.setUserName(request.getParameter("userName"));
        user2.setPassWord(request.getParameter("passWord"));
        user2.setEmail(request.getParameter("email"));
        user2.setIdCard(request.getParameter("idCard"));
        user2.setAddress(request.getParameter("address"));
        Integer level;
        try {
            level = Integer.parseInt("level");
        } catch (NumberFormatException e) {
            level = 2;
        }
        user2.setLevel(level);
        user2.setQuestion(request.getParameter("question"));
        user2.setAnswer(request.getParameter("answer"));
//        System.out.println("user2" + user2);
//        System.out.println("user1" + user1);
        AccountService as = new AccountService();
        int result = 0;
        try {
            result = as.modified(user1, user2,passWordOld);
//            System.out.println("result:"+result);
            response.getWriter().write(result + "");
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            response.getWriter().write(e.getMessage());
        }

    }

    /**
     * 注册
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String phone = request.getParameter("phone");
        String passWord = request.getParameter("passWord");
        AccountService as = new AccountService();
        try {
            if (passWord != null && passWord != "null" && passWord != "") {
                user.setPhone(phone);
                user.setPassWord(passWord);
                user.setUserName(phone+"");
                user = as.register(user);
            } else {
                user.setPhone(phone);
                user = as.register1(user);
            }
            String jsonStr = JSON.toJSONString(user);
            response.getWriter().write(jsonStr);
        } catch (Exception e) {
            System.err.print(e.getMessage());
            response.getWriter().write(JSON.toJSONString(e.getMessage()));
        }
    }

    /**
     * 登陆业务
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        System.out.println("account:" + account + " " + "passWord:" + passWord);
        User user = null;
        Integer account1;
        try {
            account1 = Integer.parseInt(account);
        } catch (NumberFormatException e) {
            account1 = -1;
        }
        user = new User(account1, account, account, passWord);
        AccountService as = new AccountService();

        try {
            user = as.login(user);
            System.out.println("user:" + user);
            request.getSession().setAttribute("user", user);
            String jsonStr = JSON.toJSONString(user);
            response.getWriter().write(jsonStr);
        } catch (Exception e) {
            System.err.print(e.getMessage());
            response.getWriter().write(JSON.toJSONString(e.getMessage()));
        }
    }

    /**
     * 选择地址
     */
    public void queryArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      System.out.println("进servlet方法了");
        String provinceName = request.getParameter("provinceName");
        String cityName = request.getParameter("cityName");
        String districtName = request.getParameter("districtName");
//        System.out.println("获取到的前端地区名称分别是：省-"+provinceName+" 市-"+cityName+" 区-"+districtName);
        List<T_area> list = new ArrayList<>();
        T_areaDao td = new T_areaDao();
        T_area ta = new T_area();
        if (provinceName == "") {
//            System.out.println("没有省级名称");
            list = td.queryT_areas(-1);
        } else if (cityName == "") {
//            System.out.println("有省级名称");
            ta = td.queryOneT_rea(-1, provinceName);
            list = td.queryT_areas(ta.getAreaId());
        } else if (districtName == "") {
//            System.out.println("有市级名称");
            ta = td.queryOneT_rea(-1, provinceName);
            ta = td.queryOneT_rea(ta.getAreaId(), cityName);
            list = td.queryT_areas(ta.getAreaId());
        }
        String jsonStr = JSON.toJSONString(list);
        response.getWriter().write(jsonStr);
//        System.out.println("控制层获得数据："+list);
    }

    //增加修改收货人信息
    public void insertReceiver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String r_idStr = request.getParameter("r_id");
//        System.out.println("r_idStr=" + r_idStr);
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
        String receiverName = request.getParameter("receiverName");
        String receiverPhone = request.getParameter("receiverPhone");
        String receiverAddress = request.getParameter("receiverAddress");
        Integer postcode = Integer.getInteger(request.getParameter("postcode"));
        String receiverAddressNickName = request.getParameter("receiverAddressNickName");
        ReceiverDao rd = new ReceiverDao();
        Receiver re = null;
        int num = 0;
        String str = "";
        if (!str.equals(r_idStr) && r_idStr != null && r_idStr != "null") {
            Integer r_id = Integer.valueOf(r_idStr);
            re = new Receiver(r_id, u_id, receiverName, receiverPhone, receiverAddress, postcode, receiverAddressNickName);
            num = rd.modifiedReceiver(re);

        } else {
            re = new Receiver(u_id, receiverName, receiverPhone, receiverAddress, postcode, receiverAddressNickName);
            num = rd.insertReceiver(re);
        }
//        System.out.println("数字大于0，表示插入地址成功：" + num);
        String jsonStr = JSON.toJSONString(num);
        response.getWriter().write(jsonStr);

    }

    //查询指定用户所设置的所有收货地址信息
    public void queryAllReceiver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_idStr = request.getParameter("u_id");
//        System.out.println("u_idStr=" + u_idStr);
        ReceiverDao rd = new ReceiverDao();
        List<Receiver> list = new ArrayList<>();
        Receiver re = new Receiver();
        String str = "";
        if (!str.equals(u_idStr) && u_idStr != null && !"null".equals(u_idStr)) {
            Integer u_id = Integer.valueOf(u_idStr);
            re.setU_id(u_id);
            list = rd.queryAllReceiver(re);
            String listStr = JSON.toJSONString(list);
//            System.out.println("查询到的指定地址信息：" + listStr);
            response.getWriter().write(listStr);
        } else {
            response.getWriter().write(JSON.toJSONString("没有设置收货"));
        }
    }

    //查询指定r_id序号的收货地址信息
    public void queryReceiver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String r_idStr = request.getParameter("r_id");
//        System.out.println("r_idStr=" + r_idStr);
        ReceiverDao rd = new ReceiverDao();
        Receiver re = null;
        String str = "";
        if (!str.equals(r_idStr) && r_idStr != null && !"null".equals(r_idStr)) {
            Integer r_id = Integer.valueOf(r_idStr);
            re = rd.queryReceiver(r_id);
            String reStr = JSON.toJSONString(re);
//            System.out.println("查询到的指定地址信息：" + reStr);
            response.getWriter().write(reStr);
        } else {
            String reStr = JSON.toJSONString("1");
            response.getWriter().write(reStr);
        }
    }

    //删除指定r_id序号的收货地址信息
    public void deleteReceiver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String r_idStr = request.getParameter("r_id");
//        System.out.println("r_idStr=" + r_idStr);
        ReceiverDao rd = new ReceiverDao();
        int num = -1;
        String str = "";
        if (!str.equals(r_idStr) && r_idStr != null && r_idStr != "null") {
            Integer r_id = Integer.valueOf(r_idStr);
            num = rd.deleteReceiver(r_id);
        }
//        System.out.println("数字大于0，表示删除地址成功：" + num);
        response.getWriter().write(num + "");
    }
    //注册号码发送随机验证码
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        System.out.println(phone);
        VerifyCodeService vcs = new VerifyCodeService();
        String num = vcs.getVerifyCode(phone);
        if("-1".equals(num)){
            response.getWriter().write("-1");
        }else if("0".equals(num)){
            response.getWriter().write("0");
        }else{
            System.out.println("num:"+num);
            response.getWriter().write(num);
        }

    }

    //个人中心展示
    public void showCenter(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String u_idStr = request.getParameter("u_id");
        try {
            Integer u_id = Integer.valueOf(u_idStr);
            AccountService as = new AccountService();
            Map<String,Object> map = as.showCenter(u_id);
            String  mapStr = JSON.toJSONString(map);
            response.getWriter().write(mapStr);
        }catch (ClassCastException e){
            response.getWriter().write("0");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }

    //关键字搜索
    public void products(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywords = request.getParameter("keyword");
        AccountService as = new AccountService();
        //ajax中的data中取index值
        String indexStr = request.getParameter("index");
        Integer index =1;
        if(indexStr!=null&&!"".equals(indexStr)){
            index = Integer.valueOf(indexStr);
        }
        try {
            PageUtil<Product> pageUtil = as.queryProducts(keywords,index,24);
            response.getWriter().write(JSON.toJSONString(pageUtil));
        } catch (Exception e) {
            response.getWriter().write(JSON.toJSONString(e.getMessage()));
        }
    }
    //查询商品按价格排序
    public void queryproPrices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String indexstr = request.getParameter("index");
        AccountService as = new AccountService();
        Integer index = 1;
        if(indexstr!=null&&!"".equals(indexstr)){
            index = Integer.valueOf(indexstr);
            try {
                PageUtil<Product> pageUtil = as.querProductPrice(keyword, index, 24);
                response.getWriter().write(JSON.toJSONString(pageUtil));
            }catch (Exception e){
                response.getWriter().write(JSON.toJSONString(e.getMessage()));
            }
        }
    }
    //查询商品按点赞排序
    public void queryProComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String indexstr = request.getParameter("index");
        AccountService as = new AccountService();
        Integer index = 1;
        if(indexstr!=null&&!"".equals(indexstr)){
            index = Integer.valueOf(indexstr);
            try {
                PageUtil<Product> pageUtil = as.querProductcomment(keyword, index, 24);
                response.getWriter().write(JSON.toJSONString(pageUtil));
            }catch (Exception e){
                response.getWriter().write(JSON.toJSONString(e.getMessage()));
            }
        }
    }
    //个人信息页面用户调用
    public  void showUserInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String u_idStr = request.getParameter("u_id");
        Integer u_id = Integer.valueOf(u_idStr);
        UserDao ud = new UserDao();
        User user = new User();
        user.setU_id(u_id);
        user = ud.queryOne(user);
        if(user!=null){
            String userStr = JSON.toJSONString(user);
            response.getWriter().write(userStr);
        }else{
            response.getWriter().write("0");
        }
    }
    //修改密保
    public void updatemibao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        String userName = request.getParameter("userName");
        AccountService as = new AccountService();
        int result = 0;
        try {
            result = as.updatemi(question, answer, userName);
            response.getWriter().write(result+"");
        }catch (Exception e){
            response.getWriter().write(e.getMessage());
        }
    }
    //修改昵称
    public void updatename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String oldUserName = request.getParameter("oldUserName");
        AccountService as = new AccountService();
        int result = 0;
        try {
            result = as.updateNa(userName, oldUserName);
            response.getWriter().write(result+"");
        }catch (Exception e){
            response.getWriter().write(e.getMessage());
        }
    }
}