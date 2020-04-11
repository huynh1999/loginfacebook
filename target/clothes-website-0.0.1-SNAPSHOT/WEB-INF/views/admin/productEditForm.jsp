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
	<h1>Edit Product </h1>
	
        <form:form modelAttribute = "productEditForm"  method="POST" action="saveEditProduct">
             <table>
     			<tr>
                    <td><form:label path="id"> Product id ${id} </form:label></td>
                    <td><form:hidden path="id"/></td>
                </tr>
                <tr>
                    <td><form:label path="name"> Name</form:label></td>
                    <td><form:input path="name" required="required" /></td>
                </tr>
                <tr>
                    <td><form:label path="url1" >Image URL</form:label></td>
                    <td><form:input path="url1" required="required" /></td>
                </tr>
                 <tr>
                    <td><form:label path="amount">Amount</form:label></td>
                    <td><form:input  type="number" path="amount" required="required" /></td>
                </tr>
                 <tr>
                    <td><form:label path="price">Price</form:label></td>
                    <td><form:input type="number" path="price" required="required" /> </td>
                </tr>
                <tr>
                    <td><form:label path="promotion">Promotion </form:label></td>
                    <td><form:input  type="number" maxlength="3" min = "0" max = "100" required="required" path="promotion"/></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Save"/></td>
                </tr>
                </table>
        </form:form>

</body>
</html>