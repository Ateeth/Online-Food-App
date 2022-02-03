<%@page import="java.util.*"%>
<%@page import="food.app.connection.DbCon"%>
<%@page import="food.app.dao.*"%>
<%@page import="food.app.model.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##") ; 
request.setAttribute("dcf" , dcf) ; 
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}else{
	response.sendRedirect("login.jsp") ; 
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list") ;
List<Cart> cartFood = null ; 
if(cart_list != null){
	FoodDao fDao = new FoodDao(DbCon.getConnection()) ; 
	cartFood = fDao.getCartFoods(cart_list) ; 
	double total = fDao.getTotalCartPrice(cart_list) ;
	request.setAttribute("cart_list" , cart_list) ; 
	request.setAttribute("total" , total) ; 
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Cart Page</title>
<%@include file="includes/head.jsp"%>
<style type = "text/css">
	.table tbody td{
		vertical-align = middle ; 
	}
	
	.btn-incre, .btn-decre{
		color : blue;
		box-shadow : none ; 
		font-size : 25px ; 
	}
</style>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price : ${(total > 0) ?dcf.format(total):0} Rs</h3>
			<a class="mx-3 btn btn-primary " href = "cart-check-out">Check Out</a>
		</div>
		<table class="table lable-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%if(cart_list != null){
				for(Cart c: cartFood){ %>
					<tr>
					<td><%=c.getName() %></td>
					<td><%=c.getCategory() %></td>
					<td><%=dcf.format(c.getPrice()) %>Rs</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre"  href="quantity-inc-dec?action=dec&id=<%=c.getId()%>">
									<i class="fas fa-minus-square"></i>
								</a>
								<input type="number" name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" readonly> 
								<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"> 
									<i class="fas fa-plus-square"></i>
								</a> 
							</div>
							<button type = "submit" class = "btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td>
						<a class = "btn btn-sm btn-danger" href = "remove-from-cart?id=<%=c.getId()%>">Remove</a>
					</td>
				</tr>	
			<%}
		}%>
				
			</tbody>
		</table>
	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>