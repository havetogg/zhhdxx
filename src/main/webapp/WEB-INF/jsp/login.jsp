<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/22
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/backstage.css" />
    <title>登陆页</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/common.js"></script>
    <script type="text/javascript">
        function login() {
            if($("#name").val()==""||$("#pwd").val()==""){
                alert("请输入用户名或者密码");
                return;
            }
            $("form").attr("action",getRootPath()+"/user/login");
            $("form").submit();
        }
    </script>
</head>
<body>
<!--head-->
<div class="head"></div>
<div class="login">
    <div class="centerDiv">
        <c:if test="${!empty errorMessage}"><label class="tip">${errorMessage}</label></c:if>
        <form name="loginForm" action="" method="post">
            <p>
                <label>用户名</label>
                <input type="text" placeholder="请输入用户名" name="name" id="name"/>
            </p>
            <p>
                <label>密码</label>
                <input type="password" placeholder="请输入密码" name="pwd" id="pwd"/>
            </p>
            <a href="javascript:login();">登录</a>
        </form>
    </div>
</div>
</body>
</html>
