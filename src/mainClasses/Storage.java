package mainClasses;

import java.util.ArrayList;

public class Storage {

	private int maxCapacity,currentCapacity;
	private double rentalFees;
	private ArrayList<Item> itemsInStorage=new ArrayList<Item>();
	private ArrayList<Integer> itemsQuantities=new ArrayList<Integer>();
	
	
	public Storage(int maxCapacity, int rentalFees, ArrayList<Item> itemsInStorage, ArrayList<Integer> itemsQuantities){
			this.maxCapacity=maxCapacity;
			this.rentalFees=rentalFees;
			this.itemsInStorage=itemsInStorage;
			this.itemsQuantities=itemsQuantities;
		
	}


	public int getMaxCapacity() {
		return maxCapacity;
	}


	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}


	public int getCurrentCapacity() {
		return currentCapacity;
	}


	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}


	public double getRentalFees() {
		return rentalFees;
	}


	public void setRentalFees(double rentalFees) {
		this.rentalFees = rentalFees;
	}


	public ArrayList<Item> getItemsInStorage() {
		return itemsInStorage;
	}


	public void setItemsInStorage(ArrayList<Item> itemsInStorage) {
		this.itemsInStorage = itemsInStorage;
	}


	public ArrayList<Integer> getItemsQuantities() {
		return itemsQuantities;
	}


	public void setItemsQuantities(ArrayList<Integer> itemsQuantities) {
		this.itemsQuantities = itemsQuantities;
	}
	

	public void increaseItemQuantity(Item item,int quantityAdded) {
		
		int k=itemsInStorage.indexOf(item);
		itemsQuantities.add(k, itemsQuantities.get(k)+quantityAdded);
		
		
	}
	
	
	
	public void reduceItemQuantity(Item item,int quantityRemoved) {
		
		int k=itemsInStorage.indexOf(item);
		itemsQuantities.add(k, itemsQuantities.get(k)-quantityRemoved);
		
	}
	
	
	
	
	
}