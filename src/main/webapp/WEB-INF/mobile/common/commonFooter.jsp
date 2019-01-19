<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.wrap_footer{background:#fff;height:49px;box-shadow:0 0px 6px 1px #e2e2e2;position:relative;box-sizing:border-box}
	.wrap_footer li{width:20%;float:left;text-align:center;font-size:12px !important;color:#5d5d5d}
	.wrap_footer li.on{color:#ff4949;}
	.wrap_footer li span{line-height:10px}
	.wrap_footer li em{position:relative;display:block;width:25px;height:25px;background:no-repeat top center/25px auto;margin:6px auto 3px !important;}
	.wrap_footer li em i{font-style:normal;position:absolute;right:-8px;font-size:12px;top:-4px;color:#fff;background:#ff4949;border-radius:50%;width:16px;height:16px;line-height:16px;overflow:hidden;text-align:center}
	.wrap_footer li.footer_list_index em{background-image:url(${path}/assets/mobileImage/footer/footer_list_index.png)}
	.wrap_footer li.footer_list_class em{background-image:url(${path}/assets/mobileImage/footer/footer_list_class.png)}
	.wrap_footer li.footer_list_cart em{background-image:url(${path}/assets/mobileImage/footer/footer_list_cart.png)}
	.wrap_footer li.footer_list_comm em{background-image:url(${path}/assets/mobileImage/footer/footer_list_comm.png)}
	.wrap_footer li.footer_list_my em{background-image:url(${path}/assets/mobileImage/footer/footer_list_my.png)}
	.wrap_footer li.on em{background-position:bottom center;}
</style>
<div class="wrap_footer clear_fix">
    <ul>
        <li class="footer_list_index" onclick="javascript:window.location.href='${path}/mobileMall/main/toStoreMallView.do'">
            <em></em>
            <span>首页</span>
        </li>
        <li class="footer_list_class" onclick="javascript:window.location.href='${path}/mobileMall/store/toClassifyGoodsView.do'">
            <em></em>
            <span>分类</span>
        </li>
        <li class="footer_list_cart" onclick="javascript:window.location.href='${path}/mobileMall/shoppingCart/template_0/queryShoppingCart.do'">
            <em>
            <c:if test="${cartNum > 0}">
            	<i>${cartNum}</i>
            </c:if>
            </em>
            <span>购物车</span>
        <li class="footer_list_comm" onclick="javascript:window.location.href='${path}/mobileMall/store/toStoreMsgView.do'">
            <em></em>
            <span>商户</span>
        </li>
        <li class="footer_list_my" onclick="javascript:window.location.href='${path}/mobileMall/member/toMemberMsg.do'">
            <em></em>
            <span>我的</span>
        </li>
    </ul>
</div>

<!-- 弹出一点点二维码关注 -->
<%-- <%@ include file="/support/ydd_attention.jsp" %>
 --%><script type="text/javascript">
//打开一点点二维码关注
$(".ico_focus").on({
	"touchend":function(){
		remindShow();
	}
});

function remindShow() {
	$(".zhlm_phone_cover_layer").show();
	$("#remind_shows").show();
}
function remindHide() {
	$(".zhlm_phone_cover_layer").hide();
	$("#remind_shows").hide();
}

function floorCheck(name) {
	$(".wrap_footer ."+name).addClass("on").siblings().removeClass("on");
};

</script>