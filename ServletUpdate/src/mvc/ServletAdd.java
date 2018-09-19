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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns ="/add")
public class ServletAdd extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Connection conn=null;
        PreparedStatement stat=null;

        Date birthday=null;
        int sid = Integer.parseInt(req.getParameter("sid"));
        String sname = req.getParameter("sname");
        String sex=req.getParameter("sex");

        try {
            birthday = sdf.parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            conn=Utils.getConnection();
            stat=conn.prepareStatement("insert into student values(?,?,?,?)");

            stat.setInt(1,sid);
            stat.setString(2,sname);
            stat.setDate(3,new java.sql.Date(birthday.getTime()));
            stat.setString(4,sex);

            stat.executeUpdate();

            resp.sendRedirect("select"); //重定向
           //req.getRequestDispatcher("add_success.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("add_error.jsp");
           // req.getRequestDispatcher("add_error.jsp").forward(req,resp);
        }finally{
            Utils.close(conn,stat);
        }
    }
}
