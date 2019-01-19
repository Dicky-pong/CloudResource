<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="alert_cover" style="display: none;"></div>
<input type="hidden" id="storeId" value="${SESSION_USER_LOGIN_INFO.merId}">
<input type="hidden" id="memberWxId" value="${SESSION_USER_LOGIN_INFO.wxId}">
<input type="hidden" id="memberUUID" value="${SESSION_USER_LOGIN_INFO.UUID}">
<input type="hidden" id="storeNameTitle" value="${SESSION_STORE_MALL_INFO.templateName}">
<input type="hidden" id="memberPhone" value="${SESSION_USER_LOGIN_INFO.phone}">

<!-- 拦截全局js执行 -->
<script type="text/javascript">
$(function() {
	document.title=$("#storeNameTitle").val();
});
</script>