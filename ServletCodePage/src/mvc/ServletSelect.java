package mvc;

/**
 * 查询数据库里面的全部数据
 */

import entity.Page;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//添加注解：浏览器访问路径
@WebServlet(urlPatterns = "/select")
public class ServletSelect extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Page p = new Page(req.getParameter("page"), req.getParameter("size"));

        //连接数据库
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs= null;

        try {
            //获取连接
            conn=Utils.getConnection();

            //预编译sql
            stat = conn.prepareStatement("select sid,sname,birthday,sex from student limit ?,?");

            stat.setInt(1,p.getStart());
            stat.setInt(2,p.getSize());

            //执行sql
            rs = stat.executeQuery();

            //新建一个集合，存放查询到的数据
            List<Student> list  = new ArrayList<>();

            //遍历结果集
            while(rs.next()){

                //得到每一个属性的值
                int sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");

                //实例化一个学生对象
                Student ss = new Student(sid,sname,birthday,sex);
                list.add(ss);

            }

            //查询出数据库中总共有多少条记录
            stat = conn.prepareStatement("select count(*)  from student");
            rs = stat.executeQuery();



            int count = 0; // 数据库中的总记录数

            if(rs.next()){
                count = rs.getInt(1);
            }

            p.setCount(count);
            p.setList(list);



            //添加进 servlet的作用域，准备传给 JSP 页面进行显示
            req.setAttribute("p",p); //数据集合



            //转发到 JSP 页面上
            req.getRequestDispatcher("/selectAll.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(rs,conn,stat);
        }
    }
}
