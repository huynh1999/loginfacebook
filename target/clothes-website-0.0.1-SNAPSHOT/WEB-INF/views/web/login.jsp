<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<section id="form">
		<!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form">
						<!--login form-->
						<h2>Login to your account</h2>

						<c:if test="${param.incorrectAccount != null}">
							<div class="alert alert-danger">Username or password
								incorrect</div>
						</c:if>
						<c:if test="${param.accessDenied != null}">
							<div class="alert alert-danger">You Don't Have Authorize</div>
						</c:if>

						<form action="<c:url value="/j_spring_security_check" />"
							method="POST">
							<input type="text" placeholder="User Name" name="j_username" />
							<input type="password" placeholder="Password" name="j_password" />
							<span> <a href="<c:url value='/forgetPassword' />"></a>
							</span>
							<button type="submit" class="btn btn-default">Login</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</div>
					<!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form">
						<!--sign up form-->
						<h2>New User Signup!</h2>
						<form:form action="registration" method="POST"
							modelAttribute="userForm" name="registerForm">


							<spring:bind path="userName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="userName" placeholder="User Name"
										autofocus="true"></form:input>
									<form:errors path="userName" style="color: orangered"></form:errors>
								</div>
							</spring:bind>
							<input type="text" placeholder="Full name" name="fullName" required="required" pattern="([A-zÀ-ž\s]){2,50}" title="Does not contain special characters"/>
							<spring:bind path="email">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="email" path="email" placeholder="Email"
										autofocus="true"></form:input>
									<form:errors path="email" style="color: orangered"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="password">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="password" path="password"
										placeholder="Password" id="password"></form:input>
									<form:errors path="password" style="color: orangered"></form:errors>
								</div>
							</spring:bind>

							<input type="password" id="passwordConfirm"
								placeholder="Confirm Password"></input>
							<div style="color: orangered" id="passwordConfirmError"></div>

							<button id="btnSubmit" type="submit" class="btn btn-default">Signup</button>
						</form:form>
					</div>
					<!--/sign up form-->
				</div>
			</div>
		</div>
	</section>
	<!--/form-->
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#btnSubmit").click(
							function() {

								var password = $("#password").val();
								var passwordConfirm = $("#passwordConfirm")
										.val();

								if (password != passwordConfirm) {
									$("#passwordConfirmError").text(
											"Password Confirm doesn't match");
									return false;
								}
							});
				});
	</script>
</body>
</html>