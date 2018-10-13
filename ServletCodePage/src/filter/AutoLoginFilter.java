package filter;

import utils.Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//检查是否有登录标记，没有就跳转到登录页面

@WebFilter(urlPatterns = "/select")
public class AutoLoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        //如果登陆,就自动登陆
        if (session.getAttribute("isLogin") != null) {
            //放行
           filterChain.doFilter(request,response);
           return;

        } else { // 如果登陆了,则继续判断cookie 里面有没有存储信息
            Cookie[] cookies = request.getCookies();
            Cookie nameC = null;
            Cookie pwdC = null;

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    nameC = cookie;
                }

                if (cookie.getName().equals("password")) {
                    pwdC = cookie;
                }
            }

            // 如果 cookie 为空,则返回登陆界面
            if(nameC == null && pwdC == null){
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }else{
                Connection conn = null;
                PreparedStatement stat = null;
                ResultSet rs = null;

                try {
                    conn = Utils.getConnection();
                    stat = conn.prepareStatement("select name,password from users where name=?");
                    stat.setString(1,nameC.getValue());

                    rs = stat.executeQuery();
                    String password1 = null;

                    if(rs.next()){
                         password1 = String.valueOf(rs.getInt("password"));
                        // 验证 cookie 中的用户名和密码 是否在数据库中存在
                        if(password1.equals(pwdC.getValue())){
                            // 如果验证通过,则放行
                            filterChain.doFilter(request,response);
                        }else{
                            request.getRequestDispatcher("/login.jsp").forward(request,response);
                        }
                    }else{
                        request.getRequestDispatcher("/login.jsp").forward(request,response);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    Utils.close(rs,conn,stat);
                }
            }

        }
    }

    @Override
    public void destroy() {

    }
}
