package bankmanagementsystem.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import bankmanagementsystem.model.AdminUser;
import bankmanagementsystem.view.Admin;
import bankmanagementsystem.view.BankView;
import bankmanagementsystem.view.DisplayCustomersPage;
import bankmanagementsystem.view.LogIn;
import bankmanagementsystem.view.ModificationPage;
import bankmanagementsystem.view.RegisterPage;
import bankmanagementsystem.view.UnregisterPage;


public class BankController implements ActionListener {
	
	private AdminUser admin = new AdminUser("adm001", "admin", "admin");

	CardLayout cardLayout = (CardLayout) BankView.getPanel().getLayout();
	

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		

		
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
		
	}
	
	
	public class MouseClickListener extends MouseAdapter{
		
		@Override
	    public void mouseClicked(MouseEvent e) {
	        if(e.getSource() == RegisterPage.getCancelBtn()) {
				cardLayout.show(BankView.getPanel(), "adminmain");
	        }
	        
	        if(e.getSource() == UnregisterPage.getCancelBtn()) {
				cardLayout.show(BankView.getPanel(), "adminmain");
	        }
	        
	        if(e.getSource() == DisplayCustomersPage.getCancelBtn()) {
				cardLayout.show(BankView.getPanel(), "adminmain");
	        }
	        
	        if(e.getSource().equals(ModificationPage.getCancelBtn())) {
				cardLayout.show(BankView.getPanel(), "adminmain");
	        }
	     
	    }
		
	}

	
}
