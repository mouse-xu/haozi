package cn.xm.service;

//import cn.xm.dao.VerifyCodeDao;
//import com.alibaba.fastjson.JSON;
//import com.sun.deploy.net.HttpResponse;
//import org.apache.http.HttpResponse;
//
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.apache.http.impl.client.HttpClients;


import cn.xm.dao.VerifyCodeDao;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class VerifyCodeService {
    VerifyCodeDao vd = new VerifyCodeDao();
    private static final String SERVER_URL="https://api.netease.im/sms/sendcode.action";//发送验证码的请求路径URL
    private static final String APP_KEY="3a02d7e31d325d2c7a4585c10828ede6";//网易云信分配的账号
    private static final String APP_SECRET="bbe56d95dd96";//网易云信分配的密钥
    private static final String NONCE="123456";//随机数

    public String getVerifyCode(String phone){
        System.out.println("phone2:"+phone);
        List<Map<String,Object>> list = new ArrayList<>();
        VerifyCodeDao vd = new VerifyCodeDao();
        list=vd.queryCount(phone);
        System.out.println("第一次查询返回集合长度："+list.size()+":值是："+list);
        String count = "";
        String dateStr ="";
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        dateStr = formater.format(date);
        System.out.println("今天日期："+dateStr);
        if(list.size()<=0){
            int num = vd.insertNewPhone(phone,dateStr);
            if(num<=0){
                System.out.println("增加新号码失败");

                return "-1";
            }else{
                list=vd.queryCount(phone);
                System.out.println("新插入的号码初始化后，查询返回的结果是："+list);
            }
        }
        if(list.size()<=0){
            System.out.println("增加新号码失败");
            return "-1";
        }

        count = (String)list.get(0).get("count");
        System.out.println("获得的剩余次数是："+count);
        String  dateStr1 = (String)list.get(0).get("date");
        System.out.println("dateStr1="+dateStr1);
        System.out.println("dateStr="+dateStr);
        if(dateStr.equals(dateStr1)&&Integer.valueOf(count)<=0){
            System.out.println("今日使用条数为0");
            return "0";
        }else if(!dateStr.equals(dateStr1)){
            //修改日期为今日日期，初始化使用次数
            int num = vd.updateCountAndDate(phone,dateStr );
            if(num == 0){
                System.out.println("修改剩余条数或日期失败");
                return "-1";
            }else{
                count = "3";
                System.out.println("修改剩余次数："+count);
            }
        }

        if(vd.updateCount(phone)>0){
            System.out.println("修改剩余条数成功");
            String verifyCode = "";
            try {
                 verifyCode =sendMsg(phone);
                if("-1".equals(verifyCode)){
                    System.out.println("发送失败！");
                }else{
                    System.out.println("发送成功！");
                }
            } catch (Exception e) {
                return "-1";
            }
            verifyCode+=count;
            System.out.println("产生的验证码是："+verifyCode);
            return verifyCode;
        }else {
            System.out.println("修改剩余条数失败");
            return "-1";
        }
    }

    //发送短信
    public  String sendMsg(String phone) throws IOException {
//    public static String sendMsg(String phone) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(SERVER_URL);

        String curTime=String.valueOf((new Date().getTime()/1000L));
        String checkSum= getCheckSum(APP_SECRET,NONCE,curTime);

        //设置请求的header
        post.addHeader("AppKey",APP_KEY);
        post.addHeader("Nonce",NONCE);
        post.addHeader("CurTime",curTime);
        post.addHeader("CheckSum",checkSum);

        post.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //设置请求参数
        List<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("mobile",phone));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));

        //执行请求
        HttpResponse response=httpclient.execute(post);
        String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");
//        System.out.println(responseEntity);

        //判断是否发送成功，发送成功返回true
        String code= JSON.parseObject(responseEntity).getString("code");
        String verifyCode= JSON.parseObject(responseEntity).getString("obj");
        if (code.equals("200")){
            return verifyCode;
        }
        return "-1";
    }

    //计算并获取checkSum
    public  String getCheckSum(String appSecret,String nonce,String curTime){
//    public static String getCheckSum(String appSecret,String nonce,String curTime){
        return encode("SHA",appSecret+nonce+curTime);
    }
    public  String encode(String algorithm,String value){
//    public static String encode(String algorithm,String value){
        if(value==null){
            return null;
        }

        try {
            MessageDigest messageDigest=MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    public static String getFormattedText(byte[] bytes){
    public  String getFormattedText(byte[] bytes){
        int len=bytes.length;
        StringBuilder sb=new StringBuilder(len*2);
        for(int $i=0;$i<len;$i++){
            sb.append(HEX_DIGITS[(bytes[$i]>>4)&0x0f]);
            sb.append(HEX_DIGITS[bytes[$i]&0x0f]);
        }
        return sb.toString();
//
    }

    public static final char[] HEX_DIGITS={'0','1','2','3','4','5','6',
            '7','8','9','a','b','c','d','e','f'};


    public static void main(String[] args) {
        VerifyCodeService vsc = new VerifyCodeService();
        String num = vsc.getVerifyCode("18014493264");
        System.out.println("接收到的num="+num);

//        String mobileNumber = "13814128663";//接收验证码的手机号码
//        try {
//            String str = VerifyCodeService.sendMsg(mobileNumber);
//            if("success".equals(str)){
//                System.out.println("发送成功！");
//            }else{
//                System.out.println("发送失败！");
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
    }
}
