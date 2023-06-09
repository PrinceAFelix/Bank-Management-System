package bankmanagementsystem.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bankmanagementsystem.AtmTransaction;
import bankmanagementsystem.Chequing;
import bankmanagementsystem.Savings;
import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;
import io.github.cdimascio.dotenv.Dotenv;

public class JDBC {
	
	private final String dburl = "jdbc:postgresql://localhost/";
	private final String dbuser = "postgres";
	private final String dbpassword = "";
	
	

	private ArrayList<User> usersptr = new ArrayList<User>();
	private ArrayList<UserAccount> userAccount = new ArrayList<UserAccount>();
//	private ArrayList<ArrayList<AtmTransaction>> transactions = new ArrayList<ArrayList<AtmTransaction>>();

	int tempid = 0;
	
	public JDBC() {
		
	}

	
	
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
	
	
	
	public boolean insertUser(String id, String fname, String email, String phone, String password, String username, String address, String cnum, Chequing account) {

		
		String accountSql = "INSERT INTO user_accounts (id, accountnumber, balance, accounttitle) VALUES (?, ?, ?, ?)";


		String sql = "INSERT INTO users (id, fullname, email, phone, password, username, address, cardnumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
	    try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
	    		PreparedStatement statement = connection.prepareStatement(sql);
	    		statement.setString(1, id);
	            statement.setString(2, fname);
	            statement.setString(3, email);
	            statement.setString(4, phone);
	            statement.setString(5, password);
	            statement.setString(6, username);
	            statement.setString(7, address);
	            statement.setString(8, cnum);
	            statement.executeUpdate();
	            
	            statement = connection.prepareStatement(accountSql);
	            statement.setString(1, String.format("%04d", tempid));
	            statement.setString(2, account.accountNumber);
	            statement.setFloat(3, account.accountBalance);
	            statement.setString(4, account.account_title);
	            statement.executeUpdate();
	            
	            
	            return true;
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
		}
		
	}
	
//	  String sql = "SELECT users.*, user_accounts.* "
//		  		+ "FROM users "
//		  		+ "JOIN user_accounts.id ON users.id = user_accounts.id "
//		  		+ "WHERE users.id = ?";
	
	public boolean deleteUser(String id) {
		
		String sql = "DELETE FROM users "
					+ "WHERE id = ?";
		
		
	    try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setString(1, id);
    		statement.executeUpdate();

            return true;
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
	    }
	    
		

	}
	
	
	public boolean editCustomer(String[] fields, String id) {
		
		String sql = "UPDATE users "
				+ "SET fullname=?, username=?, email=?, phone=?, address=? "
				+ "WHERE id = ?";
	
	    try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, fields[0]);
			statement.setString(2, fields[1]);
			statement.setString(3, fields[2]);
			statement.setString(4, fields[3]);
			statement.setString(5, fields[4]);
			statement.setString(6, id);
			statement.executeUpdate();
	
	        return true;
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
	    }

	}

	
	public User getUser(String id) {
	
		
		
		String sql = "SELECT * FROM users "
				+ "WHERE id = ?";
	
    try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet rs = statement.executeQuery();
		
		//Get the accounts associated
		getUserAccounts(id);
		if(rs.next())
			return new User(id, rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("password"), rs.getString("username"), rs.getString("address"), Long.valueOf(rs.getString("cardnumber")));
		return null;

    }catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
    }
    
		
		
		
	}
	
	
	public boolean getCustomersList(){
		
		ArrayList<User> userList = new ArrayList<User>();

		  
		  String sql = "SELECT * FROM users ";
		  
		  try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();
				
				
				while (rs.next()) {
	                User user = new User(rs.getString("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("password"), rs.getString("username"), rs.getString("address"), Long.valueOf(rs.getString("cardnumber")));
	                userList.add(user);
	            }
				  
				setUsersList(userList);
				
				return true;

		    }catch (SQLException e) {

				e.printStackTrace();
				return false;
		
		    }

	}
	
	
	
	//User Accounts
	
	public ArrayList<UserAccount> getUserAccounts(String userid) {
		
		ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();
		
		  String sql = "SELECT user_accounts.* "
		  		+ "FROM users "
		  		+ "JOIN user_accounts ON users.id = user_accounts.id "
		  		+ "WHERE users.id = ?";

		  
		  try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, userid);
				ResultSet rs = statement.executeQuery();
				UserAccount temp = null;
				
				while (rs.next()) {
	               if(rs.getString("accounttitle").equals("Chequing")) {
	            	   temp = new Chequing(rs.getString("accountnumber"), Float.valueOf(rs.getString("balance")), "Chequing");
	               }else {
	            	   temp = new Savings(rs.getString("accountnumber"), Float.valueOf(rs.getString("balance")), "Savings");
	               }
	               
	               
	               accounts.add(temp);
	              
	            }
				
				
				System.out.println(temp.accountTitle);
				setUserAccount(accounts);
				  

				return getUserAccount();
				
				
				

		    }catch (SQLException e) {

				e.printStackTrace();
				return null;
		
		    }
		  
		  
	}
	
	
	public String authenticateUser(String cardnumber, String password) {
		 String sql = "SELECT * FROM users "
			  		+ "WHERE users.cardnumber = ? AND users.password = ?";
		 
		 try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, cardnumber);
				statement.setString(2, password);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					return rs.getString("id");
				}
	

		    }catch (SQLException e) {

				e.printStackTrace();
				 return null;
		
		    }
		return "";


	}
	



	/**
	 * @return the usersptr
	 */
	public ArrayList<User> getUsersList() {
		return usersptr;
	}



	/**
	 * @param usersptr the usersptr to set
	 */
	public void setUsersList(ArrayList<User> usersptr) {
		this.usersptr = usersptr;
	}



	/**
	 * @return the userAccount
	 */
	public ArrayList<UserAccount> getUserAccount() {
		return userAccount;
	}



	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(ArrayList<UserAccount> userAccount) {
		this.userAccount = userAccount;
	}
	
	

	
	
	
	
	
	


}
