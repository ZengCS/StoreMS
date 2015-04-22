<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>harmony_wisdom</title>
<%@ include file="/page/commons/common_include.jsp"%>
<script src="${pageContext.request.contextPath}/page/account/js/statisticalInfo.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/account/css/statisticalInfo.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin_style.css">
</head>
<body>
	<div class="div_title">
		<div class="div_title_img"><img src="/StoreMS/image/img/tb.gif" width="14" height="14" /></div>
		<div class="div_title_name">当前操作：统计信息</div>
	</div>
	<!-- <div class="toolbar">
		<button id="testB">测试</button>
	</div> -->
	<div id="tabs">
		<ul>
			<LI><A href="#tabs-1" id="tab1">今日盘点</A></LI>
			<LI><A href="#tabs-2" id="tab2">本月盘点</A></LI>
			<LI><A href="#tabs-3" id="tab3">自主盘点</A></LI>
		</ul>
		<div id="tabs-1" style="height: 390px;margin: 0;padding: 0;">
			<%@ include file="/page/account/statistical-1.jsp"%>
		</div>
		<div id="tabs-2" style="height: 390px;margin: 0;padding: 0;">
			<%@ include file="/page/account/statistical-2.jsp"%>
		</div>
		<div id="tabs-3" style="height: 390px;margin: 0;padding: 0;">
			<%@ include file="/page/account/statistical-3.jsp"%>
		</div>
	</div>
	<table cellpadding="1" cellspacing="1"></table>
</body>
</html>
