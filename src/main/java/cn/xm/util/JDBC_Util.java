package cn.xm.util;

import java.sql.*;

public class JDBC_Util {
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://127.0.0.1:3306/project?serverTimezone=GMT%2B8";
    public static String user = "root";
    public static String password = "mouse";

    /**
     * 注册驱动获得连接
     */
    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放资源
     */
    public static void closeAll(Connection conn, PreparedStatement pstm, ResultSet rs){
            try {
                if(rs!=null){
                rs.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
