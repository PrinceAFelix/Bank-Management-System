package bankmanagementsystem.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import bankmanagementsystem.model.AdminUser;
import bankmanagementsystem.view.Admin;
import bankmanagementsystem.view.BankView;
import bankmanagementsystem.view.LogIn;


public class BankController implements ActionListener {
	
	private AdminUser admin = new AdminUser("adm001", "admin", "admin");

	CardLayout cardLayout = (CardLayout) BankView.getPanel().getLayout();
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Admin a = new Admin();
		

		
		if(ae.getSource() ==  LogIn.getLoginBtn()) {
			
			//Admin Login
			if(LogIn.getCredential().getText().equals(admin.getUsername()) 
					&& String.valueOf(LogIn.getPasswordField().getPassword()).equals(admin.getPassword())) {
				System.out.println("Authenticate");
				LogIn.getCredential().setText("");
				LogIn.getPasswordField().setText("");
				cardLayout.show(BankView.getPanel(), "adminmain");
			}
			
			//Process login for customers
		
		}
		

		
		if(ae.getSource().equals(Admin.getExitBtn())) {
			System.out.println("Exit");
			cardLayout.show(BankView.getPanel(), "login");
		}
		
	}

	
}
