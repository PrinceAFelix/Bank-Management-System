package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bankmanagementsystem.controller.BankController;

public class BankView extends JFrame {

	JLabel mainFrame = new JLabel();
	JLabel pageTitle;
	private static JPanel panel = new JPanel(new CardLayout());
	
	BankController controller = new BankController();

	public BankView() {
		
		LogIn l = new LogIn();
		Admin a = new Admin();
		
		panel.add(l.logInPanel(controller), "login");
		panel.add(a.adminPanel(controller), "adminmain");
		
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
