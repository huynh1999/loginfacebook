<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>UserName</th>
			<th>Email</th>
		</tr>
	
	<c:forEach items="${listUser}" var="user">
	<tr>
		<td>${user.id}</td>
		<td>${user.userName}</td>
		<td>${user.email}</td>
	</tr>
	</c:forEach>
	
	</table>
</body>
</html>