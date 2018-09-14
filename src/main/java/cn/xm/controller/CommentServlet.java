package cn.xm.controller;

import cn.xm.entity.Comment;
import cn.xm.entity.Reply;
import cn.xm.service.CommentService;
import cn.xm.util.CommentUtil;
import cn.xm.util.ReplyUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommentServlet extends HttpServlet {
    //获取评价按点赞排序
    public void comments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_name = request.getParameter("p_name");
        CommentService cs = new CommentService();
        try {
            CommentUtil<Comment> commentUtil = cs.queryComments(p_name);
            response.getWriter().write(JSON.toJSONString(commentUtil));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取评价按时间排序
    public void comments2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_name = request.getParameter("p_name");
        CommentService cs = new CommentService();
        try {
            CommentUtil<Comment> commentUtil = cs.queryComments2(p_name);
            response.getWriter().write(JSON.toJSONString(commentUtil));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取评论
    public void replys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String p_name = request.getParameter("p_name");
        CommentService cs = new CommentService();
        try {
            ReplyUtil<Reply> replyUtil = cs.queryReply(userName,p_name);
            response.getWriter().write(JSON.toJSONString(replyUtil));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //回复评论
    public void insertReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentName = request.getParameter("commentName");
        String userName = request.getParameter("userName");
        String r_content = request.getParameter("r_content");
        CommentService cs = new CommentService();
        int result = cs.insertReplys(commentName,userName,r_content);
        response.getWriter().write(result+"");
    }
    //点赞
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String p_name = request.getParameter("p_name");
        CommentService cs = new CommentService();
        int result = cs.updateUps(userName,p_name);
        response.getWriter().write(result+"");

    }
    //查找个人评论
    public void queryPersonalComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
//        System.out.println("11"+userName);
        CommentService cs = new CommentService();
        List<Comment> list = new ArrayList<>();
        try {
            list = cs.personalComment(userName);
//            System.out.println(list);
            response.getWriter().write(JSON.toJSONString(list));
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }
    //对已购买商品进行评论
    public void comm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p_name = request.getParameter("p_name");
        String userName = request.getParameter("userName");
        String content = request.getParameter("content");
        CommentService cs = new CommentService();
        int result = cs.insertcomments(p_name,userName,content);
        response.getWriter().write(result+"");
    }
}
