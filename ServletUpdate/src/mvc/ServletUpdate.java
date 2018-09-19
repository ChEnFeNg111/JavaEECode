package mvc;

import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns ="/update" )
public class ServletUpdate extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs= null;

        try {
            conn=Utils.getConnection();
            stat=conn.prepareStatement("select sid,sname,birthday,sex from student where sid=?");

            stat.setInt(1,Integer.parseInt(req.getParameter("sid")));

            rs = stat.executeQuery();

            //因为只有一条数据
            if(rs.next()){
                int sid = Integer.parseInt(rs.getString("sid"));
                String sname = rs.getString("sname");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");

                Student s = new Student(sid,sname,birthday,sex);

                req.setAttribute("s",s);
            }

            req.getRequestDispatcher("update.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("update_error.jsp").forward(req,resp);
        }finally{
            Utils.close(rs,conn,stat);
        }
    }
}
