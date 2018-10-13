package login;

import utils.Utils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



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



            if(name.equals(n) && password==pwd){
                // 创建 session
                HttpSession session = req.getSession();

                // 存储登录标记
                session.setAttribute("isLogin",true);
                session.setAttribute("name",name);
                session.setAttribute("password",password);

                String pd = String.valueOf(password);

                Cookie c1 = new Cookie("name",name);
                Cookie c2 = new Cookie("password",pd);
                c1.setMaxAge(7*24*3600);// 一周
                c2.setMaxAge(7*24*3600);

                c1.setHttpOnly(true); // 不让用户使用 js 查看到我的密码
                c2.setHttpOnly(true);

                resp.addCookie(c1); // 添加进cookie作用域
                resp.addCookie(c2);

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
