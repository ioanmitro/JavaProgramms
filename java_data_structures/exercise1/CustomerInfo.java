public class CustomerInfo{
	String name;
	String address;
	String phoneNumber;
	public CustomerInfo(String name, String address, String phoneNumber){
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public String phoneNumber(){
		return phoneNumber;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setphoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public String toString(){
		String str = "name: " + name + ", address:" + address + ", phoneNumber: " + phoneNumber;
		return str;
	}
}
