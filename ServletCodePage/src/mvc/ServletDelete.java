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

@WebServlet(urlPatterns = "/delete")
public class ServletDelete  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Connection conn=null;
        PreparedStatement stat=null;

        try {
            conn = Utils.getConnection();
            stat = conn.prepareStatement("delete from student where sid=?");

            stat.setInt(1,Integer.parseInt(req.getParameter("sid")));

            stat.executeUpdate();

            resp.sendRedirect("select"); // 重定向

           // req.getRequestDispatcher("delete_success.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("delete_error.jsp");
           // req.getRequestDispatcher("delete_error.jsp").forward(req,resp);
        }finally{
            Utils.close(conn,stat);
        }
    }
}
