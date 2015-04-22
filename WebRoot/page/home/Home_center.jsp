<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>仓库管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="icon/icon.ico" />
	
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		overflow:hidden;
	}
	-->
	</style>
	
  </head>
  
  <body>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="8" bgcolor="#353c44">&nbsp;</td>
	    <td width="147" valign="top">
	    <iframe height="100%" width="100%" border="0" frameborder="0" src="page/home/Home_left.jsp" name="leftFrame" id="leftFrame" title="leftFrame"></iframe></td>
	    <td width="10" bgcolor="#add2da">&nbsp;</td>
	    <td valign="top">
	    <iframe height="100%" width="100%" border="0" frameborder="0" src="page/home/Home_right.jsp" name="rightFrame" id="rightFrame" title="rightFrame"></iframe></td>
	    <td width="8" bgcolor="#353c44">&nbsp;</td>
	  </tr>
	</table>
  </body>
</html>
