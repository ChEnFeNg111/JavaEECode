package com.student;

import java.sql.*;

public class Utils {
    static final String URL = "jdbc:mysql://localhost:3306/school?serverTimezone=GMT%2B8&useSSL=false";
    static final String username="root";
    static final String password="123";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,username,password);
    }

    public static void close(ResultSet rs, Connection conn, PreparedStatement stat){
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, PreparedStatement stat){
        Utils.close(null,conn,stat);
    }
}
