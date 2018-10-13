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
    <style>
        .current { background-color: white; color: red; }
    </style>
</head>
<body>
    <form action="/select" method="post">
        <table border="1" align="center">
            <thead>
                <tr>
                    <td colspan="6" align="center">学生信息</td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td>姓名</td>
                    <td align="center">生日</td>
                    <td >性别</td>
                    <td colspan="2" ></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${p.list}" var="i">
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
                    <td colspan="4" align="center">
                        <a href="servletByID.jsp">按学号查询</a>
                    </td>
                </tr>

                <tr>
                    <td>
                        <a href="select?page=${p.first}">首页</a>
                        <%--          获取a标签传递的参数    --%>
                        <c:if test="${p.hasPre()}">
                            <a href="select?page=${p.pre}" >上一页</a>
                        </c:if>

                    </td>

                    <td colspan="3" align="center">

                        <c:forEach begin="${p.one}" end="${p.end}" var="i">
                            <c:if test="${p.isCurrent(i)}">
                                <span class="current">
                                        第${i}页
                                </span>
                            </c:if>
                            <c:if test="${!p.isCurrent(i)}">
                                <a href="select?page=${i}" >第${i}页</a>
                            </c:if>
                        </c:forEach>
                    </td>

                    <td>
                        <%--          获取a标签传递的参数    --%>
                        <c:if test="${(p.hasNext())}" >
                            <a href="select?page=${p.next}">下一页</a>
                        </c:if>
                        <a href="select?page=${p.last}">尾页</a>

                    </td>
                </tr>
                <tr>
                    <td colspan="6" align="center">
                         <span>
                            <input type="text" name="page" id="page" onkeyup="go()" placeholder="请输入1~${p.total}的数字">
                            <input type="submit" value="跳转" disabled="true" id="submit">
                        </span>
                    </td>
                </tr>
            <tr>
                <td colspan="6" align="center">
                        <span>
                            <span><a href="logout">注销</a></span>
                        </span>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</body>

<script>
    function go(){
        var a = document.getElementById("page").value;

        // 限制输入的内容只能是数字，而且不能超过总页数
        if(a!=null & a>0 & a<=${p.total} ){
            document.getElementById("submit").disabled=false;
        }else{
            document.getElementById("submit").disabled=true;
        }
    }
</script>

</html>