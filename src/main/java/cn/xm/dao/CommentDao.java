package cn.xm.dao;

import cn.xm.entity.Comment;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDao extends BassDao {
    //搜索商品评价，按点赞排序
    public List<Map<String,Object>> queryComment(String p_name){
        String sql = "select * from `comment` c\n" +
                "INNER JOIN `user` u\n" +
                "on c.userName=u.userName\n" +
                "where  p_id = (\n" +
                "select p_id from product\n" +
                "where p_name = ?\n" +
                ")\n" +
                "ORDER BY c.up\n" +
                "DESC";
        return super.querySome(sql,p_name);
    }
    //搜索商品评价，按时间排序
    public List<Map<String,Object>> queryComment2(String p_name){
        String sql = "select * from `comment` c\n" +
                "INNER JOIN `user` u\n" +
                "on c.userName=u.userName\n" +
                "where  p_id = (\n" +
                "select p_id from product\n" +
                "where p_name = ?\n" +
                ")\n" +
                "ORDER BY c.co_create_time\n" +
                "DESC";
        return super.querySome(sql,p_name);
    }
    //获取评价总数
    public int getCommentCount(String p_name){
        String sql ="select count(1) c from `comment`\n" +
                "where  p_id = (\n" +
                "select p_id from product\n" +
                "where p_name = ?\n" +
                ")";
        Map<String,Object> map = super.querySome(sql,p_name).get(0);
        return Integer.valueOf(map.get("c").toString());
    }
    //插入回复
    public int insertReply(Connection conn,String commentName, String userName,String content,String Time){
        String sql ="INSERT into reply \n" +
                "(co_id,userName,r_content,r_create_time)\n" +
                "VALUES ((SELECT co_id from `comment` \n" +
                "where userName=?),\n" +
                "?,?,DATE(?))";
        return super.update(conn,sql,commentName,userName,content,Time);
    }
    //评论点赞
    public int updateUp(Connection conn,String userName,String p_name){
        String sql ="UPDATE `comment` c set c.up = c.up+1 \n" +
                "where c.userName = ? and \n" +
                "c.p_id = (select p.p_id from product p \n" +
                "where p.p_name = ?\n" +
                ")\n";
        return super.update(conn,sql,userName,p_name);
    }
    //个人中心搜索个人评价
    public List<Comment> queryPersonalComment(String userName){
        String sql = "select * from `comment`\n" +
                "where userName = ?";
        return super.queryAll(Comment.class,sql,userName);
    }
    //评论购买商品
    public int comment(Connection conn,String p_name,String userName,String content,String time){
        String sql = "INSERT into `comment`\n" +
                "(p_id,userName,co_content,co_create_time)\n" +
                "VALUES((SELECT p_id from product \n" +
                "where p_name=?\n" +
                "),?,?,?\n" +
                ")";
        return super.update(conn,sql,p_name,userName,content,time);
    }
    //测试
    public static void main(String[] args) {
        CommentDao cd = new CommentDao();
        List<Comment> list = new ArrayList<>();
//        list=cd.queryComment2("小米电视4A 32英寸");
//        int a =cd.insertReply(JDBC_Util.getConn(),"不吃肉的虎","不吃肉的虎","不错","2018-7-29");
//        int b =cd.updateUp(JDBC_Util.getConn(),"梅子","小米电视4A 32英寸");
//        list = cd.queryPersonalComment("耗子");
        System.out.println(list);
    }
}
