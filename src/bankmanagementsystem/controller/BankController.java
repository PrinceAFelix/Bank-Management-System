package bankmanagementsystem.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import bankmanagementsystem.Chequing;
import bankmanagementsystem.model.AdminUser;
import bankmanagementsystem.model.User;
import bankmanagementsystem.view.Admin;
import bankmanagementsystem.view.AtmTransactionPage;
import bankmanagementsystem.view.DisplayCustomersPage;
import bankmanagementsystem.view.LogIn;
import bankmanagementsystem.view.ModificationPage;
import bankmanagementsystem.view.RegisterPage;
import bankmanagementsystem.view.TransactionHistoryPage;
import bankmanagementsystem.view.UnregisterPage;
import bankmanagementsystem.view.UserAccount;
import bankmanagementsystem.view.UserAccountPage;


public class BankController implements ActionListener {
	
	
	private ArrayList<User> users = new ArrayList<User>(20);

	
	private AdminUser admin;
	
	BankView bank;

	CardLayout cardLayout = (CardLayout) BankView.getPanel().getLayout();
	

	public BankController(){
		
		admin = new AdminUser("adm001", "admin", "admin");
		users.add(new User("0001", "John Doe", "doe@test.com", "123456789", "12345", "doe", "doe St", 123));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		Admin a = new Admin();
		UserAccount u = new UserAccount();
		UserAccountPage up = new UserAccountPage();
		User activeUser = null;
		TransactionHistoryPage transaction  = new TransactionHistoryPage();
		AtmTransactionPage atm = new AtmTransactionPage();
		
		
		if(ae.getSource() ==  LogIn.getLoginBtn()) {
			
			//Admin Login
			if(LogIn.getCredential().getText().equals(admin.getUsername()) 
					&& String.valueOf(LogIn.getPasswordField().getPassword()).equals(admin.getPassword())) {
				System.out.println("Authenticate");
				
				bank = new BankView("1000", "B01", "Bank Street", "Bank of PA");
				BankView.getPanel().add(a.adminPanel(BankView.getController(), bank.address, bank.atmNumber), "adminmain");
				cardLayout.show(BankView.getPanel(), "adminmain");
				
				activeUser = admin;
			}
			
			for (int i = 0; i < users.size(); i++) {
				if(((String.valueOf(users.get(i).getCardNumber())).equals(LogIn.getCredential().getText()))){
					if (users.get(i).verifyPassword(users.get(i).getPassword(), String.valueOf(LogIn.getPasswordField().getPassword()) )) {
						activeUser = users.get(i);
						
						UserAccountPage.setActiveUser(activeUser);
						System.out.println("Sign in");
						BankView.getPanel().add(up.userPanel(BankView.getController()), "user");
						cardLayout.show(BankView.getPanel(), "user");
					}
						
				}
				
			}
			
			UserAccountPage.setActiveUser(activeUser);
			
			//Process login for customers
		
		}
		

		
		if(ae.getSource().equals(Admin.getExitBtn())) {
			System.out.println("Exit");
			cardLayout.show(BankView.getPanel(), "login");
			LogIn.getCredential().setText("");
			LogIn.getPasswordField().setText("");
		}
		
		
		//Register
		if(ae.getSource().equals(Admin.getRegisterBtn())) {
			System.out.println("Register");
			cardLayout.show(BankView.getPanel(), "register");

		}
		
		if(ae.getSource().equals(RegisterPage.getSubmitForm())) {
			
			String firstName = RegisterPage.getFirstName().getText();
			String lastName =  RegisterPage.getLastName().getText();
			String username = RegisterPage.getUsername().getText();
			String email = RegisterPage.getEmail().getText();
			String phone = RegisterPage.getPhone().getText();
			String address = RegisterPage.getAddress().getText();
			String password = String.valueOf(RegisterPage.getPassword().getPassword());
			String confirmPass = String.valueOf(RegisterPage.getConfirmPassword().getPassword());
			
			RegisterPage.getFields()[0] = (firstName.equals("First Name") ? "" : firstName);
			RegisterPage.getFields()[1] = (lastName.equals("Last Name") ? "" : lastName);
			RegisterPage.getFields()[2] = (username.equals("Username") ? "" : username);
			RegisterPage.getFields()[3] = (email.equals("Email") ? "" : email);
			RegisterPage.getFields()[4] = (phone.equals("Phone Number") ? "" : phone);
			RegisterPage.getFields()[5] = (address.equals("Address") ? "" : address);
			RegisterPage.getFields()[6] = (password.equals("Password") ? "" : password);
			RegisterPage.getFields()[7] = (confirmPass.equals("Confirm Password") ? "" : confirmPass);
			
			
			
			if(RegisterPage.verifyEmptyFields()) {
				admin.addCustomer(users, RegisterPage.getFields());
				RegisterPage.setIsFormSubmit(true);
				BankView.register.update(BankView.register.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), null);
			}
		}
		
