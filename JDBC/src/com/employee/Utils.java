package com.employee;

import java.sql.*;

public class Utils {
    private static final String URL ="jdbc:mysql://localhost:3306/company?serverTimezone=GMT%2B8&useSSL=false";
    private static final String USERNAME="root";//数据库的用户名和登陆密码
    private static final String PASSWORD="123";

    //创建连接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    //关闭资源
    public static void close(ResultSet rs, Connection conn, PreparedStatement stat){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, PreparedStatement stat){
        Utils.close(null,conn,stat);
    }

}

