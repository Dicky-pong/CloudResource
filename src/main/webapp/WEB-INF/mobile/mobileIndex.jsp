<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 引入全局css样式 -->
<jsp:include page="/WEB-INF/reuse/css.jsp"/>
<script charset="utf-8" src="assets/js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="assets/js/mobile/global.js?v=01291"></script>
<script charset="utf-8" src="assets/js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="assets/js/mobile/template.js?v=01291"></script>

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
<title>首页</title>
</head>
<body>
<header class="header">
	<div class="fix_nav">
	<div style="max-width:768px;margin:0 auto;height: 44px;position: relative;background:#000000;">
		<form action="/m_search/list" method="get" id="searchform" name="searchform">
			<div class="navbar-search left-search">
					<input type="text" id="keyword" name="keyword" value="" placeholder="搜索商品" class="form-control">
			</div>
			<div class="nav-right">
				<input type="button" value="搜索" onclick="searchproduct();" class="img-responsive" style="text-align:center;background:#ccc;border-radius: 5px;border:none;height:34px;vertical-align:middle;clear:both;padding:0px;width:42px;"/>
			</div>
		</form>
	</div>
	</div>
</header>

<div class="container">
		    <div class="row">
        <div id="slide">
            <div class="hd">
                <ul><li class="on">1</li><li class="on">2</li><li class="on">3</li></ul>
            </div>
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="width: 3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="http://m.legendshop.cn/m_search/list?categoryId=36" target="_blank">
	   	                    		<img src="assets/img/mobile/0da8eb94-0159-4700-a6a5-bc007da5a853.jpg" alt="女包" ppsrc="assets/img/mobile0da8eb94-0159-4700-a6a5-bc007da5a853.jpg">
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="http://m.legendshop.cn/m_search/list?categoryId=38" target="_blank">
	   	                    		<img src="assets/img/mobile/61647775-f5d0-4cfe-b84f-96060eb8e2e3.jpg" alt="女鞋" ppsrc="assets/img/mobile61647775-f5d0-4cfe-b84f-96060eb8e2e3.jpg">
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="http://m.legendshop.cn/m_search/list?categoryId=38" target="_blank">
	   	                    		<img src="assets/img/mobile/efa1dc7b-1375-4876-acae-e344cae7d55c.jpg" alt="服装" ppsrc="assets/img/mobile/efa1dc7b-1375-4876-acae-e344cae7d55c.jpg">
	   	                    	</a>
	   	                    </li>
	                	</ul>
                </div>
            </div>
        </div>
    </div>
		
<script charset="utf-8" src="assets/js/mobile/TouchSlide.js"></script>

<script type="text/javascript">
	
	TouchSlide({
		slideCell:"#slide",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,//自动播放
		autoPage:true, //自动分页
		switchLoad:"_src" //切换加载，真实图片路径为"_src"
	});
	</script>
<div class="row category">
        <a href="/m_search/list?param.hot='Y'" class="col-xs-3">
            <img class="img-responsive" src="assets/img/mobile/icon_rm.png">
            <h4>热门</h4>
        </a>
        <a href="/m_search/list?param.commend='Y'" class="col-xs-3">
            <img class="img-responsive" src="assets/img/mobile/icon_tm.png">
            <h4>精品</h4>
        </a>
        <a href="/theme/allThemes" class="col-xs-3">
            <img class="img-responsive" src="assets/img/mobile/theme.png">
            <h4>专题列表</h4>
        </a>
        <a href="/brand" class="col-xs-3">
            <img class="img-responsive" src="assets/img/mobile/icon_pp.png">
            <h4>品牌</h4>
        </a>
    </div>
    
<div class="row">

		<!--产品块-->
		<div class="tb_box">
			<h2 class="tab_tit">
				<a class="more" href="http://m.legendshop.cn/m_search/list?categoryId=36">更多</a>
				礼品箱包</h2>
			
			<div class="tb_type tb_type_even clearfix"><a class="tb_floor" href="views.html">
								<img src="assets/img/mobile/c2abb958-aca8-42f2-be52-ee1c072caec0.jpg" style="width:100%;">
							</a>
						<a class="th_link" href="views.html">
								<img class="tb_pic" src="assets/img/mobile/e71099af-8a08-45f5-af16-4e8edf2f1bff.jpg" style="width:100%;">
	                		</a>
						<a class="th_link tb_last" href="views.html">
								<img class="tb_pic" src="assets/img/mobile/33c256a8-7bff-4c6b-8686-f209bbad1301.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		<!--产品块-->
		<div class="tb_box">
			<h2 class="tab_tit">
				<a class="more" href="http://m.legendshop.cn/m_search/list?categoryId=38">更多</a>
				服饰鞋帽</h2>
			
			<div class="tb_type clearfix"><a class="tb_floor" href="views.html">
								<img src="assets/img/mobile/0bbbb6bf-0d00-45c0-92f7-347377f2edb5.jpg" style="width:100%;">
							</a>
						<a class="th_link" href="views.html">
								<img class="tb_pic" src="assets/img/mobile/0d86960d-20b5-4dd3-afee-8453b5ea5e95.jpg" style="width:100%;">
	                		</a>
						<a class="th_link tb_last" href="views.html">
								<img class="tb_pic" src="assets/img/mobile/1e13498f-419d-4ebd-a3b2-d0ad95ceaa39.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		<!--产品块-->
		<div class="tb_box">
			<h2 class="tab_tit">
				<a class="more" href="http://m.legendshop.cn/m_search/list?categoryId=35">更多</a>
				数码办公</h2>
			
			<div class="tb_type tb_type_even clearfix"><a class="tb_floor" href="views.html">
								<img src="assets/img/mobile/bd6771d9-f220-454d-83b2-d58d2046d23a.jpg" style="width:100%;">
							</a>
						<a class="th_link" href="views.html">
								<img class="tb_pic" src="assets/img/mobile/04ef50d9-97f0-4fea-8359-ee21376df392.jpg" style="width:100%;">
	                		</a>
						<a class="th_link tb_last" href="views.html">
								<img class="tb_pic" src="assets/img/mobile/d07a93ef-cdff-4b21-a12b-d817890aa6d6.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		</div>
	 
</div>
<!-- 
<div class="foot_index">
	<div class="foot_index_tit">Aim Beauty Limited(HongKong)</div>
	<div class="foot_index_rx">服务热线：020-87774389</div>
</div>
-->
<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="index.html" class="active">
			<i class="navIcon home"></i>
			<span class="text">首页</span>
		</a>
		<a href="category.html">
			<i class="navIcon sort"></i>
			<span class="text">分类</span>
		</a>
		<a href="shopcart.html">
			<i class="navIcon shop"></i>
			<span class="text">购物车</span>		
		</a>
		<a href="userhome.html" >
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>

<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
});

function searchproduct(){
	   var keyword = document.getElementById("keyword").value;
	   if(keyword == undefined || keyword==null ||keyword ==""){
	     alert("请输入搜索关键字！");
	     return false;
	   }
	   document.getElementById("searchform").submit();
	}
</script>
</body></html>

