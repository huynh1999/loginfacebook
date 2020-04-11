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
	<section>
		<div class="container">
			<c:if test="${param.paymentId != null}">
					<div align="center">
				<div style="width: 500px">
					<h4>
						<b>Transaction Details:</b>
					</h4>
					<table class="table table-striped">
						<tbody>
							<tr>
								<td>Description:</td>
								<td>${transaction.description}</td>
							</tr>
							<tr>
								<td>Total:</td>
								<td>${transaction.amount.total}USD</td>
							</tr>
						</tbody>
					</table>
					<br />
					<h4>
						<b>Payer Information:</b>
					</h4>
					<table class="table table-striped">

						<tbody>
							<tr>
								<td>First Name:</td>
								<td>${payer.firstName}</td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td>${payer.lastName}</td>
							</tr>
							<tr>
								<td>Email:</td>
								<td>${payer.email}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</c:if>
			<div style="font-size: 20px; padding: 60px">
				<li>You have successfully paid.</li>
				<li>You can view the invoice details by visiting the link: <a
					href='<c:url value="/bill/${billCode}"/>' target="_blank"
					style="text-decoration: underline;">Here</a></li>
				<li>Any questions please contact us (<span>+84 123 456
						78</span>)
				</li>
			</div>
		</div>
	</section>
</body>
</html>