package utils; /**
 *  JDBC工具类:  连接数据库
 */
import java.sql.*;

public class Utils {

    static final String url="jdbc:mysql://localhost:3306/school?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
    static final String username="root";
    static final  String password="123";

    static{
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        //获取连接
        return DriverManager.getConnection(url,username,password);
    }

    //关闭资源
    public static void close(ResultSet rs , Connection conn, PreparedStatement pa ){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(pa!=null){
            try {
                pa.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(Connection conn, PreparedStatement ps){
        Utils.close(null,conn,ps);
    }
}
