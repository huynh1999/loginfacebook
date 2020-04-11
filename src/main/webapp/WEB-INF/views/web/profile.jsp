<%@page import="com.jwatgroupb.service.CustomUserDetailsService"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.jwatgroupb.util.SecurityUtils"%>
<%@include file="/common/taglib.jsp"%>

<title>Profile | E-Shopper</title>
<body>
	<div style="width: 40%; text-align: center; margin: auto; font-size: 20px; color: orangered">
		<strong id="requestError" style="font-size: 20px; color: orangered">${requestError}</strong>
		<strong id="requestSuccess" style="font-size: 20px; color: green">${requestSuccess}</strong>
	</div><br>
	
	<div style="width: 40%; margin: auto">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
			<li><a href="#UpdateInfomation" data-toggle="tab">Update
					Information</a></li>
			<li><a href="#newpassword" data-toggle="tab">Change Password</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active in" id="home" style="margin: auto">
				<form id="tab">
			
			                <label style="padding: 10px 0 5px 0">Full Name</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getName())%>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Email</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.getPrincipal().getEmail()%>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Birthday</label>
			                <br>
			                <input type="text" value="<fmt:formatDate type="date" value = "<%=SecurityUtils.getPrincipal().getBirthday()%>"/>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Phone Number</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getPhonenumber())%>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Address</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getAddress())%>" style="width: 100%; height: 70px" readonly>
			                <br>
			                <br>
			            </form>
			</div>
			<div class="tab-pane fade" id="UpdateInfomation">
				<form:form id="tab2" action="addInfo" method="POST" modelAttribute="infoForm" name="UpdateInfomation">
			
			                <label style="padding: 10px 0 5px 0">Full Name</label>
			                <br>
			                <form:input path="name" name="name" type="text" placeholder="New name" style="width: 100%; height: 40px"></form:input>
			                <form:errors id="error" path="name" style="color: orangered"></form:errors>
			                <br>
			
			                <label style="padding: 10px 0 5px 0">Birthday</label>
			                <br>
			                <form:input path="birthday" name="birthday" type="date" placeholder="yyyy-mm-dd" style="width: 100%; height: 40px"></form:input>
			                <form:errors id="error" path="birthday" style="color: orangered"></form:errors>
			                <br>
			
			                <label style="padding: 10px 0 5px 0">Phone Number</label>
			                <br>
			                <form:input path="phonenumber" name="phonenumber" type="number" placeholder="New phone number" style="width: 100%; height: 40px"></form:input>
			                <form:errors id="error" path="phonenumber" style="color: orangered"></form:errors>
			                <br>
			
			                <label style="padding: 10px 0 5px 0">Address</label>
			                <br>
			                <form:input path="address" name="address" type="text" placeholder="New address" style="width: 100%; height: 70px"></form:input>
			                <form:errors id="error" path="address" style="color: orangered"></form:errors>
			                <br>
			
			                <div>
			                    <div style="text-align: center"><button class="btn btn-primary" id="btnSubmit">Update</button></div>
			                </div>
			                <br>
			            </form:form>
			</div>

			<div class="tab-pane fade" id="newpassword">
				<form id="tab3" action="changepassword" method="POST">
			
			                <label style="padding: 10px 0 5px 0">Confirm your current password
			                </label>
			                <br>
			                <input type="password" name="oldPasswordConfirm" id="oldPasswordConfirm" style="width: 100%; height: 40px" required="required"></input>
			                <div style="color: orangered">${oldPasswordConfirmError}</div>
			
			                <label style="padding: 10px 0 5px 0">New password</label>
			                <br>
			                <input type="password" name="password" id="password" style="width: 100%; height: 40px" required="required">
			                <label style="padding: 10px 0 5px 0">Confirm new password</label>
			                <br>
			                <input type="password" name="passwordConfirm" id="passwordConfirm" style="width: 100%; height: 40px" required="required">
			                <br>
			                <div style="color: orangered" id="passwordConfirmError"></div>
			
			                <div>
			                    <div style="text-align: center"><button id="btnSubmit2" type="submit" class="btn btn-primary">Update</button></div>
			                </div>
			                <br>
			            </form>
			</div>
		</div>
	</div>
