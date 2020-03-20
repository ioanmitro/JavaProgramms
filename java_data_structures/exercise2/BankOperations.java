public class BankOperations{
	public static void main (String[] args){
		CustomerInfo custInfoOne = new CustomerInfo("Peter Smith", "Wikiland 01", "666666");
		CustomerInfo custInfoTwo = new CustomerInfo("Katherine Jones", "Wikiland 02", "777777");
		BankAccount accOne = new BankAccount(1, 200);
		BankAccount accTwo = new BankAccount(2, 190);
		Customer mrSmith = new Customer(custInfoOne, accOne);
		Customer mrsJones = new Customer(custInfoTwo, accTwo);
		System.out.println(mrSmith.toString());
		System.out.println(mrsJones.toString());
		accOne.modifyBalanceByAmount(10);
		accTwo.modifyBalanceByAmount(-10);
		System.out.println(mrSmith.toString());
		System.out.println(mrsJones.toString());

	}
} 
