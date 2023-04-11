package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;

public class DisplayCustomersPage {
	
	
	private JTextField textField;
	private static JLabel cancelBtn;
	
	
	Components comp = new Components();
	
	public JPanel header(String headerTitle) {
		
		JPanel panel = new JPanel();

		panel.setBackground(new Color(57, 118, 174));
	
		panel.setPreferredSize(new Dimension(350, 150));
		
		
		JLabel title = new JLabel(headerTitle);
		title.setPreferredSize(new Dimension(20, 20));
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

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
	
	
	public JPanel displayPanel(BankController controller, MouseClickListener mousecontroller) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(header("Customers"), BorderLayout.NORTH);
		panel.add(listPanel(controller), BorderLayout.CENTER);
		panel.add(footer(mousecontroller), BorderLayout.SOUTH);
		
		return panel;
	}
	
	public JPanel listPanel(BankController controller) {
			
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(new Color(255, 255, 255));
	
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(null);
		searchPanel.setBackground(new Color(255, 255, 255));
		
		
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setBackground(new Color(246, 246, 246));
		textField = comp.setTextFieldFocusListener(textField, "ID");
		
		searchPanel.add(textField, BorderLayout.WEST);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		searchPanel.add(btnSearch, BorderLayout.EAST);
		
		JPanel listPanel = new JPanel();
		
		GroupLayout gl_displayPanel = new GroupLayout(displayPanel);
		gl_displayPanel.setHorizontalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_displayPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_displayPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(listPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(searchPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_displayPanel.setVerticalGroup(
			gl_displayPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_displayPanel.createSequentialGroup()
					.addGap(15)
					.addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(listPanel, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		listPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		//Header
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(200, 40));
		headerPanel.setBackground(new Color(105, 148, 187));
		headerPanel.setBorder(BorderFactory.createCompoundBorder(
				headerPanel.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		listPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel listNumber = new JLabel("No.");
		listNumber.setForeground(new Color(255, 255, 255));
		listNumber.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(listNumber, BorderLayout.WEST);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(new Color(255, 255, 255));
		headerPanel.add(nameLabel, BorderLayout.CENTER);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setPreferredSize(new Dimension(60, 0));
		headerPanel.add(idLabel, BorderLayout.EAST);
		idLabel.setForeground(new Color(255, 255, 255));
		//Header
		
		
		
		JPanel customersList = new JPanel();
		
		listPanel.add(customersList, BorderLayout.CENTER);
		customersList.setLayout(new BorderLayout(0, 0));
		
		JTextArea displayedList = new JTextArea();
		displayedList.setEditable(false);
		displayedList.setBorder(BorderFactory.createCompoundBorder(
				displayedList.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		
		
		customersList.add(displayedList);
		
		JScrollPane sp = new JScrollPane(displayedList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		customersList.add(sp);
		
		
		//Temp
		JLabel index = new JLabel("1");
		JLabel name = new JLabel("Test Name");
		JLabel id = new JLabel("1001");
		
		for(int i = 1; i < 20; i++) {
			

		
			JLabel btn = new JLabel(String.valueOf(String.format("%2s\t%s\t%11s", i,  name.getText(), id.getText())));
			
		
			displayedList.append(btn.getText() + "\n");
		}
		

		displayPanel.setLayout(gl_displayPanel);
		
		
		return displayPanel;
	}
	
	public JPanel footer(MouseClickListener mousecontroller) {
		setCancelBtn(new JLabel("Cancel"));
		return comp.setFooter(mousecontroller, getCancelBtn());
	}
	
	
	public ArrayList<JLabel> list() {
		ArrayList<JLabel> customerList = new ArrayList<>();
		
		
		
		return customerList;
	}
	
	
	public static JLabel getCancelBtn() {
		return cancelBtn;
	}
	
	public static JLabel setCancelBtn(JLabel cancelBtn) {
		return DisplayCustomersPage.cancelBtn = cancelBtn;
	}
}
