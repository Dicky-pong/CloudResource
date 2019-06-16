<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>股票监控</title>
<script type="text/javascript">
		var error = '';
</script>
</head>
<body class="" style="background-color:#fff">
<header class="header">
    <div class="fix_nav">
        <div style="max-width:768px;margin:0 auto;background:#fff222;position: relative;">
            <div class="tit" style="font-size:18px;">股票监控</div>
        </div>
    </div>
</header>
<div class="maincontainer">
   <div class="container itemdetail mini-innner">
    <div class="row">
        <div class="col-md-12 tal mt20">
            <form  id="theForm"  name="theForm" class="form-signin"  action="addStockRecord.htm" method="POST" >

              <input name="stockCode"   id="stockCode" type="text" style="margin-bottom: -2px;-webkit-appearance:none; " class="form-control" placeholder="股票编号" message="股票编号" required="true" autofocus=""  tabindex="1" >
              <br>
              <input id="lowPrice" name="lowPrice" type="text" class="form-control" placeholder="请输入监控最低价格" message="监控最低价格" required="true" style="-webkit-appearance:none;" autocomplete="off"  tabindex="2" >
                <br>
                <input id="highPrice" name="highPrice" type="text" class="form-control" placeholder="请输入监控最高价格" message="监控最高价格" required="true" style="-webkit-appearance:none;" autocomplete="off"  tabindex="2" >
                <br>
                <input id="email" name="email" type="text" class="form-control" placeholder="请输入邮箱号码" message="邮箱号码" required="true" style="-webkit-appearance:none;" autocomplete="off"  tabindex="2" >

                <div class="clear"></div>
                <div class="col-xs-6 p0"><button type="submit" class="btn btn-info btn-block"  tabindex="5" >启动监控</button></div>
            </form>
        </div>
     </div>
	</div>
</div>
<script type="text/javascript">
var contextPath = '';
</script>
</body>
</html>
