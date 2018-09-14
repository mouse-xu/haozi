package cn.xm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReplyDao extends BassDao {
    //搜索回复
    public List<Map<String,Object>> queryReply(String userName,String p_name){
        String sql="select * from reply r\n" +
                "INNER JOIN `comment` c\n" +
                "on r.co_id=c.co_id\n" +
                "INNER JOIN `user` u\n" +
                "on r.userName=u.userName\n" +
                "WHERE r.co_id=(\n" +
                "select comment.co_id from `comment`\n" +
                "where userName=? AND\n" +
                "p_id = (\n" +
                "select p_id from product\n" +
                "where p_name = ?\n" +
                ")\n" +
                ")\n" +
                "ORDER BY r.r_create_time,INSTR(r.userName,'官方回复')\n" +
                "DESC";
        return super.querySome(sql,userName,p_name);
    }


    public static void main(String[] args) {
        ReplyDao rd = new ReplyDao();
        List list = new ArrayList();
        list=rd.queryReply("耗子","小米电视4A 32英寸");
        System.out.println(list);
    }
}
