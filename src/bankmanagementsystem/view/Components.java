package bankmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import bankmanagementsystem.controller.BankController;
import bankmanagementsystem.controller.BankController.MouseClickListener;
import bankmanagementsystem.model.User;

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
	
	
	public JPanel showResults(User user, boolean isRegister, String[] deletedUser) {
		JPanel panel = new JPanel();
	
		panel.setBackground(Color.WHITE);
		

		
		JLabel heading = new JLabel(isRegister ? "Registered" : "<html><body>" +
				"<div style='text-align: center;'>"+
	            "<span style='font-size: 14px; color: Black; '>Successfully Removed</span>" +
	            "<br>" +
	            "<span style='font-size: 12px; color: gray;'>" + deletedUser[1] + "</span>" +
	            "</div>" +
	            "</body></html>");
		
	

		
		JLabel idLabel = new JLabel("User's ID");
		idLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		JLabel idValue = new JLabel(isRegister ? user.getId() : deletedUser[0]);
		idValue.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		if(isRegister) {
			
			heading.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		}
		
		GroupLayout gl_panel = new GroupLayout(panel);
		
		GroupLayout.Group hGroup = gl_panel.createParallelGroup();
		GroupLayout.Group vGroup = gl_panel.createParallelGroup();
		
		hGroup.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(isRegister ? 101 : 75)
						.addComponent(heading))
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(138)
						.addComponent(idLabel))
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(155)
						.addComponent(idValue)));
		
		
		vGroup.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(171)
						.addComponent(heading)
						.addGap(35)
						.addComponent(idLabel)
						.addGap(5)
						.addComponent(idValue)));
		
		if(isRegister) {
			
			JLabel cardNumLabel = new JLabel("Card Number");
			cardNumLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));

			
			JLabel cardNumValue = new JLabel(String.valueOf(user.getCardNumber()).replaceAll("(.{4})", "$1 "));
			cardNumValue.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			
			hGroup.addGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
							.addGap(120)
							.addComponent(cardNumLabel))
					.addGroup(gl_panel.createSequentialGroup()
							.addGap(95)
							.addComponent(cardNumValue))
					);
			
			vGroup.addGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
							.addGap(295)
							.addComponent(cardNumLabel)
							.addGap(5)
							.addComponent(cardNumValue))
					);
			
			
		}
		
		gl_panel.setHorizontalGroup(hGroup);
		gl_panel.setVerticalGroup(vGroup);
		
		panel.setLayout(gl_panel);
		
	
		
		return panel;
	}
	
	
	
	

}
