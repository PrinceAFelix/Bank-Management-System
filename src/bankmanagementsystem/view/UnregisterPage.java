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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;

public class UnregisterPage {
	
	private JTextField textField;
	private static JLabel cancelBtn;
	
	Components comp = new Components();

	
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
	

	

	
	public JPanel unregisterPanel(BankController controller, MouseClickListener mousecontroller) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(header("Remove"), BorderLayout.NORTH);
		panel.add(forms(controller, mousecontroller), BorderLayout.CENTER);
		panel.add(footer(mousecontroller), BorderLayout.SOUTH);
		
		return panel;
	}
	
	public JPanel forms(BankController controller, MouseClickListener mousecontroller) {
		
		JPanel formsPanel = new JPanel();
		formsPanel.setBackground(new Color(255, 255, 255));

		
		textField = new JTextField();
		comp.setTextFieldFocusListener(textField, "User's ID");
		
		JButton submitBtn = new JButton("Cofirm");
		GroupLayout gl_formsPanel = new GroupLayout(formsPanel);
		gl_formsPanel.setHorizontalGroup(
			gl_formsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_formsPanel.createSequentialGroup()
					.addGroup(gl_formsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_formsPanel.createSequentialGroup()
							.addGap(33)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_formsPanel.createSequentialGroup()
							.addGap(91)
							.addComponent(submitBtn, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_formsPanel.setVerticalGroup(
			gl_formsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_formsPanel.createSequentialGroup()
					.addGap(150)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(submitBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(222, Short.MAX_VALUE))
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
		UnregisterPage.cancelBtn = cancelBtn;
	}


	/**
	 * @return the textField
	 */
	public JTextField getTextField() {
		return textField;
	}





	/**
	 * @param textField the textField to set
	 */
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}










}
