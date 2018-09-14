package cn.xm.service;

import cn.xm.dao.CommentDao;
import cn.xm.dao.ReplyDao;
import cn.xm.entity.Comment;
import cn.xm.util.CommentUtil;
import cn.xm.util.JDBC_Util;
import cn.xm.util.ReplyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentService {
    CommentDao cd = new CommentDao();
    ReplyDao rd = new ReplyDao();
    public CommentUtil queryComments(String p_name) throws Exception {
        List list = new ArrayList();
        list = cd.queryComment(p_name);
        int total = cd.getCommentCount(p_name);
        CommentUtil<Comment> commentUtil = new CommentUtil<>();
        commentUtil.list = list;
        commentUtil.total = total;
        if (list.size() == 0) {
            throw new Exception("没找到");
        }
        return commentUtil;
    }
    public CommentUtil queryComments2(String p_name) throws Exception {
        List list = new ArrayList();
        list = cd.queryComment2(p_name);
        int total = cd.getCommentCount(p_name);
        CommentUtil<Comment> commentUtil = new CommentUtil<>();
        commentUtil.list = list;
        commentUtil.total = total;
        if (list.size() == 0) {
            throw new Exception("没找到");
        }
        return commentUtil;
    }
    public ReplyUtil queryReply(String userName, String p_name) throws Exception{
        List list = new ArrayList();
        list = rd.queryReply(userName,p_name);
        ReplyUtil replyUtil = new ReplyUtil();
        replyUtil.list = list;
        if(list.size() == 0){
            throw new Exception("没找到");
        }
        return  replyUtil;
    }
    //插入回复
    public int insertReplys(String commentName,String userName,String content){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Time = sdf.format(date);
        return cd.insertReply(JDBC_Util.getConn(),commentName,userName,content,Time);
    }
    //评论点赞
    public int updateUps(String userName,String p_name){
        return cd.updateUp(JDBC_Util.getConn(),userName,p_name);
    }
    //查找个人评论
    public List<Comment> personalComment(String userName) throws Exception{
        List<Comment> list = new ArrayList<>();
        list = cd.queryPersonalComment(userName);
//        System.out.println("2"+list);
        if(list!=null||list.size()>0) {
            System.out.println(list);
            return list;
        }else{
            throw new Exception("暂无评论");
        }
    }
    //评论已购买商品
    public int insertcomments(String p_name,String userName,String content){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Time = sdf.format(date);
        return cd.comment(JDBC_Util.getConn(),p_name,userName,content,Time);
    }
    public static void main(String[] args) throws Exception {
        CommentService cs = new CommentService();
//        CommentUtil cu = new CommentUtil();
//        cu = cs.queryComments("小米电视4A 32英寸");
//        ReplyUtil ru = new ReplyUtil();
//        ru=cs.queryReply("耗子","小米电视4A 32英寸");
//        int ru = cs.updateUps("梅子","小米电视4A 32英寸");
        List list =  new ArrayList();
              list = cs.personalComment("耗子");
        System.out.println(list);
    }
}
