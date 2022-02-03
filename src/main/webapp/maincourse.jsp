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

FoodDao md = new FoodDao(DbCon.getConnection()) ; 
List<MainCourse> maindishes = md.getAllMainCourseDishes() ; 

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list") ;
if(cart_list != null){
	request.setAttribute("cart_list" , cart_list) ; 
}
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Home Page</title>
		<%@include file="includes/head.jsp"%>
	</head>
	<body>
		<%@include file="includes/navbar.jsp"%>
	
		<div class="container">
			<div class="card-header my-3">All Main Course Dishes</div>
			<div class="row">
			<%if(!maindishes.isEmpty()){
				for(MainCourse m : maindishes){ %>
					<div class="col md-3 my-3">
					<div class="card" style="width: 18rem;">
						<img class="card-img-top fit-image image-responsive "  style = "height: 18rem;" src="food-images/<%= m.getImage() %>" alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%= m.getName() %></h5>
							<h6 class = "price">Price: <%= m.getPrice() %> Rs </h6>
							<h6 class = "category">Category: <%= m.getCategory() %></h6>
							<div class = "mt-3 d-flex justify-content-between">
								<a href="add-to-cart?id=<%=m.getId() %>" class="btn btn-dark">Add to cart</a>
								<a href="order-now?quantity=1&id=<%=m.getId() %>" class="btn btn-primary">Buy now</a>
							</div>
						</div>
					</div>
				</div>
				<%}
			}%>
			
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>