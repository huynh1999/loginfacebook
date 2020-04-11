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
	<h1> Add a new product</h1>

        <form:form method="POST" modelAttribute="product" action = "saveNewProduct">
             <table>
         
                <tr>
                    <td><form:label path="name"> Name of Product</form:label></td>
                    <td><form:input path="name"/></td>
                    
                </tr>
               <tr>
                    <td><form:label path="amount"> Amount </form:label></td>
                    <td><form:input path="amount"/></td>
                    
                </tr>
                 <tr>
                    <td><form:label path="price"> Price </form:label></td>
                    <td><form:input path="price"/></td>
                    
                </tr>
                 <tr>
                    <td><form:label path="promotion"> Promotion </form:label></td>
                    <td><form:input path="promotion"/></td>
                    
                </tr>
 				 <tr>
                    <td><form:label path="url1"> Product Image URL </form:label></td>
                    <td><form:input path="url1"/></td>
                    
                </tr>
           <tr>
                
					 <td><input type="submit" value="Save"/></td>
                   </tr>
            </table>
        </form:form>
<script type="text/javascript">
var i = 0;
function clicked(n) {
  
    var test = document.getElementById('amount_button');
    i = i + n;
    test.innerHTML = i;
};
window.onclick;
</script>
</body>
</html>