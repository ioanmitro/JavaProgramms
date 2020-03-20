public class CreditCustomer extends Customer{
	CreditCard card;
	public CreditCustomer(CustomerInfo info, BankAccount account, CreditCard card){
		super(info, account);
		this.card = card;
	}
	public CreditCard getCreditCard(){
		return card;
	}
	public void setCreditCard(CreditCard card){
		this.card = card;
	}
	public String toString(){
		String str = super.toString() + '\n' + card.toString();
		return str;
	}	
}
	
