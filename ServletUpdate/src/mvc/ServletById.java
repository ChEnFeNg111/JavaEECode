package mvc;

import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(urlPatterns ="/selectByID" )
public class ServletById  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs=null;

        int sid = Integer.parseInt(req.getParameter("sid"));

        try {
                conn=Utils.getConnection();
                stat=conn.prepareStatement("select sid,sname,birthday,sex from student where sid=?");

                stat.setInt(1,sid);


                rs = stat.executeQuery();

                if(rs.next()){
                    int ssid = Integer.parseInt(rs.getString("sid"));
                    String sname=rs.getString("sname");
                    Date birthday = rs.getDate("birthday");
                    String sex = rs.getString("sex");

                    System.out.println(ssid+"=="+sname+"=="+birthday+"=="+sex);

                    Student s = new Student(ssid, sname, birthday, sex);

                    req.setAttribute("s",s);
                }else{
                    req.getRequestDispatcher("find_error.jsp").forward(req,resp);
                }
                req.getRequestDispatcher("result.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.getRequestDispatcher("find_error.jsp").forward(req,resp);
            }
    }
}
