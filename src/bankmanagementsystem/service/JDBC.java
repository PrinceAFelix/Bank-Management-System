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
	private ArrayList<ArrayList<AtmTransaction>> transactions = new ArrayList<ArrayList<AtmTransaction>>();

	
	
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
	
	
	
	public boolean insertUser(String id, String fname, String email, String phone, String password, String username, String address, String cnum, UserAccount account) {

		
		String accountSql = "INSERT INTO user_accounts (accountid1, accountnumber, balance, accounttitle) VALUES (?, ?, ?, ?)";


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
	            statement.setString(1, id);
	            statement.setString(2, account.accountNumber);
	            statement.setFloat(3, account.accountBalance);
	            statement.setString(4, account.accountTitle);
	            statement.executeUpdate();
	            
	            
	            return true;
	     }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
		}
		
	}
	

	
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
		getTransactionsDB(id, "Chequing");
	
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
		  		+ "JOIN user_accounts ON users.id = user_accounts.accountid1 OR users.id = user_accounts.accountid2 "
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
	
	public boolean addAccount(User user, UserAccount account) {
		
		String sql = "INSERT INTO user_accounts (accountid2, accountnumber, balance, accounttitle) VALUES (?, ?, ?, ?)";
		
		
		 try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
			    statement.setString(1, user.getId());
	            statement.setString(2, account.accountNumber);
	            statement.setFloat(3, account.accountBalance);
	            statement.setString(4, account.accountTitle);
	            statement.executeUpdate();
	            
	            
	            getUserAccounts(user.getId()); 
	            
	            return true;
	            
		 }catch (SQLException e) {

				e.printStackTrace();
				return false;
		
		    }
		
		
	}
	
	public boolean removeAccount(User user) {
		String sql = "DELETE FROM user_accounts "
				+ "WHERE user_accounts.accountid2 = ?";

		
		
		 try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, user.getId());
				
	            statement.executeUpdate();
	            
	            
	            getUserAccounts(user.getId()); 
	            
	            return true;
	            
		 }catch (SQLException e) {

				e.printStackTrace();
				return false;
		
		    }
	}
	
	
	
	
	
	public boolean depositMoney(User user, float amount, int account) {
		

		
		String sql = "UPDATE user_accounts "
		           + "SET balance = balance + ? "
		           + "FROM users "
		           + "WHERE " + (account == 0 ? " user_accounts.accountid1 " : "user_accounts.accountid2") + " = users.id "
		           + "AND users.id = ?";
		  
		  System.out.println(getUserAccount().size());
		  
	
		
		  user.getUserAccount().get(account).accountBalance += amount;
		 
		 try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setFloat(1,  amount);
				statement.setString(2, user.getId());
				
				statement.executeUpdate();
				return true;
				
		 }catch (SQLException e) {

				e.printStackTrace();
				return false;
		
		    }
		  
		
	}
	
	
	public boolean withdrawMoney(User user, float amount, int account) {
		
		
		String sql = "UPDATE user_accounts "
		           + "SET balance = balance - ? "
		           + "FROM users "
		           + "WHERE " + (account == 0 ? " user_accounts.accountid1 " : "user_accounts.accountid2") + " = users.id "
		           + "AND users.id = ?";
		  
		  System.out.println(getUserAccount().size());
		  
		
		
		  user.getUserAccount().get(account).accountBalance -= amount;
		 
		 try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setFloat(1,  amount);
				statement.setString(2, user.getId());
				
				statement.executeUpdate();
				return true;
				
		 }catch (SQLException e) {

				e.printStackTrace();
				return false;
		
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
	
	
	
	public boolean transaction(User user, AtmTransaction transaction) {
		
		String sql = "INSERT INTO transactions (" + (transaction.getAccount() == "Chequing" ? "transactionid1" : "transactionid2") + ", account, datetime, transacttype, amount, postbalance) VALUES (?, ?, ?, ?, ?, ?)";
		
		 try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, user.getId());
			 statement.setString(2,transaction.getAccount());
			 statement.setString(3,transaction.getTransaction_Date());
			 statement.setString(4,transaction.getTransaction_Type());
			 statement.setFloat(5, transaction.getTransaction_Amount());
			 statement.setFloat(6,transaction.getPost_balance());
			 statement.executeUpdate();
			 
			 
			 getTransactionsDB(user.getId(), transaction.getAccount());
		
			 
		 }catch (SQLException e) {

				e.printStackTrace();
				 return false;
		
		    }
		
		return true;
	}
	
	public boolean getTransactionsDB(String id, String account){
		
		ArrayList<ArrayList<AtmTransaction>> transactiontemp = new ArrayList<ArrayList<AtmTransaction>>();
		
		transactiontemp.add(new ArrayList<AtmTransaction>());//Represents Chequing
		transactiontemp.add(new ArrayList<AtmTransaction>());//Represents Savings
		
		String sql = "SELECT transactions.* "
				+ "FROM user_accounts "
				+ "JOIN transactions ON (user_accounts.accountid1 = transactions.transactionid1 OR user_accounts.accountid2 = transactions.transactionid2) "
				+ "WHERE user_accounts.accountid1 = ? OR user_accounts.accountid2 = ?";
		try (Connection connection = DriverManager.getConnection (dburl, dbuser, dbpassword);) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, id);
			ResultSet rs = statement.executeQuery();
			AtmTransaction temp = null;
			
			while (rs.next()) {
//				private int id;
//				private String account;
//				private String transaction_Date;
//				private String transaction_Type;
//				private float transaction_Amount;
//				private float post_balance;
				
				
				temp = new AtmTransaction(Integer.parseInt(rs.getString("id")), rs.getString("account"), rs.getString("datetime"), rs.getString("transacttype"), Float.valueOf(rs.getString("amount")), Float.valueOf(rs.getString("postbalance")));
				
				
				
				if(rs.getString("account").equals("Chequing")) {
					transactiontemp.get(0).add(temp);
				}else if(rs.getString("account").equals("Savings")) {
					transactiontemp.get(1).add(temp);
					
				}
				
				System.out.println("HERE: " + rs.getString("account"));
				
				
			}
	
			System.out.println("Transaction Chequing size: " + transactiontemp.get(0).size());
			System.out.println("Transaction Savings size: " + transactiontemp.get(1).size());
			
			
			setTransactions(transactiontemp);
			
			return true;
		}catch (SQLException e) {

			e.printStackTrace();
			 return false;
	
	    }
		
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



	/**
	 * @return the transactions
	 */
	public ArrayList<ArrayList<AtmTransaction>> getTransactions() {
 
		return transactions;
	}



	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(ArrayList<ArrayList<AtmTransaction>> transactions) {
		this.transactions = transactions;
	}
	
	

	
	
	
	
	
	


}
