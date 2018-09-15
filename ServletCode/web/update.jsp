<%--
  Created by IntelliJ IDEA.
  User: 陈峰
  Date: 2018/9/11
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>

        var flag1=true;
        var flag2=true;
        var flag3=true;

        function checkname(){
            var sname = document.getElementById("sname").value;
            var id = document.getElementById("snameMsg");
            if(sname.length==0){
                id.innerText="必须输入用户名";
                flag1=false;
            }else if(sname.length<2){
                id.innerText="必须输入大于1个字符的名字";
                flag1=false;
            }else{
                id.innerText="";
                flag1=true;
            }
            check();
        }

        function checkbirthday() {
            var birthday = document.getElementById("birthday").value;
            var id = document.getElementById("birthdayMsg");

            var reg =/^(\d{4})-(\d{2})-(\d{2})$/;

            if(!reg.test(birthday)){
                id.innerText="请按照 'yyyy-MM-dd' 格式输入";
                flag2=false;
            }else{
                id.innerText="";
                flag2=true;
            }
            check();

        }

        function checksex(){
            var sex = document.getElementById("sex").value;
            var id = document.getElementById("sexMsg");

            if(sex.length !=1){
                id.innerText="请输入一个字符";
                flag3=false;
            }else{
                id.innerText="";
                flag3=true;
            }

            check();
        }

        function check(){
            var submit = document.getElementById("submit");

            console.log(flag1==true && flag2==true && flag3==true);

            if(flag1==true && flag2==true && flag3==true){
                submit.disabled=false;
            }else{
                submit.disabled=true;
            }
        }

    </script>
</head>
<body>
    <div id="update">
        <form action="/updateform" method="post">
            <table  align="center" width="50%">
                <tbody>
                <tr>
                    <td colspan="2" align="center">
                        学生信息
                    </td>
                </tr>
                <tr>
                    <td align="right">学号</td>
                    <td><input type="text"  name="sid" value="${s.sid}" readonly></td>
                </tr>
                <tr>
                    <td width="25%" align="right">姓名</td>
                    <td width="40%">
                        <input type="text" name="sname" value="${s.sname}" id="sname" onblur="checkname()">
                        <span id="snameMsg" style="color: red"></span>
                    </td>

                </tr>
                <tr>
                    <td align="right">生日</td>
                    <td>
                        <input type="text" name="birthday" value="${s.birthday}" id="birthday" onblur="checkbirthday()">
                        <span id="birthdayMsg" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">性别</td>
                    <td>
                        <input type="text" name="sex" value="${s.sex}" id="sex" onblur="checksex()">
                        <span id="sexMsg" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="修改"  disabled="true" id="submit">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>

</html>
