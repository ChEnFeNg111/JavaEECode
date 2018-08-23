package com.jdbc;
/**
 * SQL注入攻击问题以及解决方法
 */

import java.sql.*;

public class TestPreparedStatement {
    static final String url = "jdbc:mysql://localhost:3306/school?serverTimezone=GMT%2B8&useSSL=false";


    public static boolean login(String username, String password) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //获取连接对象
            conn = DriverManager.getConnection(url, "root", "123");

            //创建SQL的执行对象
            stat = conn.createStatement();

            //拼接SQL语句  存在注入攻击问题
            String sql = "select * from user where username='" + username + "' and password='" + password + "'";

            System.out.println(sql);

            //执行SQL语句
            rs = stat.executeQuery(sql);

            //如果查询到了就该结果集就不为空,返回 true,否则返回 false
            boolean flag = rs.next();

            return flag;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

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
        }
        return false;
    }

    public static boolean login2(String username, String password) {
        Connection conn = null;
        PreparedStatement stat = null;//预编译statement 对象
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //获取连接对象
            conn = DriverManager.getConnection(url, "root", "123");

            //先创建SQL语句                           ? 只能代表值，不能是关键字，表名，列名
            String sql = "select * from user where username= ? and password= ? ";

            //创建SQL的预编译执行对象,将刚才创建好的对象传入进去
            stat = conn.prepareStatement(sql);

            stat.setString(1,username);//给每一个 ? 赋值,set方法从下标为 1 开始
            stat.setString(2,password);


            //执行SQL语句
            rs = stat.executeQuery();

            //如果查询到了就该结果集就不为空,返回 true,否则返回 false
            boolean flag = rs.next();

            return flag;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

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
        }
        return false;
    }

    public static void main(String[] args) {
        String username = "chen";
//      String password = " ' or '1=1 ";// 注入攻击问题, 结果会返回 true
        String password = "123";
        boolean flag = login2(username, password);

        if(flag){
            System.out.println("登陆成功");
        }else{
            System.out.println("登陆失败");
        }
    }
}
