<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${path}/assets/js/jquery.js"></script>
<%-- <script type="text/javascript" src="${path}/resources/js/jQueryUtil/jquery.qrcode.min.js?v=<%=ConstantUtil.version%>"></script>
<script type="text/javascript" src="${path}/resources/js/jQueryUtil/jquery-barcode.js?v=<%=ConstantUtil.version%>"></script> --%>
<script type="text/javascript" src="${path}/assets/js/mobile/mobileMallIndex.js"></script>
<link href="${path}/assets/css/mobile/activateM.css" rel="stylesheet">
<title></title>
<style rel="stylesheet">
	.phone_cover_layer{position: fixed;z-index: 9998;background: #000;opacity: 0.7;filter: alpha(opacity:70);top:0;left:0;bottom:0;right:0;overflow: auto; display: none;}
	.phone_pop_layer{position: fixed;z-index: 9999;min-width: 300px;min-height: 200px;display: none; padding: 0;width:100%;height:100%;}
	.wchat_remind_box{width:100%;height:100%;margin:0 auto;position:relative;}
	.wchat_remind_box_top{width:100%;height:auto;margin:0 auto;text-align:center;padding-top:5rem;}
	.wchat_remind_box_top img{width:60%;margin:0 auto;}
	.wchat_remind_box_close{width:80%;height:auto;margin:0 auto;text-align:right;padding-top:0rem;}
	.wchat_remind_box_close img{width:40px;margin:0 auto;}
	.wchat_remind_box_center{width:100%;height:auto;margin:0 auto;text-align:center;margin-top:0rem;}
	.wchat_remind_box_center img{width:50%;margin:0 auto;}
	.wchat_remind_box_bottom{width:100%;height:auto;margin:0 auto;text-align:center;position: absolute;bottom:7rem;}
	.wchat_remind_box_bottom img{width:90%;margin:0 auto;}
	.index_body_ico_box{width:4rem;height:4rem;margin:0 auto;text-align:center;line-height:4rem;overflow:hidden;}
	.index_body_ico_box img{height:2.3rem;margin:0.8rem auto 0.2rem;vertical-align:middle}
</style>
</head> 
<body>

	<!-- 
	*弹出层
	*-->
	<div class="bgshadow" style="display: none;"></div>
	<div class="shadowBox" style="display: none;">
		<div class="shadowSteptwo shadowStep">
			<div style="overflow: hidden;"><div class="shadowBoxImg1 chosebtn"><img class="closePop" src="${path }/assets/mobileImage/bg-chose.png"></div></div>
			<div class="shadowBoxImg packBox"><img src="${path }/assets/mobileImage/moneyCol1.png" style="width: 18%;display:inline-block"><div class="moneyPack">55</div><img src="${path }/assets/mobileImage/moneyCol2.png" style="width: 58%;display:inline-block"></div>
			<div class="shadowBoxImg stepTwoWord">
				<div>恭喜你，你已经成为我们的会员</div>
				<div>请激活会员享受更多福利</div>
			</div>
			<div class="shadowBoxImg stepTwo"><a style="display:block;height: 100%;" href="${memberRegisterUrl}"></a></div>
			<div class="shadowBoxImg stepQrcode ewm-code"><img src="${path }/assets/mobileImage/qr-code.png"></div>
			<div class="shadowBoxImg ewm-code"><img src="${path }/assets/mobileImage/step.png"></div>
		</div>
	</div>
	
<!--  *弹出层结束 -->
<%@ include file="/WEB-INF/mobile/common/header.jsp" %>
	<input type="hidden" id="templateId" value="${storeMallTemplate.id}">
	<input type="hidden" id="vipCardCode" value="${SESSION_USER_LOGIN_INFO.code}">
	<input type="hidden" id="memberState" value="${SESSION_USER_LOGIN_INFO.type}">
	<input type="hidden" id="authorization" value="${SESSION_USER_LOGIN_INFO.isSubscribe}">
	<input type="hidden" id="storeQrcode" value="${SESSION_STORE_MALL_INFO.qrcodeImg}">
	<input type="hidden" id="openMember" value="${openMember}">
	<input type="hidden" id="openStoreRedWallet" value="${openStoreRedWallet}">
	<input type="hidden" id="xfWalletAmount" value="${xfWallet.amount}">
	<input type="hidden" id="xfWalletId" value="${xfWallet.id}">
	<input type="hidden" id="member_hasPwd" value="${SESSION_USER_LOGIN_INFO.hasPwd}">
	
	<div class="phone_cover_layer" id="wchat_remind_cover" style="display:none;"></div>
	<div class="phone_pop_layer" id="wchat_remind" style="display:none;">
		<div class="wchat_remind_box">
			<div class="wchat_remind_box_top">
				<img src="${path }/assets/mobileImage/remindText.png" alt="">
			</div>
			<div class="wchat_remind_box_close">
				<img src="${path }/resources/images/mall/closeBtn.png" alt="" onclick="myWchatHide()">
			</div>
			<div class="wchat_remind_box_center">
			</div>
			<div class="wchat_remind_box_bottom">
				<img src="${path }/resources/images/mall/tips.png" alt="">
			</div>
		</div>
	</div>
	
	<div id="wrap" class="index_body">
		<div class="wrap_header"></div>
		<div class="wrap_main">
			<div class="wrap_main_box">
				<header class="index_body_header">
				<!-- 会员卡 -->
					<img src="${storeMallTemplate.vipCardImgUrl}">
					<p class="vip_class vip_text">
						<span class="vip_money">￥ ${SESSION_USER_LOGIN_INFO.balance}</span>
						<span class="vip_money">NO.${SESSION_USER_LOGIN_INFO.code}</span>
					</p>
					<p class="vip_class vip_title">
						 商家名称:${SESSION_STORE_MALL_INFO.storeName}
					</p>
				</header>
				<!-- 用户服务 -->
				<nav class="index_body_nav">
					<table>
						<tr>
							<td onclick="javascript:window.location.href='${path}/mobileMall/activity/toNoContent.do'">
								<div class="index_body_ico_box"><img src="${path}/assets/mobileImage/ind_nav_ico01.png"></div><span>本店</span>
							</td>
							<td onclick="javascript:window.location.href='${path}/mobileMall/recharge/toStoreMallRechargeView.do?source=1'">
								<div class="index_body_ico_box"><img src="${path}/assets/mobileImage/ind_nav_ico02.png"></div><span>充值</span>
							</td>
							<td onclick="javascript:window.location.href='${path}/mobile/memberQRCodePay/toStoreDefaultPayChannel.do'">
								<div class="index_body_ico_box"><img src="${path}/assets/mobileImage/ind_nav_ico03.png" style="height:3.5rem;margin-top:0.3rem"></div><span>付款</span>
							</td>
							<td onclick="javascript:window.location.href='${path}/mobileMall/activity/toCouponsActivity.do'">
								<div class="index_body_ico_box"><img src="${path}/assets/mobileImage/ind_nav_ico04.png"></div><span>活动</span>
							</td>
							<td onclick="onOpenRedEnvelopes()">
								<div class="index_body_ico_box"><img src="${path}/assets/mobileImage/ind_nav_ico05.png"></div><span>红包</span>
							</td>
						</tr>
					</table>
				</nav>
				
				<!-- 搜索 -->
				<div class="index_search" onclick="onJumpToQuery()">
					<!-- 商品分类 -->
					<div class="search_menu">
						<img src="${path}/resources/images/mall/seachbg.png">
						<!-- 分类 -->
					</div>
					<!-- 搜索关键字 -->
					<div class="search_text"><input type="text" class="search_text_input" placeholder="请输入商家或商品" disabled></div>
					<!-- 搜索按钮 -->
					<span class="search_btn">查询</span>
				</div>
				<main class="index_body_main">
					<div class="commodity_list">
						<!-- 商品列表 -->
						<ul id="goodsDataList">
							<li onclick="toBankView()">
								<div class="pic_list_img"><img src="${path}/assets/mobileImage/banner02.png"></div>
								<p class="pic_list_title">办民生信用卡，新用户送好礼</p>
								<p class="pic_list_money">广告</p>
							</li>
						</ul>
						<!-- 商品列表 End -->
						<div class="clear"></div>
					</div>
				</main>
			</div>
			<div class="goTopIco" onclick='$(".wrap_main").scrollTop(0);'>
				<img src="${path }/resources/images/mall/gotop.png">
			</div>
		<%@ include file="/WEB-INF/mobile/common/commonFooter.jsp" %>
	</div>
<!-- 
	*二维码弹出层
	*-->
	<div class='popupBg'></div>
	<div class='popupDiv popupDiv_code' style="padding: 14px 0 25px;">
		<div style="position:relative;height:1rem"><span class="closeQrcode"></span></div>
		<div id="qrcode"></div>
		<p></p>
		<div id="barcode"></div>
	</div>
	<!-- 
	*二维码弹出层结束
	 -->	
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
floorCheck("footer_list_index");
$(".index_body_nav li:lt(1)").on({
	"touchstart":function(){
		$(this).addClass("on");
	},
	"touchend":function(){
		$(this).removeClass("on");
	}
});

var defaultImage = '${SESSION_STORE_MALL_INFO.storeImg}';
/* if(!defaultImage) {
	defaultImage = yiql + "/images/logo_bg.png";
} */

var wxTitle = "发现一家好店，优惠不只一点点";

$(document).ready(function(){
	wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId: '${appId}', // 必填，公众号的唯一标识
		timestamp: '${timestamp}', // 必填，生成签名的时间戳
		nonceStr: '${nonceStr}', // 必填，生成签名的随机串
		signature: '${signature}',// 必填，签名，见附录1
		jsApiList: [
			'onMenuShareTimeline',
			'onMenuShareAppMessage',
			'hideAllNonBaseMenuItem',
			'showMenuItems'
		] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});	
});

//微信js-sdk中方法加载
wx.ready(function(){
	//隐藏所有非基础类接口
	wx.hideAllNonBaseMenuItem();
	
    wx.onMenuShareTimeline({
		title: wxTitle, // 分享标题
		link:  '${shareUrl}', // 分享链接
		//http://www.yidiandian.net/business_union/mobileRedWallet/redWalletAfterReceive.do?id=${redWallet.id}, 
		imgUrl: defaultImage, // 分享图标
		success: function () { 
			// 用户确认分享后执行的回调函数
		},
		cancel: function () { 
			// 用户取消分享后执行的回调函数
		}
	});
	
	wx.onMenuShareAppMessage({
		title: wxTitle, // 分享标题
	    desc: "【${SESSION_STORE_MALL_INFO.storeName}】经典甄选，样样精品，还有鼓励金送哦", // 分享描述
	    link:  '${shareUrl}', // 分享链接
	    imgUrl: defaultImage, // 分享图标
	    type: '', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
    
	//要显示的菜单项，所有menu项见附录3
    wx.showMenuItems({
		menuList: [
			'menuItem:share:appMessage',
			'menuItem:share:timeline'
		]
	});
});


</script>
</body>
</html>