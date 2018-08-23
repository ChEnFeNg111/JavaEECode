package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDao {
    //增加数据          传入一个学生对象
    public void insert(Student s){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            //利用工具类获取连接对象
            conn= Utils.getConnection();

            //获取执行SQL的预编译对象
            String sql = "insert into student values(?,?,?,?)";
            stat = conn.prepareStatement(sql);

            //给SQL语句赋值
            stat.setInt(1,s.getSid());
            stat.setString(2,s.getSname());
            stat.setDate(3,new java.sql.Date(s.getBirthday().getTime()));
            stat.setString(4,s.getSex());

            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("\t\t\t\t插入了" + i + "行数据\t\t\t\t\r");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(conn,stat);
        }
    }

    //删除数据          传入一个学生对象的编号(唯一)
    public void delete(int sid){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            //利用工具类获取连接对象
            conn= Utils.getConnection();

            //获取执行SQL的预编译对象
            String sql = "delete from student where sid= ?";
            stat = conn.prepareStatement(sql);

            //给SQL语句赋值
            stat.setInt(1,sid);

            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("\t\t\t\t删除了" + i + "行数据\t\t\t\t\r");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(conn,stat);
        }
    }

    //修改数据          传入一个学生对象
    public void update(Student s){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            //利用工具类获取连接对象
            conn= Utils.getConnection();

            //获取执行SQL的预编译对象
            String sql = "update student set sname=?,birthday=?,sex=? where sid=? ";
            stat = conn.prepareStatement(sql);

            //给SQL语句赋值
            stat.setString(1,s.getSname());
            stat.setDate(2,new java.sql.Date(s.getBirthday().getTime()));
            stat.setString(3,s.getSex());
            stat.setInt(4,s.getSid());


            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("\t\t\t\t修改了" + i + "行数据\t\t\t\t\r");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(conn,stat);
        }
    }
    //根据学生的sid查询
    public Student findById(int sid){
        Connection conn=null;
        PreparedStatement stat = null;
        ResultSet rs=null;
        try {
            //获取连接对象
            conn= Utils.getConnection();

            //获取执行SQL的预编译对象
            String sql = "select * from student where sid=?";

            //获取执行SQL的预编译对象
            stat = conn.prepareStatement(sql);

            //给SQL语句赋值
            stat.setInt(1,sid);

            //获取执行后的结果集
            rs = stat.executeQuery();

            if(rs.next()){
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setBirthday(rs.getDate("birthday"));
                s.setSex(rs.getString("sex"));
                return s;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);//避免返回 检查 异常
        }finally{
            Utils.close(rs,conn,stat);
        }

    }

    //根据查询 全部的学生
    public List<Student> findAll(){
        Connection conn=null;
        PreparedStatement stat = null;
        ResultSet rs=null;
        try {
            //获取连接对象
            conn= Utils.getConnection();

            //获取执行SQL的预编译对象
            String sql = "select * from student";

            //获取执行SQL的预编译对象
            stat = conn.prepareStatement(sql);


            //获取执行后的结果集
            rs = stat.executeQuery();
            List<Student> list = new ArrayList<>();
            while(rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setBirthday(rs.getDate("birthday"));
                s.setSex(rs.getString("sex"));
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);//避免返回 检查 异常
        }finally{
            Utils.close(rs,conn,stat);
        }
    }

    public static void main(String[] args) throws ParseException {
        StudentDao sd = new StudentDao();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("1997-11-24");

        Student s = new Student();
        s.setSname("chen");
        s.setBirthday(date);
        s.setSex("男");


        //查询所有学生的数据
        List<Student> list = sd.findAll();
        for (Student ss : list){
            System.out.println(ss);
        }
        System.out.println("-------------------------------------------------------------------------------------------");


        //增加
        sd.insert(s);
        System.out.println("-------------------------------------------------------------------------------------------");

        //根据学生编号查询数据
        Student s1 = sd.findById(1007);
        System.out.println(s1);
        System.out.println("-------------------------------------------------------------------------------------------");

        //修改数据
        s1.setSex("女");
        sd.update(s1);
        System.out.println("-------------------------------------------------------------------------------------------");

        //查询所有学生的数据
        List<Student> list2 = sd.findAll();
        for (Student ss : list2){
            System.out.println(ss);
        }

        //删除数据
        sd.delete(1039);
        System.out.println("-------------------------------------------------------------------------------------------");

        //查询所有学生的数据
        List<Student> list3 = sd.findAll();
        for (Student ss : list3){
            System.out.println(ss);
        }
    }
}
