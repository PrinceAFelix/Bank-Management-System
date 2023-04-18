package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;

public class BankView extends JFrame {

	JLabel mainFrame = new JLabel();
	JLabel pageTitle;
	
	private static JPanel panel = new JPanel(new CardLayout());
	private static BankController controller = new BankController();
	private static BankController.MouseClickListener mousecontroller = controller.new MouseClickListener();
	public static RegisterPage register = new RegisterPage();
	public static UnregisterPage unregister = new UnregisterPage();
	public static DisplayCustomersPage display = new DisplayCustomersPage();
	
	JButton registerBtn = new JButton("Register Customers");

	public BankView() {
		
		LogIn l = new LogIn();
		
		
	
		
		ModificationPage mp = new ModificationPage();
		UserAccount u = new UserAccount();
	
		
	
		
//		panel.add(u.userAccountPanel(controller, mousecontroller), "login");
		panel.add(l.logInPanel(controller), "login");
		
		panel.add(register.registerPanel(controller, mousecontroller), "register");
		panel.add(unregister.unregisterPanel(controller, mousecontroller), "remove");
		panel.add(display.displayPanel(controller, mousecontroller, controller.getUsers()), "display");
		panel.add(mp.ModifyPanel(controller, mousecontroller), "modify");
//		panel.add(up.userPanel(controller), "user");
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBackground(new Color(255, 255, 255));
		
		
		mainFrame.add(panel);
		
		
		add(mainFrame);
		
	}
	
	public static JPanel getPanel() {
		return panel;
	}
	
	public static BankController getController() {
		return controller;
	}
	
	
	public static BankController.MouseClickListener getMouseController() {
		return mousecontroller;
	}
	

	/**
	 * @return the pageTitle
	 */
	public JLabel getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(JLabel pageTitle) {
		this.pageTitle = pageTitle;
	}

}
