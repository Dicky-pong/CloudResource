<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>被拒绝的请求，你没有权限</title>
		<!-- 引入全局css样式 -->
   		<jsp:include page="/WEB-INF/reuse/css.jsp"/>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<script src="assets/js/jquery.js"></script>
   		<script src="assets/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/js/layer/layer.js"></script>
		<script src="assets/js/jquery.js"></script>
    	<script src="assets/js/bootstrap.min.js"></script>
	</head>
	<body data-target="#one" data-spy="scroll">
		
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<div class="col-md-12">
					<h2>系统提示</h2>
					<hr>
					<p>被拒绝的请求，你没有权限。请重新登录或者联系管理员！</p>
				</div>
			</div>
		</div>
			
	</body>
</html>