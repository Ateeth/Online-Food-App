<%@page import="java.util.*"%>
<%@page import = "food.app.model.*"  %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	User auth = (User) request.getSession().getAttribute("auth") ;  
	if( auth != null ){
		request.setAttribute("auth" , auth) ; 
	}
	
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list") ;
	if(cart_list != null){
		request.setAttribute("cart_list" , cart_list) ; 
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Registration Page</title>
		<%@include file = "includes/head.jsp"  %>
	</head>
	<body>
		<%@include file="includes/navbar.jsp"%>
		<div class = "container">
			<div class = "card w-50 mx-auto my-5">
				<div class = "card-header text-center">User Registration</div>
				<div class = "card-body">
					<form action = "user-register" method = "post">
						<!--  <div class = "form-group">
							<label>User ID</label>
							<input type = "number" class = "form-control" name = "register-id" placeholder = "Enter Your ID" required>
						</div> -->
						<div class = "form-group">
							<label>User Name</label>
							<input type = "text" class = "form-control" name = "register-name" placeholder = "Enter Your Username" required>
						</div>
						<div class = "form-group">
							<label>Email ID</label>
							<input type = "email" class = "form-control" name = "register-email" placeholder = "Enter Your EmailID" required>
						</div>
						<div class = "form-group">
							<label>Password</label>
							<input type = "password" class = "form-control" name = "register-password" placeholder = "**********" required>
						</div>
						<div class = "text-center">
							<button type = "submit" class = "btn btn-primary">Register</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<%@include file = "includes/footer.jsp" %>
	</body>
</html>