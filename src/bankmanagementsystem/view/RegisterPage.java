package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.ModuleLayer.Controller;

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
	
	private JTextField firstName;
	private JTextField lastName;
	private JTextField username;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	private JPasswordField password;
	private JPasswordField confirmPassword;
	private static JLabel cancelBtn;
	
	Components comp = new Components();
	
	
	public RegisterPage() {}
	
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
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(header("Register"), BorderLayout.NORTH);
		panel.add(forms(controller), BorderLayout.CENTER);
		
		panel.add(footer(mousecontroller), BorderLayout.SOUTH);
		
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
		
		JButton submitForm = new JButton("Register");
		submitForm.setBackground(new Color(72, 116, 169));
		
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

	public JPanel footer(MouseClickListener mousecontroller) {
		
		setCancelBtn(new JLabel("Cancel"));

		return comp.setFooter(mousecontroller, getCancelBtn());
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


}
