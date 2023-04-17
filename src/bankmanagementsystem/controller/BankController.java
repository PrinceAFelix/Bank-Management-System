package bankmanagementsystem.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import bankmanagementsystem.model.AdminUser;
import bankmanagementsystem.model.User;
import bankmanagementsystem.view.Admin;
import bankmanagementsystem.view.BankView;
import bankmanagementsystem.view.DisplayCustomersPage;
import bankmanagementsystem.view.LogIn;
import bankmanagementsystem.view.ModificationPage;
import bankmanagementsystem.view.RegisterPage;
import bankmanagementsystem.view.UnregisterPage;
import bankmanagementsystem.view.UserAccount;
import bankmanagementsystem.view.UserAccountPage;


public class BankController implements ActionListener {
	
	private ArrayList<User> users = new ArrayList<User>(20);
	
	User activeUser = new User();
	
	private AdminUser admin = new AdminUser("adm001", "a", "a");

	CardLayout cardLayout = (CardLayout) BankView.getPanel().getLayout();
	

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		Admin a = new Admin();
		UserAccount u = new UserAccount();
		UserAccountPage up = new UserAccountPage();
		User activeUser = null;
		
		users.add(new User("0001", "John Doe", "doe@test.com", "123456789", "12345", "doe", "doe St", 123));
		users.add(new User("0000", "John Doe", "doe@test.com", "123456789", "12345", "doe", "doe St", 123));

		
		
		if(ae.getSource() ==  LogIn.getLoginBtn()) {
			
			//Admin Login
			if(LogIn.getCredential().getText().equals(admin.getUsername()) 
					&& String.valueOf(LogIn.getPasswordField().getPassword()).equals(admin.getPassword())) {
				System.out.println("Authenticate");
				BankView.getPanel().add(a.adminPanel(BankView.getController()), "adminmain");
				cardLayout.show(BankView.getPanel(), "adminmain");
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
				String deletedUserID = admin.deleteCustomer(users, user , UnregisterPage.getTextField().getText());
				UnregisterPage.setIsFormSubmit(true);
				BankView.unregister.update(BankView.unregister.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), deletedUserID);
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		
		
		//End Remove
		
		if(ae.getSource().equals(Admin.getDisplayCustomersBtn())) {
			System.out.println("display");
			cardLayout.show(BankView.getPanel(), "display");
		}
		
		if(ae.getSource().equals(Admin.getModifyCustomerBtn())) {
			System.out.println("modify");
			cardLayout.show(BankView.getPanel(), "modify");
		}
		
		
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
		
		
		
	}
	
	
	public class MouseClickListener extends MouseAdapter{
		
		@Override
	    public void mouseClicked(MouseEvent e) {
			
			
			
	        if(e.getSource() == RegisterPage.getCancelBtn() 
	        		|| e.getSource() == UnregisterPage.getCancelBtn()
	        		|| e.getSource() == DisplayCustomersPage.getCancelBtn()
	        		|| e.getSource().equals(ModificationPage.getCancelBtn())) {
				cardLayout.show(BankView.getPanel(), "adminmain");
				
				RegisterPage.setIsFormSubmit(false);
				UnregisterPage.setIsFormSubmit(false);
				BankView.register.update(BankView.register.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), null);
				BankView.unregister.update(BankView.unregister.getPanel(), users.get(users.size()-1), BankView.getController(), BankView.getMouseController(), null);
	        }
	        
	        if(e.getSource().equals(UserAccount.getBackBtn())) {
	        	cardLayout.show(BankView.getPanel(), "user");
	        }
	        
	     
	        

	        
	        
	        
	        
	     
	    }
		
	}

	
}
