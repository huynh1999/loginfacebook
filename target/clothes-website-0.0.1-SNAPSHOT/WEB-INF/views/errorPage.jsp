<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<div class="container" align="center">

		<c:if test="${not empty errCode}">
			<h1>${errCode}: System Errors</h1>
		</c:if>

		<c:if test="${empty errCode}">
			<h1>System Errors</h1>
		</c:if>

		<c:if test="${not empty errMsg}">
			<h2>${errMsg}</h2>
		</c:if>
		
	</div>
</body>
</html>