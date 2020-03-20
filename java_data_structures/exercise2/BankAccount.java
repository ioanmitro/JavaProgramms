public class BankAccount{
	private long id;
	private double balance;
	public BankAccount(long accountID){
		this(accountID, 0);
	}
	public BankAccount(long accountID, double initBalance){
		id = accountID;
		balance = initBalance;
	}
	public double getBalance(){
		return balance;
	}
	public long getID(){
		return id;
	}
	public void modifyBalanceByAmount(double amount){
		balance = balance + amount;
	}
	public String toString() {
		String str = "id: " + id + ", balance: " + balance;
		return str;
	} 
}
