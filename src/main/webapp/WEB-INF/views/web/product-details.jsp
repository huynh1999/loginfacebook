<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
</head><!--/head-->

<body>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
								<c:forEach items="${listCategory}" var="category">
								<div class="panel-group category-products" id="accordian">
									<!--category-products-->
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="<c:url value="/ProductCategory/${category.categoryName}" />" >${category.categoryName}</a>
											</h4>
										</div>
			
									</div>
								</div>
							</c:forEach>
						
						
						<!--/category-products-->
						
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img src='<c:url value="${product.url1}"/>' alt="" />
							
							</div>
							<div id="similar-product" class="carousel slide" data-ride="carousel">
								
								  <!-- Wrapper for slides -->
								   
								</div>

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<img src="images/product-details/new.jpg" class="newarrival" alt="" />
								<h2>${product.name}</h2>
								<p>Web ID: 1089772</p>
								<img src="images/product-details/rating.png" alt="" />
								<span>
									<span>$${product.price}</span>
									
									<a href="/cart/add/${product.id}" class="btn btn-default add-to-cart"><i
												class="fa fa-shopping-cart"></i>Add to cart</a>
								</span>
								<p><b>Availability:</b> ${product.amount}</p>
								<p><b>Condition:</b> New</p>
								<p><b>Brand:</b> E-SHOPPER</p>
								<a href=""><img src="images/product-details/share.png" class="share img-responsive"  alt="" /></a>
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
					
				

				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.add-to-cart').click(function() {
				alert("The product has been added to cart")
			});
		});
	</script>
</body>
</html>