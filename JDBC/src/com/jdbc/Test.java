package com.jdbc;

import java.sql.*;

public class Test {
    static final String URL = "jdbc:mysql://localhost:3306/school?serverTimezone=GMT%2B8&useSSL=false";
    static final String CLASS="com.mysql.cj.jdbc.Driver";
    static final String sql4 =  "select * from student";

    public static void main(String[] args) throws ClassNotFoundException {
        String sql1 = "insert into student(sid,sname,birthday,sex) values(null, '林','1997-11-24','男')";
        String sql2 = "delete from student where sname='林'";
        String sql3 =  "update student set sex='女' where sname='林' ";


        insert(sql1);
        System.out.println("---------------------------------");
        update(sql2);
        System.out.println("---------------------------------");
        delete(sql3);
        System.out.println("---------------------------------");
        select(sql4);

    }
    public static void select(String sql) throws ClassNotFoundException {
        //注册驱动(最新的JDBCjar包可以省略此步骤)
        Class.forName(CLASS);


        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DriverManager.getConnection(URL, "root", "123");



            //创建执行SQL的对象
            stat = conn.prepareStatement(sql);

            //执行SQL语句
            rs = stat.executeQuery();

            while(rs.next()){
                int sid = rs.getInt(1);
                String sname = rs.getString(2);
                String birthday = rs.getString(3);
                String sex = rs.getString(4);
                System.out.println(sid + "\t\t" + sname + "\t\t" + birthday + "\t\t" + sex);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }


    public static void update(String sql) throws ClassNotFoundException {
        //注册驱动
        Class.forName(CLASS);


        Connection conn = null;
        PreparedStatement stat = null;

        try {
            //获取连接
            conn = DriverManager.getConnection(URL, "root", "123");

            //创建执行SQL的对象
            stat = conn.prepareStatement(sql);

            //执行SQL语句
            int i = stat.executeUpdate();


            System.out.println("修改了" + i + "行数据");

            //查询一遍数据库
            select(sql4);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }


    public static void delete(String sql) throws ClassNotFoundException {
        //注册驱动
        Class.forName(CLASS);

        Connection conn = null;
        PreparedStatement stat = null;

        try {
            //获取连接
            conn = DriverManager.getConnection(URL, "root", "123");


            //创建执行SQL的对象
            stat = conn.prepareStatement(sql);

            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("删除了" + i + "行数据");

            //查询一遍数据库
            select(sql4);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }

    public static void insert(String sql) throws ClassNotFoundException {

        //注册驱动
        Class.forName(CLASS);

        Connection conn = null;
        PreparedStatement stat = null;

        try {
            //获取连接
            conn = DriverManager.getConnection(URL, "root", "123");



            //创建执行SQL的对象
            stat = conn.prepareStatement(sql);

            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("增加了" + i + "行数据");

            //查询一遍数据库
            select(sql4);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
}

