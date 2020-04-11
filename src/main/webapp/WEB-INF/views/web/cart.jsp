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
	<c:url var="home" value="/" scope="request" />
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Shopping Cart</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td></td>
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
							<tr class="cartItem_${cartitem.id}">
								<td class="cart_product"><a href=""><img
										src='<c:url value="${cartitem.productEntity.url1}"/>'
										style="width: 70px; height: 70px;"></a></td>
								<td class="cart_description">
									<h4>
										<a href="">${cartitem.productEntity.name}</a>
									</h4>
									<p>Web ID: 1089772</p>
								</td>
								<td class="cart_price">
									<p>
										$ <span id="price_${cartitem.id}">${price}</span>
									</p>
								</td>
								<td class="cart_quantity">
									<div class="cart_quantity_button">
										<a class="cart_quantity_down" href="" id="${cartitem.id}">
											- </a> <input class="cart_quantity_input"
											id="quantity_${cartitem.id}" type="text" name="quantity"
											value="${cartitem.quantity}" size="2"> <a
											class="cart_quantity_up" href="" id="${cartitem.id}"> + </a>
									</div>
								</td>
								<c:set var="pricetotal" value="${price*cartitem.quantity}"></c:set>
								<td class="cart_total">
									<p class="cart_total_price">
										$ <span id="totalprice_${cartitem.id}">${pricetotal}</span>
									</p>
								</td>
								<td class="cart_delete"><a class="cart_quantity_delete"
									href="" id="${cartitem.id}"><i class="fa fa-times"></i></a></td>
							</tr>
							<c:set var="total" value="${total+ pricetotal}" />


						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</section>
	<!--/#cart_items-->
	<section id="do_action">
		<div class="container">

			<div class="row">
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>$<span id="carttotal">${total}</span></span></li>
							<li>Shipping Cost <span>Free</span></li>
							<li>Total <span>$<span id="total">${total}</span></span></li>
						</ul>
						<a class="btn btn-default btn-lg check_out pull-right"
							href='<c:url value="/checkout"/>'>Check Out</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/#do_action-->
	<script type="text/javascript">
		$(document).ready(function() {
			$(".cart_quantity_up").click(function(e) {
				e.preventDefault(e);

				var id = $(this).attr("id");
				var quantityid = '#quantity_' + id;
				var priceid = '#price_' + id;
				var totalpriceid = '#totalprice_' + id;

				var price = parseInt($(priceid).text());
				var totalprice = parseInt($(totalpriceid).text());
				var total = parseInt($("#total").text());

				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "${home}cart/plus",
					data : {
						id : id
					},
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						var quantity = parseInt(data);
						$(quantityid).val(quantity);
						$(totalpriceid).text(totalprice + price);
						$('#carttotal').text(total + price);
						$('#total').text(total + price);
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			});

			$(".cart_quantity_down").click(function(e) {
				e.preventDefault(e);
				var id = $(this).attr("id");
				var quantityid = '#quantity_' + id;
				var priceid = '#price_' + id;
				var totalpriceid = '#totalprice_' + id;

				var price = parseInt($(priceid).text());
				var totalprice = parseInt($(totalpriceid).text());
				var total = parseInt($("#total").text());
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "${home}cart/down",
					data : {
						id : id
					},
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						var quantity = parseInt(data);
						$(quantityid).val(quantity);
						$(totalpriceid).text(totalprice - price);
						$('#carttotal').text(total - price);
						$('#total').text(total - price);
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			});

			$(".cart_quantity_delete").click(function(e) {
				e.preventDefault(e);
				var id = $(this).attr("id");
				var cartItemClass = ".cartItem_" + id;
				var totalpriceid = '#totalprice_' + id;
				var totalprice = parseInt($(totalpriceid).text());
				var total = parseInt($("#total").text());
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "${home}cart/remove",
					data : {
						id : id
					},
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						$("tr").remove(cartItemClass);
						$('#carttotal').text(total - totalprice);
						$('#total').text(total - totalprice);
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			});
		});
	</script>

</body>
</html>