package mainClasses;

import mainClasses.*;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseInvoice implements Calculations {
public void setFinalprice(double finalprice) {
		this.finalprice = finalprice;
	}



	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}



private Store store ;
private Employee manager;
private Supplier supplier;
private ArrayList<Item> orderItems=new ArrayList<Item>();
private ArrayList<Integer> orderQuantities=new ArrayList<Integer>();
private static final double vat=0.11;
private static int PurchaseNb=0;
private double finalprice=0,totalprice=0;
private LocalDateTime DeliveryTime;

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
NumberFormat usd=NumberFormat.getCurrencyInstance();
NumberFormat percent=NumberFormat.getPercentInstance();




public PurchaseInvoice(Employee manager,Supplier supplier,Store store) {
	this.manager=manager;
	this.supplier=supplier;
	PurchaseNb++;
    this.DeliveryTime= LocalDateTime.now();
    this.store=store;
    
}



public Employee getManager(){
	return manager;
}


public ArrayList<Item> getOrderItems(){
	return orderItems;
}


public ArrayList<Integer> getOrderQuantities(){
	return orderQuantities;
}


public Supplier getSupplier() {
	return supplier;
}

	
	public void addNewRequest(Item orderItem,int orderQuantity) {
		this.orderItems.add(orderItem);
		this.orderQuantities.add(orderQuantity);
        this.store.setTotalExpenses(this.store.getTotalExpenses()+orderItem.getPrice()*orderQuantity);
        totalprice=totalprice+(orderItem.getPrice()*orderQuantity);
        finalprice=totalprice+totalprice*vat;
		
	}
	
	
	
	
	
	
	
	
	public String toString() {
	    String separator = "*************************************************************************\n";
	    String header = String.format("%s\n%s\n\n%3s%-30s%-30s%-30s\n",
	            DeliveryTime.format(formatter), PurchaseNb, "", "item(s)", "quantity", "value");

	    StringBuilder items = new StringBuilder();
	    for (int i = 0; i < orderItems.size(); i++) {
	        items.append(String.format("%3s%-30s%-30d%-30s\n",
	                (i + 1) + "-", orderItems.get(i).getName(),
	                orderQuantities.get(i),
	                usd.format(orderItems.get(i).getPrice() * orderQuantities.get(i))));
	    }

	    String total = String.format("\ntotal\t\t%s\nfinalprice(tax/discount included):%10s\nvat taken:%10s\n",
	            usd.format(totalprice), usd.format(finalprice), percent.format(vat));

	    String footer = String.format("\nby supplier\t%-30s\tto manager\t%-30s\n%s",
	            supplier.getName(), manager.getFname(), separator);

	    return separator + header + items.toString() + total + footer;
	}


	






	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}



	public double getFinalprice() {
		finalprice=totalprice+totalprice*vat;
		return finalprice;
	}



	public double getTotalprice() {
		return totalprice;
	}



	public LocalDateTime getDeliveryTime() {
		return DeliveryTime;
	}



	
	
	
	
	
}