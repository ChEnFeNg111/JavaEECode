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

@WebServlet(urlPatterns = "/updateform")
public class ServletUpdateForm extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Connection conn=null;
        PreparedStatement stat=null;
        Date birthday = null;

        int sid = Integer.parseInt(req.getParameter("sid"));
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");

        try {
            birthday = sdf.parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            conn = Utils.getConnection();
            stat = conn.prepareStatement("update student set sname=?,birthday=?,sex=? where sid=?");

            stat.setString(1,sname);
            stat.setDate(2,new java.sql.Date(birthday.getTime()));
            stat.setString(3,sex);
            stat.setInt(4,sid);

            stat.executeUpdate();

            resp.sendRedirect("select"); // 重定向

           // req.getRequestDispatcher("update_success.jsp").forward(req,resp);



        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("update_error.jsp").forward(req,resp);
        }finally{
            Utils.close(conn,stat);
        }
    }
}
