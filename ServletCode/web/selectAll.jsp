<%--
  Created by IntelliJ IDEA.
  User: 陈峰
  Date: 2018/9/11
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--将 JSTL 标签库导入--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="" method="post">
        <table border="1" align="center">
            <thead>
                <tr>
                    <td colspan="5" align="center">学生信息</td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td>姓名</td>
                    <td align="center">生日</td>
                    <td colspan="2" align="center">性别</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${l}" var="i">
                    <tr >
                        <td>${i.sid}</td>
                        <td>${i.sname}</td>
                        <td>${i.birthday}</td>
                        <td>${i.sex}</td>
                        <td>
                            <a href="delete?sid=${i.sid}">删除</a>
                            <a href="update?sid=${i.sid}">修改</a>

                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" align="center">
                        <a href="add.jsp">添加</a>
                    </td>
                    <td colspan="3" align="center">
                        <a href="servletByID.jsp">按学号查询</a>
                    </td>
                </tr>

            </tbody>
        </table>
    </form>
</body>
</html>