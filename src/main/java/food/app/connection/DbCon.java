package food.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	private static Connection connection = null ; 
	
	public static Connection getConnection() {
		if( connection == null ) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "Ateeth@2001") ;
				System.out.println("connected") ; 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return connection;
	}
}
