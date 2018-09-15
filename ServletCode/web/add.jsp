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
    <script>
        var flag1=true;
        var flag2=true;
        var flag3=true;
        var flag4=true;

        function checksid(){
            var sid = document.getElementById("sid").value;
            var sidMsg = document.getElementById("sidMsg");
            var reg = /^\d+$/;
            if(sid.length==0){
                sidMsg.innerText="学号不能为空";
                flag1=false;
            }else if(!reg.test(sid)){
                sidMsg.innerText="必须输入数字(主键唯一)";
                flag1=false;
            }else{
                sidMsg.innerText="";
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

        function checksname(){
            var sname = document.getElementById("sname").value;
            var id = document.getElementById("snameMsg");
            if(sname.length==0){
                id.innerText="必须输入用户名";
                flag3=false;
            }else if(sname.length<2){
                id.innerText="必须输入大于1个字符的名字";
                flag3=false;
            }else{
                id.innerText="";
                flag3=true;
            }
            check();
        }

        function checksex(){
            var sex = document.getElementById("sex").value;
            var id = document.getElementById("sexMsg");

            if(sex.length ==0){
                id.innerText="请输入一个字符";
                flag4=false;
            }else if(sex.length!=1){
                id.innerText="只能输入一个字符";
                flag4=false;
            }else{
                id.innerText="";
                flag4=true;
            }

            check();

        }

        function check(){
            var submit = document.getElementById("submit");

            if(flag1 & flag2 & flag3 & flag4){
                submit.disabled=false;
            }else{
                submit.disabled=true;
            }
        }
    </script>

</head>
<body>
    <form action="/add" method="post">
        <table align="center"  width="50%">
            <tbody>
                <tr>
                    <td colspan="2" align="center">学生信息</td>
                </tr>
                <tr>
                    <td align="right">学号</td>
                    <td>
                        <input type="text" name="sid" id="sid"  onblur="checksid()">
                        <span id="sidMsg" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="right">姓名</td>
                     <td width="40%">
                         <input type="text" name="sname" id="sname" onblur="checksname()">
                         <span id="snameMsg" style="color: red"></span>
                     </td>
                </tr>
                <tr>
                    <td align="right">生日</td>
                    <td>
                        <input type="text" name="birthday" id="birthday" onblur="checkbirthday()">
                        <span id="birthdayMsg" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">性别</td>
                    <td>
                        <input type="text" name="sex" id="sex" onblur="checksex()">
                        <span id="sexMsg" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="添加" disabled="true" id="submit" >
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>

</html>