		//End Register
		
		
		//Remove
		
		if(ae.getSource().equals(Admin.getUnRegisterBtn())) {
			System.out.println("Remove");
			cardLayout.show(BankView.getPanel(), "remove");
		}
		
		if(ae.getSource().equals(UnregisterPage.getSubmitBtn())) {
			User user = new User();
			try{
				String[] deletedUser = admin.deleteCustomer(users, user , UnregisterPage.getTextField().getText());
				UnregisterPage.setIsFormSubmit(true);
				BankView.unregister.update(BankView.unregister.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), deletedUser);
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		
		
		//End Remove
		
		//Display
		
		if(ae.getSource().equals(Admin.getDisplayCustomersBtn())) {
			System.out.println("display");
			cardLayout.show(BankView.getPanel(), "display");
		}
		
		
		if(ae.getSource().equals(DisplayCustomersPage.getBtnSearch())) {
			BankView.display.setUserInput(BankView.display.getTextField().getText());
			DisplayCustomersPage.setIsSearch(true);
			BankView.display.defaultUpdate(BankView.display.getPanel(), BankView.getController(), BankView.getMouseController(), users);
		}
		
		
		
		//End display
		
		
		//Modify
		if(ae.getSource().equals(Admin.getModifyCustomerBtn())) {
			System.out.println("modify");
			cardLayout.show(BankView.getPanel(), "modify");
		}
		
		if(ae.getSource().equals(ModificationPage.getcConfirmBtn())) {
			int user = admin.searchCustomer(users, UserAccountPage.getActiveUser(), ModificationPage.getTextField().getText());
			System.out.println(user);
			if(user != -1) {
				BankView.modify.setModifyingUser(BankView.modify.getModifyingUser(), user, getUsers().get(user));
				ModificationPage.setEditing(true);
				BankView.modify.update(BankView.modify.getPanel(), getUsers().get(user), BankView.getController(), BankView.getMouseController(), null);
			}else {
				ModificationPage.getTextField().setForeground(Color.RED);
			}
		}
		
		if(ae.getSource().equals(BankView.modify.getSaveBtn())) {
			
			try {
				
				Map.Entry<Integer, User> entry = BankView.modify.getModifyingUser().entrySet().iterator().next();
				
				User temp = entry.getValue();
				
				temp.setFullName(BankView.modify.getFirstName().getText() + " " + BankView.modify.getLastName().getText());
				temp.setUsername(BankView.modify.getUsername().getText());
				temp.setEmail(BankView.modify.getEmail().getText());
				temp.setPhone(BankView.modify.getPhoneNumber().getText());
				temp.setAddress(BankView.modify.getAddress().getText());
				
				admin.editCustomer(users, entry.getKey(), temp);
				BankView.modify.defaultUpdate(BankView.modify.getPanel(), BankView.getController(), BankView.getMouseController(), users);
				
			}catch(Exception e) {
				
			}
			
			BankView.modify.getModifyingUser().clear();
			cardLayout.show(BankView.getPanel(), "modify");
			ModificationPage.setEditing(false);
			
		}
		
		
		//End Modify
		
		
		//User Actions
		if(ae.getSource().equals(UserAccountPage.getSignoutBtn())) {
			cardLayout.show(BankView.getPanel(), "login");
			LogIn.getCredential().setText("");
			LogIn.getPasswordField().setText("");
		}
		if(ae.getSource().equals(UserAccountPage.getAccountBtn())) {
			System.out.println("Accounts");
			BankView.getPanel().add(u.userAccountPanel(BankView.getController(), BankView.getMouseController()), "useraccounts");
			cardLayout.show(BankView.getPanel(), "useraccounts");
		}
		
		
		
		
		if(ae.getSource().equals(UserAccount.getChequingBtn())) {
			BankView.getPanel().add(transaction.chequingPanel(BankView.getController(), BankView.getMouseController(), UserAccountPage.getActiveUser(), 0), "transaction");
			cardLayout.show(BankView.getPanel(), "transaction");
		}
		
		if(ae.getSource().equals(UserAccount.getSavingsBtn())) {
			BankView.getPanel().add(transaction.chequingPanel(BankView.getController(), BankView.getMouseController(), UserAccountPage.getActiveUser(), 1), "transaction");
			cardLayout.show(BankView.getPanel(), "transaction");
		}
		
		
		if(ae.getSource().equals(UserAccountPage.getDepositBtn())) {
			BankView.getPanel().add(atm.atmTransactionPanel(BankView.getController(), BankView.getMouseController(), "Deposit"), "atmtransaction");
			cardLayout.show(BankView.getPanel(), "atmtransaction");
			AtmTransactionPage.setOperation(0);
		}
		
		if(ae.getSource().equals(UserAccountPage.getWithdrawBtn())) {
			BankView.getPanel().add(atm.atmTransactionPanel(BankView.getController(), BankView.getMouseController(), "Withdrawal"), "atmtransaction");
			cardLayout.show(BankView.getPanel(), "atmtransaction");
			AtmTransactionPage.setOperation(1);
		}
		
		
		if(ae.getSource().equals(AtmTransactionPage.getContinueBtn())) {
			atm.processOperation(UserAccountPage.getActiveUser(), Float.valueOf(String.valueOf(AtmTransactionPage.getFormattedTextField().getValue())), AtmTransactionPage.getComboBox().getSelectedIndex());
		}
		
		if(ae.getSource().equals(AtmTransactionPage.getComboBox())) {
			AtmTransactionPage.setAccount(String.valueOf(AtmTransactionPage.getComboBox().getSelectedItem()));
		}
		
		
		
	}
	
	
	public class MouseClickListener extends MouseAdapter{
		
