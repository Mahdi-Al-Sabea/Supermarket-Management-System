package mainClasses;

import java.util.ArrayList;
public class Supplier {
	private String name;
	private Address address;
	private ArrayList<Item> items=new ArrayList<Item>();
	
	public Supplier(String name,Address address,ArrayList<Item> items) {//unlimited quantity,manager buys from,items are read from a file
		
		
		this.name=name;
		this.address=address;
		
		
		
	}


	public String getName() {
		return name;
	}


	public void setName(String namel) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public ArrayList<Item> getItems() {
		return items;
	}


	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	
	
	public String toString() {
		
		
		String itemsIN="items:\n";
		for(int i=0;i<items.size();i++) {
			itemsIN+=(i+1)+")"+items.get(i);
		}
		
		
		
		
		return "supplier name"+name+"\n"
				+"supplier address:"+address+"\n"
				+itemsIN;
		
		
		
	}
}