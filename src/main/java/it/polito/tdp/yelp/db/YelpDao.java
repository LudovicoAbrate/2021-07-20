package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.yelp.model.Adiacenza;
import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Review;
import it.polito.tdp.yelp.model.User;
import it.polito.tdp.yelp.model.Vertici;

public class YelpDao {
	
	public List<Adiacenza> getAdiacenze( Year anno,Integer n){
		
		String sql="select r1.user_id as us1, r2.user_id as us2, COUNT(r1.business_id = r2.business_id) as peso "
				+ "from users u1,users u2, reviews r1, reviews r2 "
				+ "where r1.user_id < r2.user_id "
				+ "and u1.user_id = r1.user_id "
				+ "and u2.user_id = r2.user_id "
				+ "and r1.business_id = r2.business_id "
				+ "and YEAR(r1.review_date) = ? "
				+ "and  YEAR(r1.review_date) =  YEAR(r2.review_date) "
				+ "Group by us1,us2";
		
		List<Adiacenza> result = new ArrayList<Adiacenza>();
		Connection conn = DBConnect.getConnection();
		

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,anno.getValue() );
			ResultSet res = st.executeQuery();
			while (res.next()) {

				 Adiacenza a= new Adiacenza((res.getString("us1")),(res.getString("us2")),(res.getInt("peso")));
						
				result.add(a);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Vertici> getVertici(Integer n){
		String sql="select distinct u.user_id as id, u.name as name, COUNT( r.review_text)as recensioni "
				+ "from users u,reviews r  "
				+ "where u.user_id = r.user_id "
				
				+ "Group by id,name";
		
		List<Vertici> result = new ArrayList<Vertici>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				 Vertici v = new Vertici(res.getString("id"),res.getString("name"),res.getInt("recensioni")); 
					if(v.getRecensioni() >= n)	
				result.add(v);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Business> getAllBusiness(){
		String sql = "SELECT * FROM Business";
		List<Business> result = new ArrayList<Business>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Business business = new Business(res.getString("business_id"), 
						res.getString("full_address"),
						res.getString("active"),
						res.getString("categories"),
						res.getString("city"),
						res.getInt("review_count"),
						res.getString("business_name"),
						res.getString("neighborhoods"),
						res.getDouble("latitude"),
						res.getDouble("longitude"),
						res.getString("state"),
						res.getDouble("stars"));
				result.add(business);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Review> getAllReviews(){
		String sql = "SELECT * FROM Reviews";
		List<Review> result = new ArrayList<Review>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Review review = new Review(res.getString("review_id"), 
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.add(review);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUsers(){
		String sql = "SELECT * FROM Users";
		List<User> result = new ArrayList<User>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				User user = new User(res.getString("user_id"),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("name"),
						res.getDouble("average_stars"),
						res.getInt("review_count"));
				
				result.add(user);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
