public class CreditCard{
	private double balance;
	private long id;
	private double limit;
	public CreditCard(long creditCardID, double limit){
		this(creditCardID, limit, 0);
	}
	public CreditCard(long creditCardID, double limit, double initBalance){
		balance = initBalance;
		id = creditCardID;
		this.limit = limit;
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

	public boolean canCharge(double amount){
		if(amount <= limit){
			return true;
		}else{
			return false;
		}
	}
	public boolean chargeAmount(double amount){
		if(canCharge(amount) == false){
			return false;
		}
		balance = balance - amount;
		return true;
	}
	public String toString(){
		String str = "balance: " + balance + ", id: "+ id + ", limit: " + limit;
		return str;
	}
}

