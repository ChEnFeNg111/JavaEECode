package login;

import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String n = req.getParameter("name");
        int pwd = Integer.parseInt(req.getParameter("password"));


        Connection conn=null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String name = null;
        int password = 0;

        try {
            conn = Utils.getConnection();
            stat = conn.prepareStatement("select name,password from users where name=? ");

           stat.setString(1,n);

            rs = stat.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
                password = rs.getInt("password");
            }

            if(name.equals(n) && password == pwd){
                // 创建 session
                HttpSession session = req.getSession();

                // 存储登录标记
                session.setAttribute("isLogin",true);
                session.setAttribute("name",name);
                session.setAttribute("password",password);

                // 登录成功 则 跳转到功能页面
                req.getRequestDispatcher("/select").forward(req,resp);

            }else{
                // 登录不成功 则 跳转到 登录页面重新登录
                System.out.println(name+"==="+password);
               resp.sendRedirect("login_error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.close(rs,conn,stat);
        }
    }
}
