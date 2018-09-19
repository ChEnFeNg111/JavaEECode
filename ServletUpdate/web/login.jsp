<%--
  Created by IntelliJ IDEA.
  User: 陈峰
  Date: 2018/9/18
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <form action="/login" method="post">
            <table align="center" border="1">
                <thead>
                    <tr>
                        <td colspan="2" align="center">登录</td>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="name" value="${sessionScope.name}" placeholder="请输入用户名" onblur="checkname(this)"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password" value="${sessionScope.password}" placeholder="请输入密码"  onblur="checkpwd(this)" ></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <span>
                            <input type="submit" value="登录"  disabled="true" id="submit">
                        </span>
                        <span>
                            <span><a href="logout">注销</a></span>
                        </span>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>

<script>
    var flag1 = false;
    var flag2 = false;

    function checkname(t){
        if(t.value.length !=0){
            flag1=true;
        }else{
            flag1=false;
        }

        this.check();
    }

    function checkpwd(t){

        if(t.value.length !=0){
           flag2=true;
        }else{
           flag2=false;
        }

        this.check();
    }


    function check(){
        var sb = document.getElementById("submit");
        if(flag1==true && flag2==true){
            sb.disabled=false;
        }else{
            sb.disabled=true;
        }
    }
</script>
</html>
