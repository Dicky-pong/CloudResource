/**
 * 手机商城主页
 */

var total = 0, pageIndex = 1, pageSize = 12;
var storeId, templateId;

/**
 * 下拉刷新
 */
function doRefresh() {
	window.sessionStorage.htmls = "";
	window.sessionStorage.scrollTop = "";
	window.sessionStorage.pageIndex = "";
	window.sessionStorage.total = "";
	total = 0, pageIndex = 1;
	loadGoodsDataList(storeId, templateId, 1, pageSize, true);
}

/**
 * 
 * 上拉加载
 */
function loadMore() {
	var currentPage = (total % pageSize == 0 ? total / pageSize : Math.ceil(total / pageSize));
	if(pageIndex > currentPage) return false;
	loadGoodsDataList(storeId, templateId, pageIndex, pageSize, false);
}

function loadGoodsDataList(storeId, templateId, pageIndex, pageSize, isRefresh) {
	$.ajax({
		url: basePath + "mobileMall/main/queryGoodsDatas.do",
		type : "POST",
		dataType: 'json',
		data: {
			storeId : storeId,
			storeTemplateId : templateId,
			pageIndex : pageIndex,
			pageSize : pageSize
		},
		success : function(data) {
			if(data.total) {
				total = data.total;
				writeGoodsDataDiv(data.goodsDataList, isRefresh);
			}
		},
	});
};

function writeGoodsDataDiv(datas, isRefresh) {
	if(!datas) {
		return false;
	}

	pageIndex = pageIndex + 1;
	
	content = "";
	$.each(datas, function(i, goodsData) {
		content += '<li onClick="onJumpDetail(this)" data-goodsId ="' + goodsData.goodsId + '" data-popularize="' + goodsData.popularizeId + '" data-mallGoodsId="'+ goodsData.id +'">';
		//是否有返利
		if(goodsData.isRebate == '1') {
			content += '<div class="pic_list_img rebate">';
		}else{
			content += '<div class="pic_list_img">';
		}
		
		content += '<img src="' + goodsData.goods.defaulGoodsImgUrl + '">';
		content += '</div>';
		
		//20170602判断是自营商品还是品牌商品 
		// index_brand :品牌
		// index_self :自营
		if(goodsData.popularizeId == '0') {
			if(goodsData.isBrand == '1') {
				content += '<p class="pic_list_title">'
				+ '<img style="height:1.5rem;vertical-align:top;margin-right:0.2rem" src="'+basePath+'/resources/images/index_brand.png">'
				+ '<img style="height:1.5rem;vertical-align:top;margin-right:0.2rem" src="'+basePath+'/resources/images/index_self.png">'
				+ goodsData.goods.title + '</p>';
			}else{
				content += '<p class="pic_list_title"><img style="height:1.5rem;vertical-align:top;margin-right:0.2rem" src="'+basePath+'/resources/images/index_self.png">'+ goodsData.goods.title + '</p>';
			}
			
		}else{
			if(goodsData.isBrand == '1') {
				content += '<p class="pic_list_title"><img style="height:1.5rem;vertical-align:top;margin-right:0.2rem" src="'+basePath+'/resources/images/index_brand.png">'+ goodsData.goods.title + '</p>';
			}else{
				content += '<p class="pic_list_title">'+ goodsData.goods.title + '</p>';
			}
		}
		
		
		//原:
		//content += '<p class="pic_list_title">'+ goodsData.goods.title + '</p>';
		//20170602判断是自营商品还是品牌商品 
		
		var money = goodsData.price;
		content += '<p class="pic_list_money">￥<span class="pic_list_money_text money">' + money.toFixed(2) + '</span></p></li>';
	});
	if($(".index_body_main .commodity_list #goodsDataList li").length > 0 && !isRefresh) {
		$(".index_body_main .commodity_list #goodsDataList li:last").after(content);
	}else{
		$(".index_body_main .commodity_list #goodsDataList li:gt(0)").remove();
		$(".index_body_main .commodity_list #goodsDataList li:first").after(content);
	}
}

function toBankView() {
	window.location.href = 'https://creditcard.cmbc.com.cn/wsonline/index/index.jhtml?tradeFrom=YX-SZYQL1&EnStr=66274A842504260110A5866540177281&jg=618000003&jgEnStr=9AE2FBFC6F5E91527976C6C01D96BF56';
}

//进入商品详情页面
function onJumpDetail(e) {
	var goodsId = $(e).attr("data-goodsId");
	var popularize = $(e).attr("data-popularize");
	var storeId = $("#storeId").val();
	
	window.sessionStorage.htmls = $(".index_body_main .commodity_list #goodsDataList").html();
	window.sessionStorage.scrollTop = $(".wrap_main").scrollTop();
	window.sessionStorage.pageIndex = pageIndex;
	window.sessionStorage.total = total;
	window.location.href = basePath + "mobileMall/main/template_0/toGoodsDetail.do?" +
			"goodsId="+ goodsId + "&storeId="+storeId + "&popularizeId="+popularize; 
}

//进入查询商品页面
function onJumpToQuery() {
	window.location.href = basePath + "mobileMall/main/template_0/toSearchGoods.do?templateId=" + templateId; 
}

