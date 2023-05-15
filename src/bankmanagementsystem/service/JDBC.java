package bankmanagementsystem.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bankmanagementsystem.Chequing;
import io.github.cdimascio.dotenv.Dotenv;

public class JDBC {
	
	private final String url = "";
	private final String user = "";
	private final String dbpassword = "";
	

	 
	

	
	
//	public Connection connect() {
//		try{
//		
//			Connection connection = DriverManager.getConnection (dotenv.get("URL"), dotenv.get("USER"), dotenv.get("PASSWORD"));
//			if(connection != null) {
//				System.out.println("Connected to PostgreSQL server successfully!");
//			}else {
//				System.out.println("Failed to connect PostgreSQL server");
//			}
//			
//			
//
//		    
//			Statement statement = connection.createStatement ();
//			ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
//			
//			if(resultSet.next()) {
//				System.out.println(resultSet.getString(1));
//			}
//			
//			return connection;
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//		
//		
//	}
	
	
	public boolean insertUserfull(String fname, String email, String phone, String password, String username, String address, long cnum, Chequing account) {

		
		String accountSql = "INSERT INTO user_accounts (account_number, account_balance, account_title) VALUES (?, ?, ?)";


		String sql = "INSERT INTO users (full_name, email, phone, password, username, address, card_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
	    try (Connection connection = DriverManager.getConnection (url, user, dbpassword);) {
	    		PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, fname);
	            statement.setString(2, email);
	            statement.setString(3, phone);
	            statement.setString(4, password);
	            statement.setString(5, username);
	            statement.setString(6, address);
	            statement.setLong(7, cnum);
	            statement.executeUpdate();
	            
	            statement = connection.prepareStatement(accountSql);
	            statement.setString(1, account.accountNumber);
	            statement.setFloat(2, account.accountBalance);
	            statement.setString(3, account.account_title);
	            statement.executeUpdate();
	            
	            
	            return true;
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
		}
		
	}
	
	
	


}