</body>
















    <%-- <table class="table table-bordered" style="width: 70%; margin: auto">
        <thead>
            <tr>
                <th style="width: 30%">
                    <h3 class="or" style="width: 120px; font-size: 25px; margin: auto">Profile</h3></th>
                <th style="width: 30%">
                    <h1 class="or" style="width: 300px; font-size: 25px; margin: auto">Update
						Information</h1></th>
                <th style="width: 30%">
                    <h1 class="or" style="width: 300px; font-size: 25px; margin: auto">Change
						password</h1></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
			         <div style="margin: auto">
			            <form id="tab">
			
			                <label style="padding: 10px 0 5px 0">Full Name</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getName())%>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Email</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.getPrincipal().getEmail()%>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Birthday</label>
			                <br>
			                <input type="text" value="<fmt:formatDate type="date" value = "<%=SecurityUtils.getPrincipal().getBirthday()%>"/>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Phone Number</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getPhonenumber())%>" style="width: 100%; height: 40px" readonly>
			                <br>
			                <label style="padding: 10px 0 5px 0">Address</label>
			                <br>
			                <input type="text" value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getAddress())%>" style="width: 100%; height: 70px" readonly>
			                <br>
			                <br>
			            </form>
			        </div>
                </td>
                <td>
			        <div>
			            <form:form id="tab2" action="addInfo" method="POST" modelAttribute="infoForm">
			
			                <label style="padding: 10px 0 5px 0">Full Name</label>
			                <br>
			                <form:input path="name" type="text" placeholder="New name" style="width: 100%; height: 40px"></form:input>
			                <form:errors id="error" path="name" style="color: orangered"></form:errors>
			                <br>
			
			                <label style="padding: 10px 0 5px 0">Birthday</label>
			                <br>
			                <form:input path="birthday" type="date" placeholder="yyyy-mm-dd" style="width: 100%; height: 40px"></form:input>
			                <form:errors id="error" path="birthday" style="color: orangered"></form:errors>
			                <br>
			
			                <label style="padding: 10px 0 5px 0">Phone Number</label>
			                <br>
			                <form:input path="phonenumber" type="number" placeholder="New phone number" style="width: 100%; height: 40px"></form:input>
			                <form:errors id="error" path="phonenumber" style="color: orangered"></form:errors>
			                <br>
			
			                <label style="padding: 10px 0 5px 0">Address</label>
			                <br>
			                <form:input path="address" type="text" placeholder="New address" style="width: 100%; height: 70px"></form:input>
			                <form:errors id="error" path="address" style="color: orangered"></form:errors>
			                <br>
			
			                <div>
			                    <div style="text-align: center"><button class="btn btn-primary" id="btnSubmit">Update</button></div>
			                </div>
			                <br>
			            </form:form>
			      </div>
				</td>
                <td>
			         <div>
			            <form id="tab3" action="changepassword" method="POST">
			
			                <label style="padding: 10px 0 5px 0">Confirm your current password
			                </label>
			                <br>
			                <input type="password" name="oldPasswordConfirm" id="oldPasswordConfirm" style="width: 100%; height: 40px" required="required"></input>
			                <div style="color: orangered">${oldPasswordConfirmError}</div>
			
			                <label style="padding: 10px 0 5px 0">New password</label>
			                <br>
			                <input type="password" name="password" id="password" style="width: 100%; height: 40px" required="required">
			                <label style="padding: 10px 0 5px 0">Confirm new password</label>
			                <br>
			                <input type="password" name="passwordConfirm" id="passwordConfirm" style="width: 100%; height: 40px" required="required">
			                <br>
			                <div style="color: orangered" id="passwordConfirmError"></div>
			
			                <div>
			                    <div style="text-align: center"><button id="btnSubmit2" type="submit" class="btn btn-primary">Update</button></div>
			                </div>
			                <br>
			            </form>
			        </div>
				</td>
            </tr>
        </tbody>
    </table>
    <br> --%>
    <script type="text/javascript">
        $(document).ready(
            function() {
                $("#btnSubmit2").click(
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