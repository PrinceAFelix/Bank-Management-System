package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.NumberFormatter;

import bankmanagementsystem.Atm;
import bankmanagementsystem.AtmTransaction;
import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;

public class AtmTransactionPage {

	Components comp = new Components();
	Atm atm = new Atm();
	private JPanel panel;
	
	private static JComboBox<Object> comboBox;
	private static JFormattedTextField formattedTextField;
	private static JButton continueBtn;
	private static JLabel cancelBtn;
	private static int operation;
	private static String account;
	
	
	public AtmTransactionPage() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
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
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		title.setPreferredSize(labelSize);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(130)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(50)
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
		return panel;
		
		
	}
	
	public JPanel atmTransactionPanel(BankController controller, MouseClickListener mousecontroller, String operation) {
		
		panel.add(header(operation), BorderLayout.NORTH);
		panel.add(main(controller), BorderLayout.CENTER);
		panel.add(footer(mousecontroller, "Cancel"), BorderLayout.SOUTH);
		
		return panel;
		
	}
	
	public JPanel main(BankController controller) {
		JPanel transactionPanel = new JPanel();
		transactionPanel.add(Box.createRigidArea(new Dimension(80, 80)));
		transactionPanel.setBackground(new Color(217, 217, 217));
		transactionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
		
		JPanel accountPanel = new JPanel();
		accountPanel.setBackground(new Color(255, 255, 255));
		transactionPanel.add(accountPanel);
		
		accountPanel.setPreferredSize(new Dimension(350, 70));
		accountPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel fromAccount = new JLabel(
				"<html>"
				+ "<body style='margin-left: 10px;'>"
				+ 	"<span style='font-size: 10px; color: #3976AE;'>From Account</span><br>"
				+ 	"<span style=\"font-size: 12px; color: rgba(0, 0, 0, 0.2);\">Select an Account</span><br><br>"
				+ "</body>"
				+"</html>"
				);
		
//		" + getAccount() == null ? "Select an Account" : getAccount() + "
		
		
		accountPanel.add(fromAccount, BorderLayout.WEST);
		

		comboBox = new JComboBox<Object>(new String[]{"Chequing", "Savings"});
		comboBox.setPreferredSize(new Dimension(150, 50));
		comboBox.addActionListener(controller);
		comboBox.setSelectedIndex(-1);
		
		accountPanel.add(comboBox, BorderLayout.EAST);
		
		JPanel amountPanel = new JPanel();
		
		amountPanel.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createEmptyBorder(0, 0, 0, 10), // Top padding of 10 pixels
		        amountPanel.getBorder())); 
		
		amountPanel.setPreferredSize(new Dimension(350, 50));
		amountPanel.setBackground(Color.WHITE);
		transactionPanel.add(amountPanel);
		amountPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel fromAccount_1 = new JLabel("<html>"
				+ "<body style='margin-left: 10px;'>"
				+ 	"<span style='font-size: 10px; color: #3976AE;'>Amount</span>"
				+ "</body>"
				+ "</html>");
		amountPanel.add(fromAccount_1, BorderLayout.WEST);
		
		
		
		// Create a NumberFormat object to format the text field with
		
		DecimalFormat format = new DecimalFormat("#0.00");
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Double.class);
		formatter.setMinimum(0.0);
		formatter.setMaximum(100.0);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);

		formattedTextField = new JFormattedTextField(formatter);
		formattedTextField  = (JFormattedTextField) comp.setTextFieldFocusListener(formattedTextField, "$0.00");
		
		amountPanel.add(formattedTextField, BorderLayout.EAST);
	
		
		transactionPanel.add(Box.createRigidArea(new Dimension(150, 150)));
		
		continueBtn = new JButton("Continue");
		continueBtn.addActionListener(controller);
		continueBtn.setPreferredSize(new Dimension(220, 40));
		continueBtn.setForeground(Color.WHITE);
		continueBtn.setBackground(new Color(57, 118, 174));
		continueBtn.setOpaque(true);
		continueBtn.setBorderPainted(false);
		transactionPanel.add(continueBtn);
		
		return transactionPanel;
	}
	
	public JPanel footer(MouseClickListener mousecontroller, String label) {
		
		setCancelBtn(new JLabel(label));

		return comp.setFooter(mousecontroller, getCancelBtn());
	}
	
	
	
	public boolean processOperation(User user, float amount, int account) {
		Random random = new Random();
		
		if(getOperation() == 0) {
			if(!atm.deposit(user, amount, account)) return false;
		}else if(getOperation() == 1) {
			if(!atm.withdraw(user, amount, account)) return false;
		}
		AtmTransaction transactionTemp = new AtmTransaction(random.nextInt(10000),
				account == 0 ? "Chequing" : "Savings", comp.getCurrentDate(), getOperation() == 0 ? "Deposit" : "Withdrawal", amount,
				user.getUserAccount().get(account).accountBalance);
		
		UserAccountPage.getActiveUser().getTransactions(account).add(transactionTemp);
		
		return true;
	}
	
	
	public static JLabel getCancelBtn() {
		return cancelBtn;
	}


	/**
	 * @param cancelBtn the cancelBtn to set
	 */
	public static void setCancelBtn(JLabel cancelBtn) {
		AtmTransactionPage.cancelBtn = cancelBtn;
	}


	/**
	 * @return the comboBox
	 */
	public static JComboBox<Object> getComboBox() {
		return comboBox;
	}


	/**
	 * @return the formattedTextField
	 */
	public static JFormattedTextField getFormattedTextField() {
		return formattedTextField;
	}


	/**
	 * @return the continueBtn
	 */
	public static JButton getContinueBtn() {
		return continueBtn;
	}


	/**
	 * @return the operation
	 */
	public static int getOperation() {
		return operation;
	}


	/**
	 * @param operation the operation to set
	 */
	public static void setOperation(int operation) {
		AtmTransactionPage.operation = operation;
	}


	/**
	 * @return the account
	 */
	public static String getAccount() {
		return account;
	}


	/**
	 * @param account the account to set
	 */
	public static void setAccount(String account) {
		AtmTransactionPage.account = account;
	}
}
