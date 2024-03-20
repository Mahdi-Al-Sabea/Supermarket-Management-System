package mainClasses;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Calculations {
	private Employee orderCashier;
    private Client costumer;
    private ArrayList<Item> orderItems=new ArrayList<Item>();
    private ArrayList<Integer> orderQuantities=new ArrayList<Integer>();
    private static final double vat=0.11;
    private static int orderNb=0;
    private double finalprice=0,totalprice=0,discount;
    private LocalDate date;
    private Store store;
    


	private Delivery delivery;

    NumberFormat usd=NumberFormat.getCurrencyInstance();
    NumberFormat percent=NumberFormat.getPercentInstance();



    public Order(Employee orderCashier,Client costumer,Store store) {
        this.orderCashier=orderCashier;
        this.costumer=costumer;
        this.store=store;
        this.delivery=null;
        this.date=LocalDate.now();
    }


    public Order(Employee orderCashier,Client costumer,Store store,Delivery delivery) {
        this.orderCashier=orderCashier;
        this.costumer=costumer;
        this.date=LocalDate.now();
        this.store=store;
        this.delivery=delivery;
    }

    public Employee getOrderCashier() {
        return orderCashier;
    }



    public ArrayList<Item> getOrderItems(){
        return orderItems;
    }


    public ArrayList<Integer> getOrderQuantities(){
        return orderQuantities;
    }



    public void addNewRequest(Item orderItem,int orderQuantity) {
        orderItems.add(orderItem);
        orderQuantities.add(orderQuantity);
        this.store.setTotalSales(this.store.getTotalSales()+orderItem.getPrice()*orderQuantity);
        totalprice=totalprice+(orderItem.getPrice()*orderQuantity);
        if(costumer.getNbPr()>=3) {
            discount=0.10;
        }
        else {
            discount=0;}

        finalprice=(totalprice+(totalprice*vat))-(totalprice+(totalprice*vat))*discount;
        for (int i = 0; i < orderItems.size(); i++) {
            Item orderedItem = orderItems.get(i);
            Integer orderedQuantity = orderQuantities.get(i);

            for (int j = 0; j < store.getStorage().getItemsInStorage().size(); j++) {
                Item itemInStore = store.getStorage().getItemsInStorage().get(j);

                if (orderedItem.getName().equals(itemInStore.getName())) {
                    Integer currentQuantity = store.getStorage().getItemsQuantities().get(j);
                    currentQuantity -= orderedQuantity;

                    // Ensure the quantity doesn't go negative (if needed)
                    if (currentQuantity >= 0) {
                        store.getStorage().getItemsQuantities().set(j, currentQuantity);
                    } else {
                        // Handle insufficient quantity scenario here
                        // You may want to throw an error or take appropriate action
                        // For example, setting the quantity to zero
                        store.getStorage().getItemsQuantities().set(j, 0);
                        System.err.println("Insufficient quantity for: " + itemInStore.getName());
                    }
                }
            }
        }
                orderNb++;
                costumer.IncreaseNbPr();
            }

            

    public String toString() {

        String del="";

        if(delivery!=null){
            del="delivered from:"+ delivery.getAddressFrom()+" to:"+delivery.getAddressTo()+"\n";
        }

                                            //notice we have not change Date from string
        String start="*************************\n"+date+"\n"+orderNb+"\n\n"
                + "item(s)\t\t\t\t\t\t quantity\t\t\t\t\t\tvalue\n";
        for(int i=0;i<orderItems.size();i++) {
            start+=orderItems.get(i).getName()+"\t\t\t\t\t\t "+orderQuantities.get(i)+"\t\t\t\t\t\t\t"+usd.format(orderItems.get(i).getPrice()*orderQuantities.get(i))+"\n";
        }

        start=start+"\ntotal\t\t\t\t\t"+usd.format(totalprice)+"\ndiscount:\t\t\t\t"+percent.format(discount)+"\nfinalprice(tax/discount included):"
                + "\t"+usd.format(finalprice)+"\nvat taken:"+percent.format(vat);

        start+="\nby "+orderCashier.getFname()+" "+orderCashier.getLname()+" to client "+costumer.getFname()+" "+costumer.getLname()+"\n"
               +del+ "******************************\n";

        return start;
    }


	public Client getCostumer() {
		return costumer;
	}


	
	public Delivery getDelivery() {
		return delivery;
	}


	public LocalDate getDate() {
		return date;
	}


	public double getFinalprice() {
		return finalprice;
	}


	public void setFinalprice(double finalprice) {
		this.finalprice = finalprice;
	}


	public double getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}












}