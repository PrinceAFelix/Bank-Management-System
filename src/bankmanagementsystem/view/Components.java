package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bankmanagementsystem.controller.BankController.MouseClickListener;

public class Components {
	
	public Components() {
		
	}
	
	public JTextField setTextFieldFocusListener(JTextField textfield, String placeholder) {
		
		textfield.setBackground(new Color(246, 246, 246));
		textfield.setColumns(10);
		textfield.setBorder(BorderFactory.createCompoundBorder(
				textfield.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textfield.setText(placeholder);
		textfield.setForeground(Color.GRAY);
		

		
		textfield.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (textfield.getText().equals(placeholder)) {
		        	textfield.setText("");
		        	textfield.setForeground(Color.BLACK);
		        }
		        
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (textfield.getText().isEmpty()) {
		        	textfield.setForeground(Color.GRAY);
		        	textfield.setText(placeholder);
		        }
		    }
		    });
		
		return textfield;
	}
	
	
	public JPasswordField setPasswordFieldFocusListener(JPasswordField passfield, String placeholder) {
		
		passfield.setBackground(new Color(246, 246, 246));
		passfield.setMargin(new Insets(10, 10, 10, 10));
		passfield.setBorder(BorderFactory.createCompoundBorder(
				passfield.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		passfield.setText(placeholder);
		passfield.setForeground(Color.GRAY);
		passfield.setEchoChar((char) 0);
		passfield.addFocusListener(new FocusListener() {
			
			@Override
		    public void focusGained(FocusEvent e) {
		        if (String.valueOf(passfield.getPassword()).equals(placeholder)) {
		        	passfield.setText("");
		        	passfield.setForeground(Color.BLACK);
		        	
		        }
		        passfield.setEchoChar('\u2022');
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (passfield.getPassword().length == 0) {
		        	passfield.setForeground(Color.GRAY);
		        	passfield.setText(placeholder);
		        	passfield.setEchoChar((char) 0);
		        }
		    }

		    });
		
		return passfield;
	}
	
	
	public JPanel setFooter(MouseClickListener mousecontroller, JLabel textBtn) {
		JPanel footer = new JPanel();
		footer.setBackground(new Color(57, 118, 174));
		footer.setPreferredSize(new Dimension(350, 30));
		
		footer.setLayout(new BorderLayout(0, 0));
		
		
		textBtn.setBackground(new Color(255, 255, 255));
		textBtn.setForeground(new Color(142, 184, 230));
		textBtn.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		textBtn.setHorizontalAlignment(SwingConstants.CENTER);
		
		footer.add(textBtn, BorderLayout.CENTER);
		
		textBtn.addMouseListener(mousecontroller);
		
		return footer;

	}

}
