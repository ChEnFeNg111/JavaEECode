package mvc;

/**
 * 查询数据库里面的全部数据
 */

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

        String page = req.getParameter("page"); // 页数
        String size = req.getParameter("size"); // 每页记录数


        int p=1; // 默认首页为1
        int s=5; // 默认大小为5

        if(page!=null){
            p = Integer.parseInt(page);
        }

        if(size!=null){
            s = Integer.parseInt(size);
        }

        int strat = (p-1)*s; // SQL查询语句的起始位置

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
            stat = conn.prepareStatement("select sid,sname,birthday,sex from student limit ?,?");

            stat.setInt(1,strat);
            stat.setInt(2,s);

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
            stat = conn.prepareStatement("select count(*) from student");
            rs = stat.executeQuery();

            int count = 0; // 数据库中的总记录数

            if(rs.next()){
                count = rs.getInt(1);
            }

            int total = 0 ; // 总页数

            if(( count %s ) == 0){
                total = count/s;
            }else {
                total = count/s +1;
            }



            //添加进 servlet的作用域，准备传给 JSP 页面进行显示
            req.setAttribute("l",list); //数据集合

            req.setAttribute("total",total); // 总页数


            //转发到 JSP 页面上
            req.getRequestDispatcher("/selectAll.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(rs,conn,stat);
        }
    }
}
