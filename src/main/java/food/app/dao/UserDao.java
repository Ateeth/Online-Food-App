package food.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import food.app.model.User;


public class UserDao {
	private Connection con ; 
	private String query ; 
	private PreparedStatement pst ; 
	private ResultSet rs ;
	
	public UserDao(Connection con) {
		this.con = con;
	} 
	
	public User userLogin(String email , String password) {
		User user = null ; 
		try {
			//sql injection prevented using preparedStatement
			query = "select * from users where email = ? and password = ?" ; 
			pst = this.con.prepareStatement(query) ; 
			pst.setString(1,email) ; 
			pst.setString(2, password);
			rs = pst.executeQuery() ; 
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name")) ; 
				user.setEmail(rs.getString("email")) ; 
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user ; 
	}
	
	
	 
	 public int registerUser(User user) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO users" +
	            "  ( name, email ,  password) VALUES " +
	            " ( ?, ?, ?);";

	        int result = 0;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "Ateeth@2001");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	        	//preparedStatement.setInt(1, user.getId());
	        	preparedStatement.setString(1, user.getName());
	            preparedStatement.setString(2, user.getEmail());
	            preparedStatement.setString(3, user.getPassword());
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	 
	
	
}