///红包
function onOpenRedEnvelopes() {
	window.location.href = basePath + "mobileRedWallet/redWalletIndex.do";
}


//$(function() {
//	//关闭弹出框
//	$(".closePop").click(function(){
//		$(".bgshadow").hide();
//		$(".shadowBox").hide();
//	});
//	//弹出框验证
//	isOpenPopup();
//	//滚动到一定距离时固定搜索框	
//	var indexScrollTop=$(".index_body_nav").outerHeight(true)+$(".index_body_header").outerHeight(true),
//		index_search_wid=$(".index_search").outerWidth(true),
//		fonSize=parseInt($(".index_search").css("paddingLeft"));
//	
//	$(".index_body_nav").after($(".index_search").clone(true).css({
//		"zIndex":99,
//		"position":"fixed",
//		"width":index_search_wid-fonSize*2,
//		"top":0,
//		"left":0,
//		"display":"none"
//	}).addClass("scrollIndex_search"));
//	$(".wrap_main").scroll(function(){
//		if($(this).scrollTop()>=indexScrollTop){
//			$(".scrollIndex_search").show();
//		}else{
//			$(".scrollIndex_search").hide();
//		}
//	});
//	
//	
//	
//	
//	storeId = $("#storeId").val();
//	templateId = $("#templateId").val();
//	
//	//初始化页面
//	initView();
//	
//	pull_refresh(".wrap_main_box",".wrap_main",doRefresh, loadMore);
//	
//	floorCheck("mallIndex");
//});

//弹出框红包状态更改已通知
function updateWalletLogState(){
	var xfWalletId = $("#xfWalletId").val();
	var url_ = "${path}/app/StoreRedWallet/updateWalletLogState.do";
	$.ajax({
        type: "POST",
        url : url_,
        data: {id:xfWalletId},
        dataType: "json",
        async: false,
		success: function(data){
		},
		error:function(){
		}
    });
}

//弹出框显示接口
function isOpenPopup(){
	updateWalletLogState();
	var storeQrcode = $("#storeQrcode").val();
	var xfWalletAmount = $("#xfWalletAmount").val();
	$(".moneyPack").html(xfWalletAmount);
	if(storeQrcode) {
		$(".stepQrcode").html("<img src='" + storeQrcode+"'>");
	}
	if(!window.sessionStorage.openMember){
		//是否弹出框
		if($("#authorization").val()=="1" && $("#openMember").val()=="0" && $("#openStoreRedWallet").val()=="0"){
			$(".bgshadow").hide();
			$(".shadowBox").hide();
			
		}else{
			$(".bgshadow").show();
			$(".shadowBox").show();
		}
		//关注公众号
		if($("#authorization").val()=="1"){
			$(".ewm-code").hide();
		}else{
			$(".ewm-code").show();
		}
		//未认证会员
		if($("#openMember").val()=="1"){
			$(".stepTwo").show();
			$(".stepTwoWord").show();
		}else{
			$(".stepTwo").hide();
			$(".stepTwoWord").hide();
		}
		//未领取红包
		if($("#openStoreRedWallet").val()=="1"){
			$(".packBox").show();
		}else{
			$(".packBox").hide();
		}
		window.sessionStorage.openMember=true;
		
	}else{
		$(".bgshadow").hide();
		$(".shadowBox").hide();
	}
}

function myWchatHide(){
	$("#wchat_remind_cover").css("display","none");
	$("#wchat_remind").css("display","none");
	
}

function paySuccess(){
	var paySuccess='<div class="pay_success">\
							支付成功		 \
						<p>确认</p>\
					</div>';
	$("#wrap").append(paySuccess);
	$(".popupBg").show();
	
}

function closestPaySuccess(){
	$(".pay_success").remove();
	$(".popupBg,.popupDiv").hide();
}


$(document).on({
	"touchend":function(){
		closestPaySuccess();
		clearInterval(scanOrder);
		
	}
},".closeQrcode");

/**
 * 初始化时，检查session中是否存在缓存信息。有则直接显示
 */
function initView() {
	var href_ = window.location.href;
	if(href_.indexOf("state=Member_Mall") > -1) {
		if(window.sessionStorage.scrollTop) {
			onReadsessionStorage();
		}else{
			loadGoodsDataList(storeId, templateId, 1, pageSize);
			window.sessionStorage.htmls = "";
			window.sessionStorage.scrollTop = "";
			window.sessionStorage.pageIndex = "";
			window.sessionStorage.total = "";
		}
	}else{
		if(!window.sessionStorage.scrollTop) {
			loadGoodsDataList(storeId, templateId, 1, pageSize);
			window.sessionStorage.htmls = "";
			window.sessionStorage.scrollTop = "";
			window.sessionStorage.pageIndex = "";
			window.sessionStorage.total = "";
		}else{
			onReadsessionStorage();
		}
	}
}

function onReadsessionStorage() {
	$(".index_body_main .commodity_list #goodsDataList").html(window.sessionStorage.htmls);
	$(".wrap_main").scrollTop(window.sessionStorage.scrollTop);
	pageIndex = parseInt(window.sessionStorage.pageIndex);
	total = parseInt(window.sessionStorage.total);
}

