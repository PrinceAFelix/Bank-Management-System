package bankmanagementsystem.view;



import java.util.ArrayList;

import javax.swing.JPanel;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;



public abstract class Results {
	
	public void defaultUpdate(JPanel panel, BankController controller, MouseClickListener mousecontroller,  ArrayList<User> users) {
		panel.removeAll();
		reRender(controller, mousecontroller, users);
		panel.revalidate(); 
	    panel.repaint(); 
	}
	
	public abstract void reRender(BankController controller, MouseClickListener mousecontroller,  ArrayList<User> users);
	
	
	
	public void update(JPanel panel, User user, BankController controller, MouseClickListener mousecontroller, String[] deleteduser) {
		
		 panel.removeAll();
		 
		 condition(user, controller, mousecontroller, deleteduser);
		 
		 panel.revalidate(); 
	     panel.repaint(); 
	}
	
	public abstract void condition(User user, BankController controller, MouseClickListener mousecontroller, String[] deleteduser);
	
}