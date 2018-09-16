package com.employee;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 处理业务逻辑: 对数据库中的数据进行增删改查 ( CRUD )
 */
public class EmployeeDAO {

    //增
    public static void insert(Employee e){
        Connection conn =null;
        PreparedStatement stat=null;
        try {
            //利用工具类 Utils 获取连接对象
            conn=Utils.getConnection();

            //插入语句
            String sql = "insert into emp values(?,?,?,?,?,?,?,?)";

            //获取执行SQL的 statement 对象
            stat = conn.prepareStatement(sql);

            //给SQL语句中的 ?  赋值,下标从 1 开始
            stat.setInt(1,e.getEmpno());
            stat.setString(2,e.getEname());
            stat.setString(3,e.getJob());
            stat.setInt(4,e.getMgr());
            stat.setDate(5, new Date(e.getHiredate().getTime()));
            stat.setDouble(6,e.getSal());
            stat.setDouble(7,e.getComm());
            stat.setInt(8,e.getDeptno());


            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("\t\t\t\t增加了"+i+"行记录\t\t\t\t\r");
        } catch (SQLException E) {
            E.printStackTrace();
        }finally{
            //调用工具类 Utils
            Utils.close(conn,stat);
        }
    }

    //删: 根据 学号(sid) 找到要删除的学生
    public static void delete(int empno){
        Connection conn =null;
        PreparedStatement stat=null;
        try {
            //利用工具类 Utils 获取连接对象
            conn=Utils.getConnection();

            //插入语句
            String sql = "delete from emp where empno=?";

            //获取执行SQL的 statement 对象
            stat = conn.prepareStatement(sql);

            //给SQL语句中的 ?  赋值,下标从 1 开始
            stat.setInt(1,empno);

            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("\t\t\t\t删除了"+i+"行记录\t\t\t\t\r");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Utils.close(conn,stat);
        }
    }

    //修改:根据指定 工号 修改该员工的信息
    public static void update(Employee e){
        Connection conn =null;
        PreparedStatement stat=null;
        try {
            //利用工具类 Utils 获取连接对象
            conn=Utils.getConnection();

            //插入语句
            String sql = " update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? where empno=? ";

            //获取执行SQL的 statement 对象
            stat = conn.prepareStatement(sql);

            //给SQL语句中的 ?  赋值,下标从 1 开始

            stat.setString(1,e.getEname());
            stat.setString(2,e.getJob());
            stat.setInt(3,e.getMgr());
            stat.setDate(4, new Date(e.getHiredate().getTime()));
            stat.setDouble(5,e.getSal());
            stat.setDouble(6,e.getComm());
            stat.setInt(7,e.getDeptno());
            stat.setInt(8,e.getEmpno());


            //执行SQL语句
            int i = stat.executeUpdate();

            System.out.println("\t\t\t\t修改了"+i+"行记录\t\t\t\t\r");

        } catch (SQLException E) {
            E.printStackTrace();
        }finally{
            Utils.close(conn,stat);
        }
    }

    //查询,根据指定 工号 查询,结果只有一条记录
    public static Employee findByID(int empno){
        Connection conn =null;
        PreparedStatement stat=null;
        ResultSet rs = null;
        try {
            //利用工具类 Utils 获取连接对象
            conn=Utils.getConnection();

            //插入语句
            String sql = "select * from emp where empno=?";

            //获取执行SQL的 statement 对象
            stat = conn.prepareStatement(sql);

            //给SQL语句中的 ?  赋值,下标从 1 开始
            stat.setInt(1,empno);

            //执行SQL语句,获得一个结果集,并将结果集中的数据保存到一个 Employee 对象中
            rs = stat.executeQuery();

            //创建一个 Employee 对象
            Employee e = new Employee();

            //判断该 结果集(rs) 中是否有数据
            if(rs.next()){
                e.setEmpno(rs.getInt("empno"));
                e.setEname(rs.getString("ename"));
                e.setJob(rs.getString("job"));
                e.setMgr(rs.getInt("mgr"));
                e.setHiredate(rs.getDate("hiredate"));
                e.setSal(rs.getDouble("sal"));
                e.setComm(rs.getDouble("comm"));
                e.setDeptno(rs.getInt("deptno"));

                return e;
            }else{
                return null;// null 代表没有查询到该数据
            }

        } catch (SQLException E) {
            throw new RuntimeException(E);// 因为try{} 中有return 语句,所以catch{}中最好抛出 非检查 异常
        }finally{
            Utils.close(rs,conn,stat);
        }
    }

    //查询:找出所有的员工信息
    public static List<Employee> findALL(){
        Connection conn =null;
        PreparedStatement stat=null;
        ResultSet rs =null;
        try {
            //利用工具类 Utils 获取连接对象
            conn=Utils.getConnection();

            //插入语句
            String sql = "select * from emp ";

            //获取执行SQL的 statement 对象
            stat = conn.prepareStatement(sql);

            //执行SQL语句,获得一个结果集,并将结果集中的数据保存到一个 List<Employee> 集合中
            rs = stat.executeQuery();

            //创建一个 List<Employee> 对象
            List<Employee> list = new ArrayList<>();

            //判断该 结果集(rs) 中是否有数据
            while (rs.next()){
                Employee e = new Employee();

                e.setEmpno(rs.getInt("empno"));
                e.setEname(rs.getString("ename"));
                e.setJob(rs.getString("job"));
                e.setMgr(rs.getInt("mgr"));
                e.setHiredate(rs.getDate("hiredate"));
                e.setSal(rs.getDouble("sal"));
                e.setComm(rs.getDouble("comm"));
                e.setDeptno(rs.getInt("deptno"));

                list.add(e);
            }

            return list;//如果返回 null 代表该表中还没有数据

        } catch (SQLException E) {
            throw new RuntimeException(E);// 因为try{} 中有return 语句,所以catch{}中最好抛出 非检查 异常
        }finally{
            Utils.close(rs,conn,stat);
        }
    }

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("1997-11-24");//先将时间格式化( String----> Date)


        Employee e = new Employee();
        e.setEname("chen");
        e.setJob("manager");
        e.setMgr(1111);
        e.setHiredate(date);//
        e.setSal(9999.00);
        e.setComm(1000.00);
        e.setDeptno(40);//因为 Deptno 是外键,所以只能填写 Dept表中的数据(10,20,30,40)

        //增
        EmployeeDAO.insert(e);

        System.out.println("-------------------------------------------------------------------------------------------");

        //根据 empno 找到指定的员工信息
        Employee employee = EmployeeDAO.findByID(7934);
        System.out.println(employee);

        System.out.println("-------------------------------------------------------------------------------------------");

        //修改
        employee.setDeptno(40);
        EmployeeDAO.update(employee);

        System.out.println("-------------------------------------------------------------------------------------------");

        //查询出所有的数据
        List<Employee> list = EmployeeDAO.findALL();
        for (Employee employee1 : list) {
            System.out.println(employee1);
        }

        System.out.println("-------------------------------------------------------------------------------------------");

        //删除:根据 工号 指定员工的数据
        EmployeeDAO.delete(7951);


        System.out.println("-------------------------------------------------------------------------------------------");

        //查询出所有的数据
        List<Employee> list1 = EmployeeDAO.findALL();
        for (Employee employee1 : list1) {
            System.out.println(employee1);
        }

    }
}
