package bankmanagementsystem;


import java.awt.Dimension;
import java.awt.EventQueue;


import javax.swing.JFrame;


import bankmanagementsystem.view.BankView;



public class BankMain {
	public static void main(String[] args) {
		int duration = 500;
		if(args.length == 1){
	    	try{
	    	 duration = Integer.parseInt(args[0]);
	    	}catch (NumberFormatException mfe){
	    	  System.out.println("Wrong command line argument: must be an integer number");
	    	  System.out.println("The default duration 10000 milliseconds will be used");
	    	  //mfe.printStackTrace();	
	    	} 
	    }
		
		
		
		EventQueue.invokeLater(new Runnable(){
		       @Override
		       public void run(){ 	
		        JFrame frame = new BankView();
		        frame.setTitle("Bank System");
		        frame.setMinimumSize(new Dimension(350, 650));
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLocationRelativeTo(null);//screen center
		        frame.setLocationByPlatform(true);
		        frame.setVisible(true);  
		        frame.setResizable(false);
		       
		        
		       }
		});
	}
}
