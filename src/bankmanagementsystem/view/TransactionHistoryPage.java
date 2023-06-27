package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bankmanagementsystem.AtmTransaction;
import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;

public class TransactionHistoryPage {
	
	
	private JPanel panel;
	private static JLabel cancelBtn;
	Components comp = new Components();
	
	public TransactionHistoryPage(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
	}
	
	private int i = 0, preferredHeight = 0;
	
	
	
	public JPanel header(ArrayList<UserAccount> accounts, int account) {
		JPanel header = new JPanel();
		
		header.setBackground(new Color(57, 118, 174));
	
		header.setPreferredSize(new Dimension(350, 150));
		
		System.out.println(accounts.size());
		
		JLabel accountBalance = new JLabel(String.valueOf(String.format("$%.2f", accounts.get(account).accountBalance)));
		accountBalance.setHorizontalAlignment(SwingConstants.CENTER);
		accountBalance.setForeground(new Color(100, 184, 106));
		accountBalance.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		accountBalance.setPreferredSize(new Dimension(100, 60));
		
		JLabel accountLabel = new JLabel(
				"<html>"
				+ "<body>"
				+ 	"<span style='font-size: 14px; color: white;'>" + accounts.get(account).accountTitle.toUpperCase() + "</span><br>"
				+ 	"<span style=\"color: rgba(255, 255, 255, 0.4);\">" + accounts.get(account).accountNumber + "</span>"
				+ "</body>"
				+"</html>"
				);
		
		
		GroupLayout gl_panel = new GroupLayout(header);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(accountLabel)
					.addContainerGap(76, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(72, Short.MAX_VALUE)
					.addComponent(accountBalance, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addGap(64))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addComponent(accountLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(accountBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		header.setLayout(gl_panel);
		
		return header;
	}
	
	
	
	public JPanel chequingPanel(BankController controller, MouseClickListener mousecontroller, User user, int account) {

		
		panel.add(header(user.getUserAccount(), account), BorderLayout.NORTH);
		panel.add(transactions(user, account), BorderLayout.CENTER);
		panel.add(footer(mousecontroller, "Back"), BorderLayout.SOUTH);


		
		return panel;
	}
	
	
	
	public JPanel transactions(User user, int account) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); 

		
		JPanel transactionPanel = new JPanel();
		transactionPanel.setBackground(new Color(217, 217, 217));
		transactionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		transactionPanel.setPreferredSize(new Dimension(350, 350));
		transactionPanel.setAlignmentY(FlowLayout.LEADING);
		panel.add(transactionPanel);
		
		JLabel transactionLabel = new JLabel("TRANSACTIONS");
		transactionLabel.setHorizontalAlignment(SwingConstants.CENTER);

		transactionLabel.setPreferredSize(new Dimension(350, 50));
		transactionLabel.setForeground(Color.WHITE);
		transactionLabel.setBackground(new Color(5, 52, 96));
		transactionLabel.setOpaque(true);
		transactionPanel.add(transactionLabel);
		
		preferredHeight = panel.getPreferredSize().height; 
		
		
		for(int i = user.getTransactions(account).size() - 1; i >= 0; i--) {
			
			AtmTransaction tr = user.getTransactions(account).get(i);
			transactionPanel.add(transactionItem(transactionPanel, tr.getTransaction_Date(), tr.getTransaction_Type(), tr.getTransaction_Amount()));

			if (i >= 5) { // if more than 5 components added, adjust preferred height
				preferredHeight += 70;
				transactionPanel.setPreferredSize(new Dimension(350, preferredHeight));
		    }
		}
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(transactionPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panel.add(scrollPane, BorderLayout.CENTER);
		

		return panel;
	}
	
	
	public JPanel transactionItem(JPanel transactionPanel, String dt, String ttype, float amount) {
		JPanel transactionItem = new JPanel();
		
		transactionItem.setPreferredSize(new Dimension(350, 70));
		transactionItem.setBackground(Color.WHITE);
		transactionItem.setLayout(new BorderLayout(0, 0));
		

		String color = ttype.equals("Withdrawal") ? "#000000" : "#3EBB61";
		
		String addSign = color.equals("#000000") ? "-" : "";
		
		JLabel itemLabel = new JLabel(
				"<html>"
				+ "<body>"
				+ 	"<span style='font-size: 9px; color: rgba(0, 0, 0, 0.4);'>"+ dt +"</span><br>"
				+ 	"<span style='font-size: 12px; color: black;'>" + ttype + "</span><br>"
				+ "</body>"
				+"</html>"
				);
		
		JLabel ammountLabel = new JLabel(
				"<html>"
				+ "<body>"
				+ 	"<span style='font-weight: bold; font-size: 14px; color: " + color + ";'>" + addSign + "$" + String.valueOf(String.format("%.2f", amount)) + "</span><br>"
				+ "</body>"
				+"</html>"
				);
		
		transactionItem.add(itemLabel, BorderLayout.WEST);
		transactionItem.add(ammountLabel, BorderLayout.EAST);
		
		transactionItem.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		
		transactionPanel.add(Box.createRigidArea(new Dimension(2, 2)));
		
		return transactionItem;
	}
	
	
	public JPanel footer(MouseClickListener mousecontroller, String label) {
		
		setCancelBtn(new JLabel(label));
		
	
		return comp.setFooter(mousecontroller, getCancelBtn());
	}
	
	public static JLabel getCancelBtn() {
		return cancelBtn;
	}


	/**
	 * @param cancelBtn the cancelBtn to set
	 */
	public static void setCancelBtn(JLabel cancelBtn) {
		TransactionHistoryPage.cancelBtn = cancelBtn;
	}

}



