package bankmanagementsystem.view;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.NumberFormatter;

import bankmanagementsystem.controller.BankController;

import javax.swing.JPanel;

public class ManageUserAccounts extends JDialog{
	private static final long serialVersionUID = 1L;



	/** Has the user pressed the connect button? */
    Boolean hasConnected=false;
    Components comp = new Components();


    private static JComboBox<String> accounts;
    private static JFormattedTextField amountField;
    private static JButton cancelbtn;
    private static JButton confirmbtn;
    

    //This will hold your UI.  Of course you may rename it.
    Container modalPanel = getContentPane();
    
    GridBagConstraints c = new GridBagConstraints();
    
    Controller handler = new Controller();
   

    
    
    public ManageUserAccounts(JPanel panel, BankController controller, boolean isOpenAccount, String ac){
    	super((Frame) SwingUtilities.getWindowAncestor(panel), "My Dialog", true);
 
        setUndecorated(true);
        setSize(245, 150);
        setLocationRelativeTo(null);
      
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        


       
        
        
        String[] accountOptions = {"", "Savings"};
        accounts = new JComboBox<>(accountOptions);
        accounts.setEditable(true);
        accounts.setSelectedIndex(0);

    
        

        modalPanel = new JPanel();
  
        modalPanel.setPreferredSize(new Dimension(300, 210));
		 ((JComponent) modalPanel).setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

		

		
		cancelbtn = new JButton("Cancel");
		
		confirmbtn = new JButton("Confirm");
		
        //Set the action command, to be use in action listener
		confirmbtn.setActionCommand(ac);
		cancelbtn.setActionCommand("X");
        
        //Add two buttons to action listener
		confirmbtn.addActionListener(controller);
        cancelbtn.addActionListener(controller);
		
		JLabel label1 = new JLabel("Choose an account");
		
		DecimalFormat format = new DecimalFormat("#0.00");
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Double.class);
		formatter.setMinimum(0.0);
		formatter.setMaximum(100.0);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		
		
		
		amountField = new JFormattedTextField(formatter);
		amountField = (JFormattedTextField) comp.setTextFieldFocusListener(amountField, "$0.00");
		amountField.setColumns(10);
		
		JLabel label2 = new JLabel("Enter Amount");
		
		if(!isOpenAccount) {
			amountField.setVisible(false);
			label2.setVisible(false);
		}
		
		
		GroupLayout gl_panel = new GroupLayout(modalPanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(25)
							.addComponent(label1)))
					.addContainerGap(50, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(cancelbtn)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(confirmbtn)
							.addGap(40))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label2)
										.addComponent(amountField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
								.addComponent(accounts, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(50, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addComponent(label1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(accounts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label2)
					.addGap(!isOpenAccount ? 70 : 2)
					.addComponent(amountField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelbtn)
						.addComponent(confirmbtn))
					.addGap(60))
		);
		modalPanel.setLayout(gl_panel);
        
        
        
        add(modalPanel);
        //And when you're done, of course you'll pack it.
        pack();
  
    }


    private class Controller implements ActionListener
    {
    	
    	
    	
        @Override
        public void actionPerformed(ActionEvent ae)
        {
        	
            String s = ae.getActionCommand();
           
            
            //User selects "Connect":  I set "C" to be my Connect button's "action command".
            if ("C".equals(s)) //Connect option.
            {
                  
           
                
               
            }
            //I set "X" to be my Cancel button's "action command":
            else
            {
            	 setVisible(false);
                
            }


           
            
        }
    
    }


	/**
	 * @return the accounts
	 */
	public static JComboBox<String> getAccounts() {
		return accounts;
	}


	/**
	 * @return the amountField
	 */
	public static JTextField getAmountField() {
		return amountField;
	}


	/**
	 * @return the cancelbtn
	 */
	public static JButton getCancelbtn() {
		return cancelbtn;
	}


	/**
	 * @return the confirmbtn
	 */
	public static JButton getConfirmbtn() {
		return confirmbtn;
	}
}
