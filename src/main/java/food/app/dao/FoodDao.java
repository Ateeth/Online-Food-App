package food.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import food.app.model.*;

public class FoodDao {
	private Connection con ; 
	private String query ; 
	private PreparedStatement pst ; 
	private ResultSet rs ;
	
	public FoodDao(Connection con) {
		this.con = con;
	}
	
	public List<Food> getAllFoods(){
		List<Food> foods = new ArrayList<Food>() ; 
		
		try {
			query = "select * from foods" ; 
			pst = this.con.prepareStatement(query) ; 
			rs = pst.executeQuery(); 
			
			while(rs.next()) {
				Food row = new Food() ; 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name")) ; 
				row.setCategory(rs.getString("category")) ;
				row.setPrice(rs.getDouble("price")) ;
				row.setImage(rs.getString("image"));
				
				foods.add(row) ; 
			}
		}catch(Exception e) {
			e.printStackTrace() ; 
		}
		return foods;		
	}
	
	public List<Cart> getCartFoods(ArrayList<Cart> cartList){
		List<Cart> foods = new ArrayList<Cart>() ; 
		try {
			if(cartList.size() > 0) {
				for(Cart item : cartList) {
					query = "select * from foods where id = ?" ; 
					pst = this.con.prepareStatement(query) ; 
					pst.setInt(1, item.getId());
					rs = pst.executeQuery(); 
					while(rs.next()) {
						Cart row = new Cart() ; 
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity()) ; 
						foods.add(row);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return foods ; 
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0 ;
		
		try {
			if(cartList.size() > 0) {
				for(Cart item : cartList) {
					query = "select price from foods where id=?" ; 
					pst = this.con.prepareStatement(query) ; 
					pst.setInt(1,  item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						sum += rs.getDouble("price")*item.getQuantity() ; 
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sum   ; 
	}
	
	public Food getSingleFood(int id) {
		Food row = null ; 
		
		try {
			query = "select * from foods where id = ?" ; 
			pst = this.con.prepareStatement(query) ;
			pst.setInt(1,  id);
			rs = pst.executeQuery() ; 
			
			while(rs.next()) {
				row = new Food() ; 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category")) ;
				row.setPrice(rs.getDouble("price")) ; 
				row.setImage(rs.getString("image")) ; 
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return row ; 
	}
	
	
	public List<Starter> getAllStarters(){
		List<Starter> starters = new ArrayList<Starter>() ; 
		
		try {
			query = "select * from starters" ; 
			pst = this.con.prepareStatement(query) ; 
			rs = pst.executeQuery(); 
			
			while(rs.next()) {
				Starter row = new Starter() ; 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name")) ; 
				row.setCategory(rs.getString("category")) ;
				row.setPrice(rs.getDouble("price")) ;
				row.setImage(rs.getString("image"));
				
				starters.add(row) ; 
			}
		}catch(Exception e) {
			e.printStackTrace() ; 
		}
		return starters;		
	}
	
	public List<MainCourse> getAllMainCourseDishes(){
		List<MainCourse> maindishes = new ArrayList<MainCourse>() ; 
		
		try {
			query = "select * from maincoursedishes" ; 
			pst = this.con.prepareStatement(query) ; 
			rs = pst.executeQuery(); 
			
			while(rs.next()) {
				MainCourse row = new MainCourse() ; 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name")) ; 
				row.setCategory(rs.getString("category")) ;
				row.setPrice(rs.getDouble("price")) ;
				row.setImage(rs.getString("image"));
				
				maindishes.add(row) ; 
			}
		}catch(Exception e) {
			e.printStackTrace() ; 
		}
		return maindishes;		
	}
	
	public List<Dessert> getAllDesserts(){
		List<Dessert> desserts = new ArrayList<Dessert>() ; 
		
		try {
			query = "select * from desserts" ; 
			pst = this.con.prepareStatement(query) ; 
			rs = pst.executeQuery(); 
			
			while(rs.next()) {
				Dessert row = new Dessert() ; 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name")) ; 
				row.setPrice(rs.getDouble("price")) ;
				row.setImage(rs.getString("image"));
				
				desserts.add(row) ; 
			}
		}catch(Exception e) {
			e.printStackTrace() ; 
		}
		return desserts;		
	}
}
