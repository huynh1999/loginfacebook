<%@page import="com.jwatgroupb.service.CustomUserDetailsService"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.jwatgroupb.util.SecurityUtils"%>
<%@include file="/common/taglib.jsp"%>

<title>Purchase History | E-Shopper</title>
<body>
	
	<table class="table table-striped" style="width: 70%; margin: auto">
		<thead>
			<tr>
				<th>#</th>
				<th>Receiver</th>
				<th>Address</th>
				<th>Order Date</th>
				<th>Status</th>
				<th>Net Amount</th>
				<th>Payment method</th>
				<th>Payment detail</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listBill}" var="bill" varStatus="loop">
			<tr>
				<td>${loop.count}</td>
				<td>${bill.receiver}</td>
				<td>${bill.address}</td>
				<td>${bill.createdDate}</td>
				<c:set var = "status" value = "${bill.status}"/>
				<c:if test="${status eq 1}">
							<td>Order Success</td>
				</c:if>
				<c:if test="${status eq 0}">
							<td>Order Rejected</td>
				</c:if>
				<td align="center">${bill.totalMoney}</td>
				<td align="center">${bill.paymentMethod}</td>
				<td align="center"><a href='<c:url value="/bill/${bill.billCode}"/>'><i class="fa fa-file-text" style="font-size:20px;"></i></a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table><br>
</body>