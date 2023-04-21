package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



public class UserAccountPage {
	
	private static JButton signoutBtn;
	private static JButton accountBtn;
	protected static JButton depositBtn;
	protected static JButton withdrawBtn;
	protected static User activeUser;
	
	
	public JPanel header(String headerTitle) {
		
		JPanel panel = new JPanel();

		panel.setBackground(new Color(57, 118, 174));
	
		panel.setPreferredSize(new Dimension(350, 150));
		
		
		
		JLabel title = new JLabel(headerTitle);
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(300, 60));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(120)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(41)
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
		return panel;
		
		
	}
	

	

	
	public JPanel userPanel(BankController controller) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(header(activeUser.getFullName()), BorderLayout.NORTH);
		panel.add(main(controller), BorderLayout.CENTER);
		
		panel.add(footer(controller), BorderLayout.SOUTH);
		
		return panel;
	}
	
	
	private JPanel main(BankController controller) {
		
		JPanel main = new JPanel();
		main.setBackground(new Color(205, 205, 205));
		
		accountBtn = new JButton("My Accounts");
		accountBtn.setForeground(new Color(72, 116, 169));
		accountBtn.setFocusable(false);
		
		accountBtn.addActionListener(controller);
		
		
		depositBtn = new RoundButton("Deposit");
		depositBtn.addActionListener(controller);
		withdrawBtn = new RoundButton("Withdraw");
		withdrawBtn.addActionListener(controller);
		
		GroupLayout gl_main = new GroupLayout(main);
		gl_main.setHorizontalGroup(
			gl_main.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main.createSequentialGroup()
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main.createSequentialGroup()
							.addGap(72)
							.addComponent(depositBtn, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(withdrawBtn, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main.createSequentialGroup()
							.addGap(18)
							.addComponent(accountBtn, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_main.setVerticalGroup(
			gl_main.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_main.createParallelGroup(Alignment.BASELINE)
						.addComponent(depositBtn, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(withdrawBtn, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(accountBtn, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(221, Short.MAX_VALUE))
		);
		main.setLayout(gl_main);
		
		return main;
	}
	
	
	private JPanel footer(BankController controller) {
		JPanel footer = new JPanel();
		footer.setBackground(new Color(205, 205, 205));
		
		footer.setPreferredSize(new Dimension(350,70));
		
		signoutBtn = new JButton("Sign out");
		
		signoutBtn.setBackground(new Color(255, 255, 255));
		signoutBtn.setFocusable(false);
		
		signoutBtn.addActionListener(controller);
		
		GroupLayout gl_footer = new GroupLayout(footer);
		gl_footer.setHorizontalGroup(
			gl_footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_footer.createSequentialGroup()
					.addGap(80)
					.addComponent(signoutBtn, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		gl_footer.setVerticalGroup(
			gl_footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_footer.createSequentialGroup()
					.addContainerGap()
					.addComponent(signoutBtn, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addGap(15))
		);
		footer.setLayout(gl_footer);
		
		return footer;
	}
	
	
	public static JButton getAccountBtn() {
		return accountBtn;
	}
	
	
	public static JButton getSignoutBtn() {
		return signoutBtn;
	}



	/**
	 * @return the activeUser
	 */
	public static User getActiveUser() {
		return activeUser;
	}



	/**
	 * @param activeUser the activeUser to set
	 */
	public static void setActiveUser(User activeUser) {
		UserAccountPage.activeUser = activeUser;
	}





	/**
	 * @return the depositBtn
	 */
	public static JButton getDepositBtn() {
		return depositBtn;
	}

	/**
	 * @return the withdrawBtn
	 */
	public static JButton getWithdrawBtn() {
		return withdrawBtn;
	}

	

}
