package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;



public class RegisterPage {
	
	private static JTextField firstName;
	private static JTextField lastName;
	private static JTextField username;
	private static JTextField email;
	private static JTextField phone;
	private static JTextField address;
	private static JPasswordField password;
	private static JPasswordField confirmPassword;
	private static JButton submitForm;
	private static JLabel cancelBtn;
	
	private JPanel panel;
	
	private static String[] fields;
	
	
	Components comp = new Components();
	private static boolean isFormSubmit;
	
	
	public RegisterPage() {
		isFormSubmit = false;
		fields = new String[8];
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
	}
	
	public JPanel header(String headerTitle) {
		
		JPanel panel = new JPanel();

		panel.setBackground(new Color(57, 118, 174));
	
		panel.setPreferredSize(new Dimension(350, 150));
		
		
		
		JLabel title = new JLabel(headerTitle);
		Dimension labelSize = title.getPreferredSize();
		labelSize.width = (int) title.getPreferredSize().getWidth();
		labelSize.height = (int) title.getPreferredSize().getHeight();
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		title.setPreferredSize(labelSize);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
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
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
		return panel;
		
		
	}
	

	
	public JPanel registerPanel(BankController controller, MouseClickListener mousecontroller) {
		
		
		
		panel.add(header("Register"), BorderLayout.NORTH);
		panel.add(forms(controller), BorderLayout.CENTER);
	
		
		panel.add(footer(mousecontroller, isFormSubmit ? "Return Home" : "Cancel"), BorderLayout.SOUTH);
		
		
		return panel;
	}
	
	
	public JPanel forms(BankController controller) {
		JPanel formsPanel = new JPanel();
		formsPanel.setBackground(new Color(255, 255, 255));

		
		firstName = new JTextField();
		firstName.setBackground(new Color(246, 246, 246));
		firstName.setColumns(10);
		comp.setTextFieldFocusListener(firstName, "First Name");
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBackground(new Color(246, 246, 246));
		comp.setTextFieldFocusListener(lastName, "Last Name");
		
		username = new JTextField();
		username.setColumns(10);
		username.setBackground(new Color(246, 246, 246));
		comp.setTextFieldFocusListener(username, "Username");
		
		email = new JTextField();
		email.setColumns(10);
		email.setBackground(new Color(246, 246, 246));
		comp.setTextFieldFocusListener(email, "Email");
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBackground(new Color(246, 246, 246));
		comp.setTextFieldFocusListener(phone, "Phone Number");
		
		address = new JTextField();
		address.setColumns(10);
		address.setBackground(new Color(246, 246, 246));
		comp.setTextFieldFocusListener(address, "Address");
		
		submitForm = new JButton("Register");
		submitForm.setBackground(new Color(72, 116, 169));
		submitForm.addActionListener(controller);
		
		password = new JPasswordField();
		password.setBackground(new Color(246, 246, 246));
		comp.setPasswordFieldFocusListener(password, "Password");
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBackground(new Color(246, 246, 246));
		comp.setPasswordFieldFocusListener(confirmPassword, "Confirm Password");
		
		GroupLayout gl_formsPanel = new GroupLayout(formsPanel);
		gl_formsPanel.setHorizontalGroup(
			gl_formsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_formsPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_formsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_formsPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(username, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
							.addGroup(Alignment.TRAILING, gl_formsPanel.createSequentialGroup()
								.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lastName, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
							.addComponent(email, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
							.addComponent(phone, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
							.addComponent(address, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
							.addComponent(password, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
							.addComponent(confirmPassword, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
						.addGroup(gl_formsPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 88, GroupLayout.PREFERRED_SIZE)
							.addComponent(submitForm, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(93)))
					.addGap(17))
		);
		gl_formsPanel.setVerticalGroup(
			gl_formsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_formsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_formsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lastName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(username, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(email, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(phone, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(address, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(password, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(confirmPassword, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(submitForm, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		formsPanel.setLayout(gl_formsPanel);
		
		
		
		return formsPanel;
	}
	
	

	public JPanel footer(MouseClickListener mousecontroller, String label) {
		
		setCancelBtn(new JLabel(label));

		return comp.setFooter(mousecontroller, getCancelBtn());
	}
	
	
	public void updatePanel(MouseClickListener mousecontroller, BankController controller) {
		
		 panel.removeAll();
		 
		 if(isFormSubmit) {
			 
			 panel.add(comp.showResults(true, null), BorderLayout.CENTER);
			 panel.add(footer(mousecontroller, isFormSubmit ? "Return Home" : "Cancel"), BorderLayout.SOUTH);
		 }else {
			registerPanel(controller,  mousecontroller);
			
		 }
		 panel.revalidate(); 
	     panel.repaint(); 
	}
	
	

	
	
	public static boolean verifyEmptyFields() {
		boolean isFormComplete = false;
		boolean conifrmForm = false;
		for(int i = 0; i < getFields().length; i++) {
			
			if(getFields()[i].equals("")) {
				showEmptyFields(i);
				isFormComplete = false;
			}else {
				isFormComplete = true;
			}
			
		}
		
		for(int i = 0; i < getFields().length; i++) {
			
			if(getFields()[i].equals("")) {
				
				isFormComplete = false;

			}
			
		}
		
		return isFormComplete;
	}
	
	
	public static void showEmptyFields(int index) {
		
		JTextField textField = new JTextField();
		
		switch(index) {
		case 0:
			textField = getFirstName();
			break;
		case 1:
			textField = getLastName();
			break;
		case 2:
			textField = getUsername();
			break;
		case 3:
			textField = getEmail();
			break;
		case 4:
			textField = getPhone();
			break;
		case 5:
			textField = getAddress();
			break;
		case 6:
			textField = getPassword();
			break;
		case 7:
			textField = getConfirmPassword();
			break;
		default:
			break;
		}
		
		textField.setForeground(Color.RED);

		
	}
	
	
	
	
	
	
	
	
	public static boolean setIsFormSubmit(boolean isFormSubmit) {
		return RegisterPage.isFormSubmit = isFormSubmit;
		
	}
	
	
	public static JLabel getCancelBtn() {
		return cancelBtn;
	}


	/**
	 * @param cancelBtn the cancelBtn to set
	 */
	public static void setCancelBtn(JLabel cancelBtn) {
		RegisterPage.cancelBtn = cancelBtn;
	}
	
	public static String[] getFields() {
		return fields;
	}

	/**
	 * @return the firstName
	 */
	public static JTextField getFirstName() {
		return firstName;
	}



	/**
	 * @return the lastName
	 */
	public static JTextField getLastName() {
		return lastName;
	}



	/**
	 * @return the username
	 */
	public static JTextField getUsername() {
		return username;
	}



	/**
	 * @return the email
	 */
	public static JTextField getEmail() {
		return email;
	}



	/**
	 * @return the phone
	 */
	public static JTextField getPhone() {
		return phone;
	}



	/**
	 * @return the address
	 */
	public static JTextField getAddress() {
		return address;
	}



	/**
	 * @return the password
	 */
	public static JPasswordField getPassword() {
		return password;
	}



	/**
	 * @return the confirmPassword
	 */
	public static JPasswordField getConfirmPassword() {
		return confirmPassword;
	}


	/**
	 * @return the submitForm
	 */
	public static JButton getSubmitForm() {
		return submitForm;
	}


	
	


}
