package bankmanagementsystem.view;



import javax.swing.JPanel;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;



public abstract class Results {

	public void update(JPanel panel, User user, BankController controller, MouseClickListener mousecontroller, String id) {
		
		 panel.removeAll();
		 
		 condition(user, controller, mousecontroller, id);
		 
		 panel.revalidate(); 
	     panel.repaint(); 
	}
	
	public abstract void condition(User user, BankController controller, MouseClickListener mousecontroller, String id);
}