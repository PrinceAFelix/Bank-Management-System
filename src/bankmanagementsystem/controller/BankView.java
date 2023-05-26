package bankmanagementsystem.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.AdminUser;
import bankmanagementsystem.view.DisplayCustomersPage;
import bankmanagementsystem.view.LogIn;
import bankmanagementsystem.view.ModificationPage;
import bankmanagementsystem.view.RegisterPage;
import bankmanagementsystem.view.UnregisterPage;
import bankmanagementsystem.view.UserAccounts;

public class BankView extends JFrame {
	
	public String atmNumber;
	public String code;
	public String address;
	public String name;

	JLabel mainFrame = new JLabel();
	JLabel pageTitle;
	
	private static JPanel panel = new JPanel(new CardLayout());
	private static BankController controller = new BankController();
	private static BankController.MouseClickListener mousecontroller = controller.new MouseClickListener();
	public static RegisterPage register = new RegisterPage();
	public static UnregisterPage unregister = new UnregisterPage();
	public static DisplayCustomersPage display = new DisplayCustomersPage();
	public static ModificationPage modify = new ModificationPage();
	

	
	public BankView(String atmNum, String c, String adr, String nm) {
		this.atmNumber = atmNum;
		this.code = c;
		this.address = adr;
		this.name = nm;
	}
	
	public BankView() {
		
		LogIn l = new LogIn();
	
		panel.add(l.logInPanel(controller), "login");
		panel.add(register.registerPanel(controller, mousecontroller), "register");
		panel.add(unregister.unregisterPanel(controller, mousecontroller), "remove");
	
		panel.add(modify.ModifyPanel(controller, mousecontroller), "modify");
		
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
