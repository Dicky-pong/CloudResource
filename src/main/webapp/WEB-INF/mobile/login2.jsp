<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!-- 引入全局css样式 -->
    <jsp:include page="/WEB-INF/reuse/css.jsp"/>
    <jsp:include page="/WEB-INF/reuse/layerJs.jsp"/>
    <script charset="utf-8" src="assets/js/jquery.min.js?v=01291"></script>
    <script charset="utf-8" src="assets/js/mobile/global.js?v=01291"></script>
    <script charset="utf-8" src="assets/js/bootstrap.min.js?v=01291"></script>
    <script charset="utf-8" src="assets/js/mobile/template.js?v=01291"></script>
    <script charset="utf-8" src="assets/js/mobile/login.js?v=01291"></script>

    <link rel="stylesheet" href="assets/css/mobile/member.css?v=01291">
    <link rel="stylesheet" href="assets/css/mobile/style.css?v=01291">
    <link rel="stylesheet" href="assets/css/mobile/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no" name="format-detection">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
    <title>会员登录</title>
    <script type="text/javascript">
        var error = '';
    </script>
</head>
<body class="" style="background-color:#fff">
<header class="header">
    <div class="fix_nav">
        <div style="max-width:768px;margin:0 auto;background:#000000;position: relative;">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit" style="font-size:18px;">会员登陆</div>
        </div>
    </div>
</header>
<div class="maincontainer">
    <div class="container itemdetail mini-innner">
        <div class="row">
            <div class="col-md-12 tal mt20">
                <form  id="theForm"  name="theForm" class="form-signin"  action="mobile/index.htm" method="POST" >

                    <input name="userName"   id="userName" type="text" style="margin-bottom: -2px;-webkit-appearance:none; " class="form-control" placeholder="帐户名/手机号码" message="账号" required="true" autofocus=""  tabindex="1" >
                    <br>
                    <input id="password" name="password" type="password" class="form-control" placeholder="请输入密码" message="密码" required="true" style="-webkit-appearance:none;" autocomplete="off"  tabindex="2" >
                    <label class="checkbox">
                        <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox"   tabindex="4" /> 记住我
                        <a href="/forget" style="float:right">忘记密码</a>
                    </label>
                    <div class="clear"></div>
                    <div class="col-xs-6 p0"><button type="button" class="btn btn-info btn-block" onclick="userLogin();"  tabindex="5" >登  陆</button></div>
                    <div class="col-xs-5 p0" style="margin-left:10px;"><button type="button" class="btn btn-default btn-block" onclick="window.location.href='/register'"  tabindex="6" >注 册</button></div>
                </form>

                <form  id="register"  name="theForm" class="form-signin"  action="mobile/index.htm" method="POST" >

                    <input name="userName"   id="userName" type="text" style="margin-bottom: -2px;-webkit-appearance:none; " class="form-control" placeholder="帐户名/手机号码" message="账号" required="true" autofocus=""  tabindex="1" >
                    <br>
                    <input id="password" name="password" type="password" class="form-control" placeholder="请输入密码" message="密码" required="true" style="-webkit-appearance:none;" autocomplete="off"  tabindex="2" >
                    <label class="checkbox">
                        <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox"   tabindex="4" /> 记住我
                        <a href="/forget" style="float:right">忘记密码</a>
                    </label>
                    <div class="clear"></div>
                    <div class="col-xs-5 p0" style="margin-left:10px;"><button type="button" class="btn btn-default btn-block" onclick="window.location.href='/register'"  tabindex="6" >注 册</button></div>
                </form>
            </div>
        </div>
    </div>
</div>


<%--<div id="register">--%>
    <%--<span>推浪网账号注册</span>--%>
    <%--<form  method="post">--%>
        <%--<ul>--%>
            <%--<p id="err_msg"></p>--%>
            <%--<li><i class="un"><img src="images/user_name.png"></i><input class="username" type="text" placeholder="请输入用户名" /></li>--%>
            <%--<li><i class="yz"><img src="images/msg.png"></i><input class="yzm" type="text" placeholder="请输入手机验证码" /><input type="button" id="send" value="获取验证码" /></li>--%>
            <%--<li><i class="pw"><img src="images/pwd.png"></i><input class="pwd" type="password" placeholder="请输入密码" /></li>--%>
            <%--<li><i class="pw2"><img src="images/pwd.png"></i><input class="pwd2" type="password" placeholder="请输入确认密码" /></li>--%>
            <%--<div class="queren"><input class="fx" type="checkbox" checked="checked" /><p>我已阅读并同意《用户协议》</p></div>--%>
        <%--</ul>--%>
        <%--<div class="reg_btn">--%>
            <%--<button class="submit" type="submit">注册</button>--%>
            <%--<a href="login.html"><div class="reg-login"><p>已有帐号，立即登陆</p></div></a>--%>
        <%--</div>--%>
    <%--</form>--%>
<%--</div>--%>

<script type="text/javascript">
    $("#theForm").hide();
    $("#register").show();
</script>
</body>
</html>
