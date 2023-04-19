package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.controller.BankController;

public class Admin {
	
	private String title;
	private String bankID;
	private String location;
	private final static JButton registerBtn = new JButton("Register Customer");
	private final static JButton unRegisterBtn = new JButton("Unregister Customer");
	private final static JButton displayCustomersBtn = new JButton("Display Customers");
	private final static JButton modifyCustomerBtn = new JButton("Modify Customer");
	private final static JButton exitBtn = new JButton("Exit");


	public Admin() {
//		registerBtn = new JButton("Register Customer");
//		unRegisterBtn = new JButton("Unregister Customer");
//		displayCustomersBtn = new JButton("Display Customers");
//		searchCustomerBtn = new JButton("Search Customer");
//		modifyCustomerBtn = new JButton("Modify Customer");
//		exitBtn = new JButton("Exit");
	}
	public JPanel header(String headerTitle, String loc, String ID) {
	
		JPanel panel = new JPanel();

		panel.setBackground(new Color(57, 118, 174));
	
		panel.setPreferredSize(new Dimension(350, 150));
		
		JLabel bankLocation = new JLabel(loc);
		bankLocation.setForeground(new Color(255, 255, 255));
		
		JLabel bankID = new JLabel(ID);
		bankID.setForeground(new Color(255, 255, 255));
		
		JLabel title = new JLabel(headerTitle);
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addComponent(bankLocation)
					.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
					.addComponent(bankID, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(130)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(41)
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(bankLocation)
						.addComponent(bankID))
					.addGap(19))
		);
		panel.setLayout(gl_panel);
		
		
		return panel;
		
		
	}
	
	public JPanel adminPanel(BankController controller, String loc, String ID) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(header("Admin", loc, ID), BorderLayout.NORTH);
		panel.add(actions(controller), BorderLayout.CENTER);
		
		return panel;
		
		
	}
	
	public JPanel actions(BankController controller) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(205, 205, 205));
		

		
		
		registerBtn.setForeground(new Color(72, 116, 169));
		registerBtn.setBackground(new Color(255, 255, 255));
		
		
		
		unRegisterBtn.setForeground(new Color(72, 116, 169));
		unRegisterBtn.setBackground(Color.WHITE);
		
		
		displayCustomersBtn.setForeground(new Color(72, 116, 169));
		displayCustomersBtn.setBackground(Color.WHITE);
		
		
		
		modifyCustomerBtn.setForeground(new Color(72, 116, 169));
		modifyCustomerBtn.setBackground(Color.WHITE);
		

		

		exitBtn.setForeground(new Color(72, 116, 169));
		exitBtn.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(modifyCustomerBtn, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
								.addComponent(displayCustomersBtn, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
								.addComponent(unRegisterBtn, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
								.addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(86)
							.addComponent(exitBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(unRegisterBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayCustomersBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(modifyCustomerBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
					.addComponent(exitBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		panel.setLayout(gl_panel);
		
		registerBtn.addActionListener(controller);
		unRegisterBtn.addActionListener(controller);
		displayCustomersBtn.addActionListener(controller);
		modifyCustomerBtn.addActionListener(controller);
		
		exitBtn.addActionListener(controller);
		
		return panel;
	}
	/**
	 * @return the registerBtn
	 */
	public static JButton getRegisterBtn() {
		return registerBtn;
	}

	
	/**
	 * @return the unRegisterBtn
	 */
	public static JButton getUnRegisterBtn() {
		return unRegisterBtn;
	}

	
	/**
	 * @return the displayCustomersBtn
	 */
	public static JButton getDisplayCustomersBtn() {
		return displayCustomersBtn;
	}

	

	
	/**
	 * @return the modifyCustomerBtn
	 */
	public static JButton getModifyCustomerBtn() {
		return modifyCustomerBtn;
	}

	/**
	 * @return the exitBtn
	 */
	public static JButton getExitBtn() {
		return exitBtn;
	}


	

	
	
}
