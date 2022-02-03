package food.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import food.app.connection.DbCon;
import food.app.dao.OrderDao;
import food.app.model.Cart;
import food.app.model.Order;
import food.app.model.User;


@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ; 
			
			Date date = new Date() ; 
			
			User auth = (User) request.getSession().getAttribute("auth") ; 
			if(auth != null) {
				
				String foodId = request.getParameter("id") ; 
				int foodQuantity = Integer.parseInt(request.getParameter("quantity")) ; 
				if(foodQuantity <= 0) {
					foodQuantity = 1 ; 
				}
				
				Order orderModel = new Order() ; 
				orderModel.setId(Integer.parseInt(foodId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(foodQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao orderDao = new OrderDao(DbCon.getConnection()) ; 
				boolean result = orderDao.insertOrder(orderModel) ;
				
				if(result) {
					
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cart_list != null) {
						for(Cart c : cart_list) {
							if(c.getId() == Integer.parseInt(foodId)) {
								cart_list.remove(cart_list.indexOf(c)) ;
								break  ;
							}
						}
					}
					
					response.sendRedirect("orders.jsp");
				}else {
					out.println("order failed") ; 
				}
			}else {
				response.sendRedirect("login.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
