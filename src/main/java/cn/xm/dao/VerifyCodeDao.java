package cn.xm.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VerifyCodeDao extends BassDao{
    //插入一个新号码记录
    public int insertNewPhone(String phone,String dateStr){
        String sql = "insert into newPhone (phone,date) values(?,?)";
        return super.update(sql,phone,dateStr);
    }
    //查询指定号码当日剩余短信条数
    public List<Map<String,Object>> queryCount (String phone){
        String sql = "select count,date from newPhone where phone = ?";
        return  super.querySome(sql,phone);
    }
    //修改指定号码当日剩余短信条数
    public int updateCount (String phone){
        String sql = "update newPhone set count = count -1  where phone = ?";
        return  super.update(sql,phone);
    }
    //修改日期为今日日期，初始化使用次数
    public int updateCountAndDate(String phone,String date){
        String sql = "update newPhone set count = 3 ,date = ? where phone = ?";
        return  super.update(sql,date,phone);
    }

}
