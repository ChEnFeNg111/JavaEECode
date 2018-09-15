<%--
  Created by IntelliJ IDEA.
  User: 陈峰
  Date: 2018/9/11
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.2.1.min.js"></script>

</head>
<body>
    <form action="/add" method="post">
        <table align="center" border="1">
            <tbody>
                <tr>
                    <td colspan="2" align="center">学生信息</td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td>
                        <input type="text" name="sid" id="sid" >
                        <span id="sidMsg" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="sname" ></td>
                </tr>
                <tr>
                    <td>生日</td>
                    <td><input type="text" name="birthday" ></td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td><input type="text" name="sex" ></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="提交">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>

<script>
    function checksid(){
        var reg=/^\d+$/;
        if($.trim(this.value.length)==0){
            $("#sidMsg").text("学号不能为空");
        }else if(!reg.test($.trim(this.value))){
            $("#sidMsg").text("必须输入数字（主键唯一）");
        }else{
            $("#sidMsg").text("");
        }
    }

    $("#sid").blur(checksid);

</script>
</html>
