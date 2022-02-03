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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp"); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String email = request.getParameter("login-email") ; 
			String password = request.getParameter("login-password") ; 
			
			UserDao udao = new UserDao(DbCon.getConnection()) ; 
			User user = udao.userLogin(email, password) ; 
			
			if(user != null) {
				request.getSession().setAttribute("auth", user) ; 
				response.sendRedirect("index.jsp") ;
			}else {
				out.print("User Login Fail Incorrect UserName / Password")  ;
			}
			//out.print(email + " " + password) ; 
		}
	}

}
