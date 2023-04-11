package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import bankmanagementsystem.controller.BankController;

public class LogIn {
	
	
	private static JTextField credential;
	private static JPasswordField passwordField;
	private static JButton logInBtn;
	
	Components comp = new Components();
	
	
	public LogIn(){
		
	}
	
	
	public JPanel logInPanel(BankController controller) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(loginTitle(), BorderLayout.NORTH);
		panel.add(formFields(controller), BorderLayout.CENTER);
		panel.add(forgotPassword(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JLabel loginTitle() {

		JLabel title = new JLabel("Log In", SwingConstants.CENTER);
		
		title.setFont(new Font("Calibri", Font.BOLD, 28));
		title.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createEmptyBorder(50, 0, 0, 0), // Top padding of 10 pixels
		        title.getBorder())); 
		
		return title;
	}
	
	private JLabel forgotPassword() {

		JLabel forgotpass = new JLabel("Forgot your password", SwingConstants.CENTER);
		
		forgotpass.setFont(new Font("Calibri", Font.PLAIN, 14));
		forgotpass.setForeground(new Color(132, 186, 235));
		forgotpass.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createEmptyBorder(0, 0, 150, 0), // Top padding of 10 pixels
		        forgotpass.getBorder())); 
		
		return forgotpass;
	}
	
	private JPanel formFields(BankController controller) {
		
		JPanel panel = new JPanel();
		
		logInBtn = new JButton("Log In");
		logInBtn.setBackground(new Color(57, 118, 174));
		logInBtn.setForeground(Color.WHITE);
		logInBtn.setOpaque(true);
		logInBtn.setBorderPainted(false);
	
		

		credential = new JTextField();
		credential = comp.setTextFieldFocusListener(credential, "Access Number or Username");
		
	
		
		passwordField = new JPasswordField();
		passwordField = comp.setPasswordFieldFocusListener(passwordField, "Password");
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(credential, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
						.addComponent(logInBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
					.addGap(19))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(147)
					.addComponent(credential, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(logInBtn, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(277, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		logInBtn.addActionListener(controller);
		
		
		return panel;
		
		
	}
	

	public static JButton getLoginBtn() {
		return logInBtn;
		
	}

	/**
	 * @return the credential
	 */
	public static JTextField getCredential() {
		return credential;
	}


	/**
	 * @return the passwordField
	 */
	public static JPasswordField getPasswordField() {
		return passwordField;
	}



	
	

}



