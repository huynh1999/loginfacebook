<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>

	<section id="cart_items">

		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Check out</li>
				</ol>
			</div>

			<!--/register-req-->

			<div class="shopper-informations">
				<div class="row">
					<div class="col-sm-4 clearfix">
						<div class="bill-to">
							<p>Bill To</p>
							<div class="form-one" style="width: 100%">
								<form:form id="form1" action="checkout" method="POST"
									modelAttribute="bill">
									<form:input id="email" path="email" type="text"
										placeholder="Email*" value="${user.email}" />
									<form:errors path="email" cssClass="error" />
									<form:input id="name" path="receiver" type="text"
										placeholder="Full Name *"
										value="${user.profileUserEntity.name}" />
									<form:errors path="receiver" cssClass="error" />
									<form:input id="address" path="address" type="text"
										placeholder="Address 1 *"
										value="${user.profileUserEntity.address}" />
									<form:errors path="address" cssClass="error" />
									<form:input id="phone" path="phonenumber" type="text"
										placeholder="Phone *"
										value="${user.profileUserEntity.phonenumber}" />
									<form:errors path="phonenumber" cssClass="error" />
									<br />
									<form:hidden id="method" path="paymentMethod" />
									<form:errors path="paymentMethod" cssClass="error" />
									<br />
									<form:hidden id="noteinput" path="note" />
									<form:hidden id="totalmoney" path="totalMoney" />
								</form:form>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="order-message" style="height: 250px">
							<p>Shipping Order</p>
							<textarea id="note" name="message"
								placeholder="Notes about your order, Special Notes for Delivery"
								style="height: 220px"></textarea>
							<form:errors path="note" cssClass="error" />
						</div>
						<div style="padding-top: 20px;"></div>
						<c:if test="${param.notLogin!= null}">
							<div class="alert alert-danger" style="margin-bottom: 0px;">
								You are not logged in. Please <a href='<c:url value="/login"/>'>Login</a>
							</div>
						</c:if>
						<a class="btn btn-primary"
							href="<c:url value="/checkout/getquotes"/>">Get Quotes</a>
						<button class="btn btn-primary" id="btn1">Refresh</button>

					</div>
				</div>
			</div>
			<div class="review-payment">
				<h2>Review & Payment</h2>
			</div>

			<div class="table-responsive cart_info" style="margin-bottom: 20px">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6" align="center"><c:if
									test="${cart.listCartItem.isEmpty()}">
									You have no items in your shopping cart.<a
										href='<c:url value="/HomePage"/>'
										style="text-decoration: underline;"> Back to shopping.</a>
								</c:if></td>
						</tr>

						<c:set var="total" value="0" />
						<c:forEach items="${cart.listCartItem}" var="cartitem">
							<c:set var="price"
								value="${cartitem.productEntity.price-cartitem.productEntity.price*cartitem.productEntity.promotion}" />

							<tr>
								<td class="cart_product"><a href=""><img
										src='<c:url value="${cartitem.productEntity.url1}"/>' style="width: 70px; height: 70px;"></a></td>
								<td class="cart_description">
									<h4>
										<a href="">${cartitem.productEntity.name}</a>
									</h4>
									<p>Web ID: 1089772</p>
								</td>
								<td class="cart_price">
									<p>$ ${price}</p>
								</td>
								<td class="cart_price">
									<p style="padding-left: 20px">${cartitem.quantity}</p>
								</td>
								<c:set var="pricetotal" value="${price*cartitem.quantity}"></c:set>
								<td class="cart_total">
									<p class="cart_total_price">$ ${pricetotal}</p>
								</td>
							</tr>
							<c:set var="total" value="${total+ pricetotal}" />
						</c:forEach>
						<tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2">
								<table class="table table-condensed total-result">
									<tr>
										<td>Cart Sub Total</td>
										<td>$${total}</td>
									</tr>

									<tr class="shipping-cost">
										<td>Shipping Cost</td>
										<td>Free</td>
									</tr>
									<tr>
										<td>Total</td>
										<td><span>$${total}</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-decoration: underline;"><i
								class="fa fa-arrow-left" style="color: #428bca"></i><a
								href='<c:url value="/cart"/>' style="padding-left: 5px;">Back
									to your cart</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<c:if test="${param.paymentFail != null}">
					<div class="alert alert-danger" align="center">Payment Fail. Please try again!</div>
			</c:if>
			<div style="float: right; padding-bottom: 30px;">
				<label>Choose Payment Method:</label> <label class="radio-inline"><input
					type="radio" name="optradio" value="COD">COD</label> <label
					class="radio-inline" style="padding-right: 40px"><input
					type="radio" name="optradio" value="PAYPAL">PAYPAL</label>
				<button class="btn btn-primary" id="btnpay" style="width: 250px;">PAY</button>
			</div>
			<form>
				<input id="total" type="hidden" value="${total}">
			</form>


		</div>
	</section>
	<!--/#cart_items-->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btn1').click(function() {
				$('#name').val('');
				$('#email').val('');
				$('#address').val('');
				$('#phone').val('');
				$('#note').val('');
			});
			$('#btnpay').click(function() {
				var selValue = $('input[name=optradio]:checked').val();
				$("#method").val(selValue);
				var note = $("#note").val();
				$("#noteinput").val(note);
				var totalmoney=$("#total").val();
				$("#totalmoney").val(totalmoney);
				if (confirm('Are you sure you want to pay for items?')) {
					$("#form1").submit();
				} else {
					// Do nothing!
				}
			});
		});
	</script>
</body>
</html>