		@Override
	    public void mouseClicked(MouseEvent e) {
		
			
	        if(e.getSource() == RegisterPage.getCancelBtn() 
	        		|| e.getSource() == UnregisterPage.getCancelBtn()
	        		|| e.getSource() == DisplayCustomersPage.getCancelBtn()
	        		|| e.getSource().equals(ModificationPage.getCancelBtn())) {
				
	        	
				RegisterPage.setIsFormSubmit(false);
				UnregisterPage.setIsFormSubmit(false);
				BankView.register.update(BankView.register.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), null);
				BankView.unregister.update(BankView.unregister.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), null);
				BankView.display.defaultUpdate(BankView.display.getPanel(), BankView.getController(), BankView.getMouseController(), users);
				BankView.modify.defaultUpdate(BankView.modify.getPanel(), BankView.getController(), BankView.getMouseController(), users);
				
				cardLayout.show(BankView.getPanel(), "adminmain");
				
				System.out.println(DisplayCustomersPage.getIsSearch());
				
				if(DisplayCustomersPage.getIsShwoingInformation()) {
					cardLayout.show(BankView.getPanel(), "display");
					DisplayCustomersPage.setIsShwoingInformation(false);
				}
				
				if(ModificationPage.isEditing()) {
					cardLayout.show(BankView.getPanel(), "modify");
					ModificationPage.setEditing(false);
				}
				
				
				
	        }
	        
	        if(e.getSource().equals(UserAccount.getBackBtn())) {
	        	cardLayout.show(BankView.getPanel(), "user");
	        }
	        
	        if(e.getSource().equals(TransactionHistoryPage.getCancelBtn())) {
	        	cardLayout.show(BankView.getPanel(), "useraccounts");
	        	
	        }
	        
	        
	        for(int i = 0; i < DisplayCustomersPage.userBtn.length; i++) {
	        	if(e.getSource().equals(DisplayCustomersPage.userBtn[i])) {
		        	User user = DisplayCustomersPage.map.get(DisplayCustomersPage.userBtn[i]);
		        	BankView.display.update(BankView.display.getPanel(), user, BankView.getController(), BankView.getMouseController(), null);
		        	DisplayCustomersPage.setIsShwoingInformation(true);
		        }
	        }
	     
	        
	        
	        if(e.getSource().equals(AtmTransactionPage.getCancelBtn())) {
	        	cardLayout.show(BankView.getPanel(), "user");
	        }
	        
	     
	    }
		
	}
	
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	
	public int verifyUser(String id) {
		for(int i = 0; i < getUsers().size(); i++) {
			if(id.equals(users.get(i).getId())) return i;
		}
		
		return -1;
	}

	
}