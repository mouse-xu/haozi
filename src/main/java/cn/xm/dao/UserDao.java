package cn.xm.dao;

import cn.xm.entity.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BassDao{
    /**
     * 登陆查询
     * @param user
     * @return
     */
    public User queryOne(User user){
        String sql = "select * from user where u_id = ?  or email =? or phone = ?";
        return super.queryOne(User.class,sql,user.getU_id(),user.getEmail(),user.getPhone());
    }

    /**
     * 查询所有
     * @return
     */
    public List<User> queryAll(){
        String sql = "select * from user";
        return super.queryAll(User.class,sql);
    }
    public int modified(User user){
        String sql = "update user set ";
        boolean flag = false;
        List<Object> params = new ArrayList<>();
       if(user.getUserName()!=null){
           sql+="userName = ?";
           params.add(user.getUserName());
           flag = true;
       }
       if(user.getPassWord()!=null && flag == false){
           sql+="passWord = ?";
           params.add(user.getPassWord());
           flag = true;
       }else if(user.getPassWord()!=null && flag == true){
           sql+=",passWord = ?";
           params.add(user.getPassWord());
       }
       if(user.getEmail()!=null && flag == false){
           sql+="email = ?";
           params.add(user.getEmail());
           flag = true;
       }else if(user.getEmail()!=null && flag == true){
           sql+=",email = ?";
           params.add(user.getEmail());
       }
       if(user.getIdCard()!=null && flag == false){
           sql+="idCard = ?";
           params.add(user.getIdCard());
           flag = true;
       }else if(user.getIdCard()!=null && flag == true){
           sql+=",idCard = ?";
           params.add(user.getIdCard());
       }
       if(user.getAddress()!=null && flag == false){
           sql+="address = ?";
           params.add(user.getAddress());
           flag = true;
       }else if(user.getAddress()!=null && flag == true){
            sql+=",address = ?";
            params.add(user.getAddress());
            flag = true;
        }
//        if(user.getLevel()!=null && flag == false){
//            sql+="level = ?";
//            params.add(user.getLevel());
//            flag = true;
//        }else if(user.getLevel()!=null && flag == true){
//            sql+=",level = ?";
//            params.add(user.getLevel());
//            flag = true;
//        }
       if(user.getQuestion()!=null && flag == false){
           sql+="question = ?,answer = ?";
           params.add(user.getQuestion());
           params.add(user.getAnswer());
           flag = true;
       }else if(user.getQuestion()!=null && flag == true){
           sql+=",question = ?,answer = ?";
           params.add(user.getQuestion());
           params.add(user.getAnswer());
           flag = true;
       }
       sql+=" where u_id = ?";
//        System.out.println("sql:"+sql);
       params.add(user.getU_id());
        return super.update(sql,params.toArray());
    }
    //查询密码
    public String queryPassWord(User user1){
        String sql = "select passWord from user where u_id = ?";
        return  (String)super.queryField(sql, user1.getU_id());
    }
    //注册
    public User register(User user){
        String sql = "insert into user (phone,passWord,userName) values(?,?,?)";
        super.update(sql,user.getPhone(),user.getPassWord(),user.getUserName());
        user = this.queryOne(user);
        return user;
    }
    //修改密保
    public int updateMibao(Connection conn,String question, String answer, String userName){
        String sql = "UPDATE `user` set question = ?,answer = ?\n" +
                "where userName = ?";
        return super.update(conn,sql,question,answer,userName);
    }
    //修改昵称
    public int updateName(Connection conn, String userName, String oldUserName){
        String sql = "UPDATE `user` set userName = ?\n" +
                "where userName = ?";
        return super.update(conn,sql,userName,oldUserName);
    }
    public static void main(String[] args) {
        UserDao ud = new UserDao();
        User user = new User();
        user.setU_id(1005);
//      user.setUserName("345");
        user.setPhone("18788899654");
        user.setEmail("12343@126.com");
        user.setPassWord("1233");
        user.setAddress("1233");
        user.setIdCard("1233");
        user.setQuestion("1233");
        user.setAnswer("1233");
        System.out.println(ud.queryOne(user));

    }
}
