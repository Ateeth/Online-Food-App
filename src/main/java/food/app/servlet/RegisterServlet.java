package food.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import food.app.connection.DbCon;
import food.app.dao.UserDao;
import food.app.model.User;




@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp"); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			//int id = Integer.parseInt(request.getParameter("register-id")) ; 
			String name = request.getParameter("register-name") ; 
			String email = request.getParameter("register-email") ; 
			String password = request.getParameter("register-password") ; 
			
			UserDao userDao = new UserDao(DbCon.getConnection()) ; 
			User user = new User() ;
		//	user.setId(id) ; 
			user.setName(name) ; 
			user.setEmail(email) ; 
			user.setPassword(password) ; 
			try {
				userDao.registerUser(user) ;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			response.sendRedirect("login.jsp") ;
		}
	}
}
