package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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

public class ModificationPage {
	
	private JTextField textField;
	private static JLabel cancelBtn;
	
	Components comp = new Components();
	
	public JPanel header(String headerTitle) {
		
		JPanel panel = new JPanel();

		panel.setBackground(new Color(57, 118, 174));
	
		panel.setPreferredSize(new Dimension(350, 150));
		
		JLabel bankLocation = new JLabel("Location");
		bankLocation.setForeground(new Color(255, 255, 255));
		
		JLabel bankID = new JLabel("BankID");
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
	
	
	public JPanel ModifyPanel(BankController controller,  MouseClickListener mousecontroller) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(header("Admin"), BorderLayout.NORTH);
		panel.add(forms(controller, mousecontroller), BorderLayout.CENTER);
		panel.add(footer(mousecontroller), BorderLayout.SOUTH);
		
		
		return panel;
	}
	
	public JPanel forms(BankController controller,  MouseClickListener mousecontroller) {
		
		JPanel forms = new JPanel();
		forms.setBackground(new Color(255, 255, 255));

		
		textField = new JTextField();
		
		textField = comp.setTextFieldFocusListener(textField, "User's Id");
		
		JLabel lblNewLabel = new JLabel("Enter Customer's ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton confirmBtn = new JButton("Confirm");
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
	

}
