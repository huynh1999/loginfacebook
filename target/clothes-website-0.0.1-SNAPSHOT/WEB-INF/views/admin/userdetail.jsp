<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>User Detail</h1>  
       <form:form method="POST" modelAttribute="usernamesearch"
        action = "edit/">    
        
          		<c:forEach items="${result}" var = "user">
        <table > 
        <tr>  </tr>
        <tr>  
        <td>Username:  ${user.username}</td>    
         </tr>  
       
					<tr> <td><form action = "edit/${user.username}">
					<input type="submit" value="Edit"></form> </td>  </tr>  
  
					
        </table> </c:forEach>  
       </form:form>    