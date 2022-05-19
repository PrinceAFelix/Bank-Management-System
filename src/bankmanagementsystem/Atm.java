package bankmanagementsystem;

public class Atm extends Bank {
	
	public String atmLocation;

	Atm(String atmNum, String c, String adr, String nm) {
		super(atmNum, c, adr, nm);
	}
	
	Atm(){
		this.atmLocation = super.address;
	}
	
	public void deposit() {
		
	}
	
	public void withdraw() {
		
	}
	
	public void checkBalance() {
		
	}
}
