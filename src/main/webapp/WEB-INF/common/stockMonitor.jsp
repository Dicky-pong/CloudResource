<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<script src="<%=basePath%>assets/js/jquery.js"></script>
		<script type="text/javascript" src="https://172.16.146.142:666/FinanceTimers/web/thaw/frozenMoney" charset="gb2312"></script>
	</head>
	<body>
	123
	<script>
	<%-- var elements=hq_str_sz002895.split(",");
	var stockName = elements[0];
	var currentPrice = elements[3];
	document.write(stockName+"\r"+"当前价格:"+currentPrice);
	if(currentPrice >= 23.0 || currentPrice <=22.5){
		 $.ajax({
			  type:"POST",
			  url:"<%=basePath%>stockMonitor/sendStockPriceMessage.htm",
			  data:{"stockName":stockName,"currentPrice":currentPrice},
			  cache:false,
			  success:function(data,status){
				 console.log(data);
			  },
			  error:function(xhr,status,ex){
				  console.log("失败");
			  }
		  });
	} --%>
	function myrefresh() 
	{ 
		window.location.reload(); 
	} 
	setTimeout('myrefresh()',5000); //指定60秒刷新一次 
    </script>	
	</body>
</html>