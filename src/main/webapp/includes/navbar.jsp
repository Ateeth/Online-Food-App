<%@page import="java.util.*"%>
<%@page import="food.app.connection.DbCon"%>
<%@page import="food.app.model.*"%>
<%@page import="food.app.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}else{
	response.sendRedirect("login.jsp") ; 
}
%>

<nav class="navbar navbar-expand-lg ">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">Food Ordering App</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<%if( auth != null){%>
					<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
					<li class="nav-item active"><a class="nav-link" href="starter.jsp">Starters</a></li>
					<li class="nav-item active"><a class="nav-link" href="maincourse.jsp">Main Course Dishes</a></li>
					<li class="nav-item active"><a class="nav-link" href="dessert.jsp">Desserts and Beverages</a></li>
					<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger">${cart_list.size()}</span></a></li>
					<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
					<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
				<%}else{%>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
				<%}%>
			</ul>
		</div>
	</div>
</nav>