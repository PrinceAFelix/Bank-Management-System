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
	
	BankController controller = new BankController();
	BankController.MouseClickListener mousecontroller = controller.new MouseClickListener();
	
	
	JButton registerBtn = new JButton("Register Customers");

	public BankView() {
		
		LogIn l = new LogIn();
		Admin a = new Admin();
		RegisterPage register = new RegisterPage();
		UnregisterPage remove = new UnregisterPage();
		DisplayCustomersPage dp = new DisplayCustomersPage();
		ModificationPage mp = new ModificationPage();
		
		
		panel.add(l.logInPanel(controller), "login");
		panel.add(a.adminPanel(controller), "adminmain");
		panel.add(register.registerPanel(controller, mousecontroller), "register");
		panel.add(remove.unregisterPanel(controller, mousecontroller), "remove");
		panel.add(dp.displayPanel(controller, mousecontroller), "display");
		panel.add(mp.ModifyPanel(controller, mousecontroller), "modify");
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBackground(new Color(255, 255, 255));
		
		
		mainFrame.add(panel);
		
		
		add(mainFrame);
		
	}
	
	public static JPanel getPanel() {
		return panel;
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
