package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;

public class UserAccount extends UserAccountPage {
	
	private static JLabel backBtn;
	private User user;
	
	private static JButton chequingBtn;
	private static JButton savingsBtn;
	
	Components comp = new Components();
	
	public JPanel userAccountPanel(BankController contorller, MouseClickListener mouseListener) {
		user = super.getActiveUser();
		
		setBackBtn(new JLabel("Back"));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(super.header(user.getFullName()), BorderLayout.NORTH);
		panel.add(main(contorller, user), BorderLayout.CENTER);
		panel.add(comp.setFooter(mouseListener, getBackBtn()), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel main(BankController contorller, User user) {
		
		boolean savingIsNull = user.getUserAccount().size() == 1 ? true : false;
		
		JPanel main = new JPanel();
		
		main.setLayout(new BorderLayout(0, 0));
		
		JPanel accounts = new JPanel();
		accounts.setPreferredSize(new Dimension(350, 230));
		accounts.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createEmptyBorder(10, 0, 0, 0), // Top padding of 10 pixels
		        accounts.getBorder())); 
		
		main.add(accounts, BorderLayout.NORTH);
		
		float[] balance = {user.getUserAccount().get(0).accountBalance, savingIsNull ? 0 : user.getUserAccount().get(1).accountBalance};
		
		JLabel[] amount = {new JLabel(setAmountText(String.valueOf(String.format("%.2f", balance[0])), "14px")), new JLabel(setAmountText(String.valueOf(String.format("%.2f", balance[1])), "14px")), new JLabel(setAmountText(String.valueOf(String.format("%.2f", balance[0] + balance[1])), "10px"))};
	
		
		JLabel chequing = new JLabel(setTitleText("CHEQUING", user.getUserAccount().get(0).accountNumber));
		chequingBtn = accountBtns(new BorderLayout(0, 0), new Dimension(280, 70), "");
	
		accounts.add(chequingBtn);
		chequingBtn.add(amount[0], BorderLayout.EAST);
		chequingBtn.add(chequing, BorderLayout.WEST);
		
		chequingBtn.addActionListener(contorller);
		
		if(!savingIsNull) {
			JLabel saving = new JLabel(setTitleText("SAVINGS", user.getUserAccount().get(1).accountNumber));
			savingsBtn = accountBtns(new BorderLayout(0, 0), new Dimension(280, 70), "");
			accounts.add(savingsBtn);
			savingsBtn.add(saving, BorderLayout.WEST);
			savingsBtn.add(amount[1], BorderLayout.EAST);
			savingsBtn.addActionListener(contorller);
		}
		
		JLabel totalTitle = new JLabel("<html><body>" +
	            "<span style='font-size: 12px; font-weight: bold; color: FFFFFF;'> Total </span>" +
	            "</body></html>");
		JButton total = accountBtns(new BorderLayout(0, 0), new Dimension(275, 40), "Total");
		
		accounts.add(total);
		total.add(totalTitle, BorderLayout.WEST);
		total.add(amount[2], BorderLayout.EAST);
		
		JPanel accountsOptions = new JPanel();
		main.add(accountsOptions, BorderLayout.CENTER);
		
		JButton openAccountBtn = accountBtns(null, new Dimension(280, 50), "Open Account");
		accountsOptions.add(openAccountBtn);
		
		JButton deleteAccountBtn = accountBtns(null, new Dimension(280, 50), "Delete Account");
		accountsOptions.add(deleteAccountBtn);
		
		return main;
	}
	
	
	
	private JButton accountBtns(LayoutManager lo, Dimension dm, String text) {
		
		
		JButton btn = new JButton(text);
		btn.setMargin(new Insets(5, 10, 5, 10));
		btn.setLayout(lo);
		btn.setPreferredSize(dm);
		btn.setFocusable(false);
		
		if(text == "Total") {
			btn.setBackground(new Color(57, 118, 174));
			btn.setOpaque(true);
			btn.setBorderPainted(false);
			btn.setText("");
			System.out.println("Total");
		}

		return btn;
	}
	
	
	private String setTitleText(String title, String accNum) {
		return "<html><body>" +
	            "<span style='font-size: 14px; font-weight: bold;'>" + title + "</span>" +
	            "<br>" +
	            "<span style='font-size: 9px; color: gray;'>" + accNum + "</span>" +
	            "</body></html>";
	}
	
	private String setAmountText(String amount, String fontSize) {
		return "<html><body>" +
	            "<span style='font-size:" + fontSize + "; font-weight: bold; color: 3EBB61;'> $" + amount + "</span>" +
	            "</body></html>";
	}
	
	
	public static JButton getChequingBtn() {
		return chequingBtn;
	}
	
	public static JButton getSavingsBtn() {
		return savingsBtn;
	}
	
	
	
	public static JLabel getBackBtn() {
		return backBtn;
	}


	/**
	 * @param cancelBtn the cancelBtn to set
	 */
	public static void setBackBtn(JLabel backBtn) {
		UserAccount.backBtn = backBtn;
	}

}
