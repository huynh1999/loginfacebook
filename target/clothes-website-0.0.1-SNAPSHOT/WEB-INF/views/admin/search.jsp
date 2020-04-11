<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
</head>
<body>
<div align="left">
    <h2>Search Result</h2>
     <td>
					<form action = "1">
					<input  type="submit" value="List Users"></form> 
					</td> 
					<br>
					<br>
    <table border="1" cellpadding="5">
        <tr>
            <th align="center">ID</th>
            <th align="center">User Name</th>
            <th align="center">Password</th>
            <th align="center">E-mail</th>
            <th align="center">Active</th>
            <th align="right" >Role</th>
            
        </tr>
        <c:forEach items="${result}" var="user">
        <tr>
            <td align="center">${user.id}</td>
            <td align="center">${user.userName}</td>
            <td align="center">${user.password}</td>
            <td align="center">${user.email}</td>
            <td align="center">${user.active}</td>
            <td align="center"><c:out value="${user.roleUserEntity.roleName}"></c:out></td>
              <td>
					<a href=<c:url value = "/admin/editUser/${user.userName} "/> class="edit"><img src="https://iconsetc.com/icons-watermarks/simple-yellow/raphael/raphael_pensil-edit/raphael_pensil-edit_simple-yellow_512x512.png" width ="20px" height="20px" class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</a>
                    <a href="delete/${user.id}" class="delete"><img src="https://cdn1.iconfinder.com/data/icons/social-messaging-ui-color/254000/08-512.png" width ="25px" height="25px" class="material-icons"  class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</a>
			</td>
        </tr>
      
        </c:forEach>
    </table>
</div>   
</body>
</html>