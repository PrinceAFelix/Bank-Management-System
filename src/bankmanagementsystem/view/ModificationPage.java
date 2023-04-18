package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;

public class ModificationPage extends Results {
	
	private static JTextField textField;
	private static JLabel cancelBtn;
	private JPanel panel;
	private static JButton confirmBtn;
	
	private static boolean isEditing;
	
	private JTextField firstName;
	private JTextField lastName;
	private JTextField username;
	private JTextField email;
	private JTextField phoneNumber;
	private JTextField address;
	private JButton saveBtn;
	
	private Map<Integer, User> modifyingUser = new HashMap<>();
	
	Components comp = new Components();
	
	public ModificationPage() {
		isEditing = false;
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
	
	
	public JPanel ModifyPanel(BankController controller,  MouseClickListener mousecontroller) {
		
		
		reRender(controller, mousecontroller, null);
		
		
		return panel;
	}
	
	public JPanel forms(BankController controller,  MouseClickListener mousecontroller) {
		
		JPanel forms = new JPanel();
		forms.setBackground(new Color(255, 255, 255));

		
		textField = new JTextField();
		
		textField = comp.setTextFieldFocusListener(textField, "User's Id");
		
		JLabel lblNewLabel = new JLabel("Enter Customer's ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(controller);
		
		GroupLayout gl_forms = new GroupLayout(forms);
		gl_forms.setHorizontalGroup(
			gl_forms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_forms.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addGroup(gl_forms.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_forms.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
							.addGap(39))
						.addGroup(Alignment.TRAILING, gl_forms.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(109))))
				.addGroup(gl_forms.createSequentialGroup()
					.addGap(88)
					.addComponent(confirmBtn, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_forms.setVerticalGroup(
			gl_forms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_forms.createSequentialGroup()
					.addGap(124)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(confirmBtn, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(171, Short.MAX_VALUE))
		);
		forms.setLayout(gl_forms);
		
		
		return forms;
		
		
	}
	
	
	public JPanel modifyForms(BankController controller, User user) {
		JPanel form = new JPanel();
		
		
		firstName = new JTextField();
		firstName = comp.setTextFieldFocusListener(firstName, user.getFullName().substring(0, user.getFullName().indexOf(" ")));
		
		
		lastName = new JTextField();
		lastName = comp.setTextFieldFocusListener(lastName, user.getFullName().substring(user.getFullName().indexOf(" ") + 1));
		
		
		username = new JTextField();
		username = comp.setTextFieldFocusListener(username, user.getUsername());
		
		
		email = new JTextField();
		email = comp.setTextFieldFocusListener(email, user.getEmail());
		
		
		phoneNumber = new JTextField();
		phoneNumber = comp.setTextFieldFocusListener(phoneNumber, user.getPhone());
		
		
		address = new JTextField();
		address = comp.setTextFieldFocusListener(address, user.getAddress());
		
		
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(controller);
		GroupLayout gl_form = new GroupLayout(form);
		gl_form.setHorizontalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_form.createParallelGroup(Alignment.LEADING)
						.addComponent(address, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_form.createParallelGroup(Alignment.LEADING)
							.addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_form.createParallelGroup(Alignment.LEADING)
								.addComponent(email, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_form.createParallelGroup(Alignment.TRAILING)
									.addComponent(username, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
									.addGroup(gl_form.createSequentialGroup()
										.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lastName))))))
					.addGap(23))
				.addGroup(Alignment.TRAILING, gl_form.createSequentialGroup()
					.addContainerGap(99, Short.MAX_VALUE)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
		);
		gl_form.setVerticalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastName, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(username, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(email, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(address, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		form.setLayout(gl_form);
		
		return form;
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
		ModificationPage.cancelBtn = cancelBtn;
	}
	
	
	public static JTextField getTextField() {
		return textField;
	}
	
	
	public static JButton getcConfirmBtn() {
		return confirmBtn;
	}
	
	/**
	 * @return the isEditing
	 */
	public static boolean isEditing() {
		return isEditing;
	}

	/**
	 * @param isEditing the isEditing to set
	 */
	public static void setEditing(boolean isEditing) {
		ModificationPage.isEditing = isEditing;
	}
	
	public JPanel getPanel() {
		return panel;
	}


	@Override
	public void reRender(BankController controller, MouseClickListener mousecontroller, ArrayList<User> users) {
		panel.add(header("Admin"), BorderLayout.NORTH);
		panel.add(forms(controller, mousecontroller), BorderLayout.CENTER);
		panel.add(footer(mousecontroller), BorderLayout.SOUTH);
	}


	@Override
	public void condition(User user, BankController controller, MouseClickListener mousecontroller,
			String[] deleteduser) {
		
		panel.add(header("Edit"), BorderLayout.NORTH);
		panel.add(modifyForms(controller, user), BorderLayout.CENTER);
		panel.add(footer(mousecontroller), BorderLayout.SOUTH);
	}

	/**
	 * @return the firstName
	 */
	public JTextField getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public JTextField getLastName() {
		return lastName;
	}

	/**
	 * @return the username
	 */
	public JTextField getUsername() {
		return username;
	}

	/**
	 * @return the email
	 */
	public JTextField getEmail() {
		return email;
	}

	/**
	 * @return the phoneNumber
	 */
	public JTextField getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the address
	 */
	public JTextField getAddress() {
		return address;
	}

	/**
	 * @return the saveBtn
	 */
	public JButton getSaveBtn() {
		return saveBtn;
	}

	/**
	 * @return the modifyingUser
	 */
	public Map<Integer, User> getModifyingUser() {
		return modifyingUser;
	}

	/**
	 * @param modifyingUser the modifyingUser to set
	 */
	public void setModifyingUser(Map<Integer, User> modifyingUser, int key, User value) {
		modifyingUser.put(key, value);
	};

	
	
	


	

}
