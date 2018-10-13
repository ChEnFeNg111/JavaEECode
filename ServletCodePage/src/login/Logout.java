package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();

        if(session.getAttribute("isLogin")!=null){
            // 销毁session
            session.invalidate();
            resp.sendRedirect("login.jsp");
        }else {
            req.getRequestDispatcher("logout.jsp").forward(req,resp);

        }


    }
}
