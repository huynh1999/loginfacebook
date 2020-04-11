<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Edit User </h1>
	
        <form:form method="POST" action="saveEditUser" modelAttribute= "userEditForm">
             <table>
     
                <tr>
                    <td><form:label path="userName">User Name : ${userName}</form:label></td>
                    <td><form:hidden path="userName"/></td>
                </tr>
                <tr>
                    <td><form:label required="required" type = "email" path="email">Email</form:label></td>
                    <td><form:input required="required" type = "email" path="email"/></td>
                </tr>
                 <tr>
                    <td><form:label required="required" path="password">Password</form:label></td>
                    <td><form:input pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                     required="required" path="password"/></td>
                </tr>
                 <tr>
                    <td><form:label required="required" path="active">Active</form:label></td>
                    <td><form:input type="number" maxlength="1" step="1" min = "0" max = "1" required="required" path="active"/>    1:Active  || 0:Deactive</td>
                </tr>
                <tr>
                    <td><form:label required="required" path="roleUserEntity.id">Role User ID</form:label></td>
                    <td><form:input  type="number" maxlength="1" step="1" min = "1" max = "2" required="required" path="roleUserEntity.id"/>   1:Admin || 2:Customer</td>
                </tr>
                
                
               
               
                
                <tr>
                    <td><input type="submit" value="Save"/></td>
                </tr>
                </table>
        </form:form>

</body>
</html>