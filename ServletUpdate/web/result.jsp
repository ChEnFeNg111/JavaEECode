<%--
  Created by IntelliJ IDEA.
  User: 陈峰
  Date: 2018/9/11
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table  border="1" align="center">
    <tbody>
    <tr>
        <td colspan="2" align="center">
            学生信息
        </td>
    </tr>
    <tr>
        <td>学号</td>
        <td><input type="text"  name="sid" value="${s.sid}" readonly></td>
    </tr>
    <tr>
        <td>姓名</td>
        <td><input type="text" name="sname" value="${s.sname}" readonly></td>
    </tr>
    <tr>
        <td>生日</td>
        <td><input type="text" name="birthday" value="${s.birthday}" readonly></td>
    </tr>
    <tr>
        <td>性别</td>
        <td><input type="text" name="sex" value="${s.sex}" readonly></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
        <a href="select">主页面</a>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
