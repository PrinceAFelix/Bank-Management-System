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
	

	
	private AdminUser admin = new AdminUser("adm001", "admin", "admin");

	CardLayout cardLayout = (CardLayout) BankView.getPanel().getLayout();
	

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		UserAccount u = new UserAccount();
		UserAccountPage up = new UserAccountPage();
		User activeUser;
		
		users.add(new User("0001", "John Doe", "doe@test.com", "123456789", "12345", "doe", "doe St", 123));

		
		
		if(ae.getSource() ==  LogIn.getLoginBtn()) {
			
			//Admin Login
			if(LogIn.getCredential().getText().equals(admin.getUsername()) 
					&& String.valueOf(LogIn.getPasswordField().getPassword()).equals(admin.getPassword())) {
				System.out.println("Authenticate");
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
		
		
		if(ae.getSource().equals(Admin.getRegisterBtn())) {
			System.out.println("Register");
			cardLayout.show(BankView.getPanel(), "register");
		}
		
		if(ae.getSource().equals(Admin.getUnRegisterBtn())) {
			System.out.println("Remove");
			cardLayout.show(BankView.getPanel(), "remove");
		}
		
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
	        }
	        
	        if(e.getSource().equals(UserAccount.getBackBtn())) {
	        	cardLayout.show(BankView.getPanel(), "user");
	        }
	        
	     
	        

	        
	        
	        
	        
	     
	    }
		
	}

	
}
