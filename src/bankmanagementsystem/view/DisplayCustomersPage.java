package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;

public class DisplayCustomersPage extends Results {
	
	
	private JTextField textField;
	private static JLabel cancelBtn;
	
	
	public static JLabel[] userBtn;
	public static Map<JLabel, User> map = new HashMap<>();
	
	private static boolean isSearch;
	private JPanel panel;
	private static JButton btnSearch;
	private String userInput;
	private static boolean isShwoingInformation;
	
	public DisplayCustomersPage(){
		isShwoingInformation = false;
		userInput = "";
		isSearch = false;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
	}
	
	
	
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
	
	
	public JPanel displayPanel(BankController controller, MouseClickListener mousecontroller,  ArrayList<User> users) {

		
		panel.add(header("Customers"), BorderLayout.NORTH);
		panel.add(listPanel(controller, mousecontroller, users), BorderLayout.CENTER);
		panel.add(footer(mousecontroller, "Return Home"), BorderLayout.SOUTH);
		
		return panel;
	}
	
	public JPanel listPanel(BankController controller, MouseClickListener mousecontroller, ArrayList<User> users) {
			
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(new Color(255, 255, 255));
	
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(null);
		searchPanel.setBackground(new Color(255, 255, 255));
		
		
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setBackground(new Color(246, 246, 246));
		textField = comp.setTextFieldFocusListener(textField, "ID");
		if(isSearch)
			 textField.setText(getUserInput());
		
		searchPanel.add(textField, BorderLayout.WEST);
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		searchPanel.add(btnSearch, BorderLayout.EAST);
		
		btnSearch.addActionListener(controller);
		
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
		
		JTextPane displayedList = new JTextPane();
		
		
		
		StyledDocument doc = displayedList.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		StyleConstants.setRightIndent(center, 30);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		displayedList.removeMouseListener(mousecontroller);
		displayedList.setEditable(false);
		displayedList.setBorder(BorderFactory.createCompoundBorder(
				displayedList.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		
		
		customersList.add(displayedList);
		
		JScrollPane sp = new JScrollPane(displayedList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		customersList.add(sp);

		userBtn = new JLabel[users.size()];
		
		for(int i = 0; i < users.size(); i++) {
			
			
			JLabel btn = new JLabel(String.valueOf(String.format("%2s%30s%20s", (i + 1), users.get(i).getFullName(), users.get(i).getId())));
			
			userBtn[i] = btn;
			userBtn[i].addMouseListener(mousecontroller);
			
			map.put(userBtn[i], users.get(i));
	
			
			if(isSearch) {
				
				if(getUserInput().equals(users.get(i).getId())) {
					System.out.println("Found");
					displayedList.insertComponent(userBtn[i]);
				}
			}else {
				displayedList.insertComponent(userBtn[i]);
				try {
					displayedList.getDocument().insertString(displayedList.getDocument().getLength(), "\n", null);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			
		}

		displayPanel.setLayout(gl_displayPanel);
		
		
		return displayPanel;
	}
	
	
	public JPanel showUser(User user) {
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(new Color(255, 255, 255));
	
		
		JPanel searchResultPanel = new JPanel();
//		searchResultPanel.setPreferredSize(new Dimension(300, 50));
		
		searchResultPanel.setBorder(new LineBorder(Color.BLACK, 1));
		
		GroupLayout gl_displayPanel = new GroupLayout(displayPanel);
		gl_displayPanel.setHorizontalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_displayPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(searchResultPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_displayPanel.setVerticalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_displayPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(searchResultPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(217, Short.MAX_VALUE))
		);
		searchResultPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		//Header
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(300, 40));
		headerPanel.setBackground(new Color(105, 148, 187));
		headerPanel.setBorder(BorderFactory.createCompoundBorder(
				headerPanel.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		searchResultPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel headerLabel = new JLabel("Customer's Information");
		headerLabel.setForeground(new Color(255, 255, 255));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(headerLabel, BorderLayout.CENTER);
		
		
	
		JPanel customerIformation = new JPanel();
		
		searchResultPanel.add(customerIformation, BorderLayout.CENTER);
		customerIformation.setLayout(new BorderLayout(0, 0));
		
		JTextPane informationResult = new JTextPane();

		informationResult.setEditable(false);
		informationResult.setBorder(BorderFactory.createCompoundBorder(
				informationResult.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		StyledDocument doc = informationResult.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);


		try {
			informationResult.getDocument().insertString(informationResult.getDocument().getLength(), String.format("ID: %s\nName: %s\nEmail: %s\nPhone: +1 %s\nAddress: %s", user.getId(), user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress()), null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		
		customerIformation.add(informationResult);
		

		displayPanel.setLayout(gl_displayPanel);
		
		return displayPanel;
	}
	
	public JPanel footer(MouseClickListener mousecontroller, String label) {
		setCancelBtn(new JLabel(label));
		return comp.setFooter(mousecontroller, getCancelBtn());
	}
	
	
	public ArrayList<JLabel> list() {
		ArrayList<JLabel> customerList = new ArrayList<>();
		
		
		
		return customerList;
	}
	
	public static boolean getIsShwoingInformation() {
		return isShwoingInformation;
	}
	
	public static boolean setIsShwoingInformation(Boolean isShwoingInformation) {
		return DisplayCustomersPage.isShwoingInformation = isShwoingInformation;
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public static JLabel getCancelBtn() {
		return cancelBtn;
	}
	
	public static JLabel setCancelBtn(JLabel cancelBtn) {
		return DisplayCustomersPage.cancelBtn = cancelBtn;
	}
	
	
	public static JButton getBtnSearch() {
		return btnSearch;
	}
	
	public static boolean getIsSearch() {
		return isSearch;
	}
	
	public static boolean setIsSearch(boolean isSearch) {
		return DisplayCustomersPage.isSearch = isSearch;
		
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * @return the userInput
	 */
	public String getUserInput() {
		return userInput;
	}


	/**
	 * @param userInput the userInput to set
	 */
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}


	@Override
	public void condition(User user, BankController controller, MouseClickListener mousecontroller, String[] deleteduser) {
		panel.add(header("Customers"), BorderLayout.NORTH);
		panel.add(showUser(user), BorderLayout.CENTER);
		panel.add(footer(mousecontroller, "Back"), BorderLayout.SOUTH);
	}




	@Override
	public void reRender(BankController controller, MouseClickListener mousecontroller, ArrayList<User> users) {
		panel.add(header("Customers"), BorderLayout.NORTH);
		panel.add(listPanel(controller, mousecontroller, users), BorderLayout.CENTER);
		panel.add(footer(mousecontroller, "Return Home"), BorderLayout.SOUTH);
	}


	
}
