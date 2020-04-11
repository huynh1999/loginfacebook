<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products List</title>
</head>


<body>

	<%-- <h2 align="center" style="color: green;" class="alert alert-info">Products List</h2>
	<br>
	<br>
	<br>
	<form action = "addproduct" >
					<input type="submit" value="Add new Product"></form> 
		 <form:form action="delete/product" >
		<table border="1" cellpadding="5">
		

			<tr align="center" style="background-color: silver;">
				<th><input type = "checkbox" onClick="toggle(this);checkSubmit(this)"/></th>
				<th align="center">Image</th>
				<th align="center">ID</th>
				<th align="center">Name</th>
				<th align="center">Amount</th>
				<th align="center">Price</th>
				<th align="center">Promotion</th>
			</tr>

			<tbody>
			
					 <c:forEach items="${listProduct}"  var ="product">
                     <tr>
                     <td> <input type = "checkbox" class="inputButton" id="checkbox" name = "checkbox" value="${product.id}"
                      onclick= "checkSubmit(this)"  /></td>
             	 	 <td> <img width="50" height="50"  src ="${product.url1}"/></td>
					 <td align="center"><c:out value= "${product.id}" ></c:out></td>
					 <td align="center"><c:out value= "${product.name}" ></c:out></td>
					 <td align="center"><c:out value= "${product.amount}" ></c:out></td>
					 <td align="center"><c:out value= "${product.price}" ></c:out></td>
           		  	 <td align="center"><c:out value= "${product.promotion} %" > </c:out></td>
           		     </tr>  
			
	                </c:forEach>
                	  </tbody>
				  
		</table>      	<input type="submit" name="del_event" id="del_event" disabled="disabled" value="Delete" class="button"  />
		<br>
						
		         </form:form>	 --%>
		         	
		          <form:form method="POST" modelAttribute="checkboxList" action="delete">
	<div class="container">
		<div class="table-wrapper" >
			<div class="table-title">
				<div class="row" >
					<div class="col-sm-6">
						<h2>Products List</h2>
					</div>
					<br>
					<div class="col-sm-6">
						<a href="<c:url value = "/admin/addproduct"/>" class="btn btn-success"><i class="material-icons">&#xE147;</i> 
						<span> Add New Product</span></a>
			 		
							
						<form ><div align="right" > <input type="submit" class = "btn-danger" 
								name="del_event" id="del_event" disabled="disabled"
								value="Delete" style ="background-color: #fa594d; border: none ; padding: 7px 7px;"/>
								</div> </form>
					</div>

				</div>

			</div>
		</div>
	
			<form method="POST" action="search">

			Name of Product <input type="text" name="keyword" id="keyword">
			<input type="submit" value="Search">
		</form>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th><input type="checkbox"
						onClick="toggle(this);checkSubmit(this)" /></th>
					<th align="left">Image </th>
					<th align="left">ID</th>
					<th align="left">Name</th>
					<th align="left">Amount</th>
					<th align="left">Price</th>
					<th align="left">Promotion</th>

				</tr>
			</thead>

			<tbody>

				<c:forEach items="${listProduct}" var="product">
					<tr>
						<td><input type="checkbox" class="inputButton" id="checkbox"
							name="checkbox" value="${product.id}" onclick="checkSubmit(this)" /></td>
						<td><img width="50" height="50" src="${product.url1}" /></td>
						<td align="left"><c:out value="${product.id}"></c:out></td>
						<td align="left"><c:out value="${product.name}"></c:out></td>
						<td align="left"><c:out value="${product.amount}"></c:out></td>
						<td align="left"><c:out value="${product.price}"></c:out></td>
						<td align="left"><c:out value="${product.promotion} %"></c:out>
						<td>
						<a href=<c:url value = "/admin/editProduct/${product.id} "/> class="edit"><img src="https://iconsetc.com/icons-watermarks/simple-yellow/raphael/raphael_pensil-edit/raphael_pensil-edit_simple-yellow_512x512.png" width ="20px" height="20px" class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</a>
						
							</td>
					</tr>
				
				</c:forEach>

			</tbody>

		</table>



		<br>
		

<c:forEach items = "${listPage}" varStatus ="listPage">
<div align = "center" class="pagination">
  <a href="${listPage.index+1}" class="active">${listPage.index+1}</a>
   </div>
</c:forEach>
 

		</div>	</form:form>

	<script>
		function checkSubmit(source) {
			x = document.getElementById('del_event');
			if (source.checked == true)
				x.disabled = false;
			else
				x.disabled = true;
		}
	</script>

	<script>
		function toggle(source) {
			checkboxes = document.getElementsByName('checkbox');
			for (var i = 0; i < checkboxes.length; i++) {
				if (checkboxes[i] != source)
					checkboxes[i].checked = source.checked;
			}
		}
	</script>

	
</body>
</html>