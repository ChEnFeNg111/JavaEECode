package mvc;

/**
 * 查询数据库里面的全部数据
 */

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
        //设置编码，防止乱码
        req.setCharacterEncoding("utf-8");

        //连接数据库
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs= null;

        try {
            //获取连接
            conn=Utils.getConnection();

            //预编译sql
            stat = conn.prepareStatement("select sid,sname,birthday,sex from student");

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
                Student s = new Student(sid,sname,birthday,sex);
                list.add(s);

            }
            //添加进 servlet的作用域，准备传给 JSP 页面进行显示
            req.setAttribute("l",list);

            //转发到 JSP 页面上
            req.getRequestDispatcher("/selectAll.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(rs,conn,stat);
        }
    }
}
