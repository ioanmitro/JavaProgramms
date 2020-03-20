public class Customer{
	private BankAccount account;
	private CustomerInfo info;
	
	public Customer(CustomerInfo info, BankAccount account){
		this.info = info;
		this.account = account;
	}
	public Customer(String name, String address, String phoneNumber, long accountID, double initBalance){
		account = new BankAccount(accountID, initBalance); 
		info = new CustomerInfo(name, address, phoneNumber);
	}	
	public CustomerInfo getCustomerInfo(){
		return info;
	}
	public BankAccount getBankAccount(){
		return account;
	}
	public String toString(){
		return account.toString() + info.toString();
	}
} 
