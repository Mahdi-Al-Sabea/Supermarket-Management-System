package mainClasses;

import java.util.ArrayList;

/*public class Store {
static  double sales=0;
static double expenses=0;
private String name,description,password,phone;
Address address;
private ArrayList<Employee> employees=new ArrayList<Employee>();
private ArrayList<Item> items=new ArrayList<Item>();
private ArrayList<Client>clients=new ArrayList<Client>();
private ArrayList<Order>orders=new ArrayList<Order>();
private ArrayList<PurchaseInvoice>purchases=new ArrayList<PurchaseInvoice>();
private Storage storage;


public static double getSales() {
	return sales;
}

public void setEmployees(ArrayList<Employee> employees) {
	this.employees = employees;
}

public void setItems(ArrayList<Item> items) {
	this.items = items;
}

public void setClients(ArrayList<Client> clients) {
	this.clients = clients;
}

public void setOrders(ArrayList<Order> orders) {
	this.orders = orders;
}

public void setPurchases(ArrayList<PurchaseInvoice> purchases) {
	this.purchases = purchases;
}

public ArrayList<PurchaseInvoice> getPurchases() {
	return purchases;
}

public Storage getStorage() {
	return storage;
}

public Store(String name,Address address,String description,String phone,String password) {
	this.phone=phone;
	this.name=name;
	this.address=address;
	this.description=description;
	this.password=password;
}

public Store() {
name="empty";
address=null;
description="empty";
phone="0";
password="0";
}





public ArrayList<Employee> getEmployees(){
	return employees;
}


public ArrayList<Item> getItems(){
	return items;
}

public ArrayList<Client> getClients(){
	return clients;
}


public ArrayList<Order> getOrders(){
	return orders;}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password=password;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}




public Employee searchCashiers(String fname,String lname) {
	for(int i=2;i<employees.size();i++) {
		if(fname.equalsIgnoreCase(employees.get(i).getFname()) && lname.equalsIgnoreCase(employees.get(i).getLname())) {
				return employees.get(i);
				}		
	}
	return null;
}


public Item searchItems(String name) {
	for(int i=0;i<items.size();i++) {
		   if(name.equalsIgnoreCase(items.get(i).getName())) {
			return items.get(i);   
		   }
		   
		   }
	return null;
}


public Client searchClient(String fname,String lname) {
	for(int i=0;i<clients.size();i++) {
		if(fname.equalsIgnoreCase(clients.get(i).getFname()) && lname.equalsIgnoreCase(clients.get(i).getLname())) {
				return clients.get(i);
				}		
	}
	return null;
}






	
	
	public String toString(){
		
		String emp;
	
		if(employees.size()==0) {
			
			return "Company name:"+name+"\n"+
					"address:"+address+"\n"+
					"descripton:"+description+"\n"+
					"phone number:"+phone+"\n"+"no employees yet";
		}
		else
		
			emp="employees:\n";
		for(int i=0;i<employees.size();i++) {
			emp+=(i+1)+")"+employees.get(i);
		}
			
			
			
			
			
		return "Company name:"+name+"\n"+
				"address:"+address+"\n"+
				"descripton:"+description+"\n"+
				"phone number:"+phone+"\n"+emp;
					
		
	}

	public void setStorage(Storage myStorage) {
		// TODO Auto-generated method stub
		this.storage=myStorage;
	}
	
	
	
	
	
}
*/

public class Store {
    private String name,description,password,phone;
    Address address;
    private ArrayList<Employee> employees=new ArrayList<Employee>();
    private ArrayList<Item> items=new ArrayList<Item>();//should not be here because we already have storage
    private ArrayList<Client>clients=new ArrayList<Client>();
    private ArrayList<Order>orders=new ArrayList<Order>();
    private ArrayList<PurchaseInvoice>purchases=new ArrayList<PurchaseInvoice>();
    private ArrayList<Supplier>Suppliers=new ArrayList<Supplier>();



	private Storage storage;

    public ArrayList<PurchaseInvoice> getPurchases() {
		return purchases;
	}


	private double totalExpenses=0;

    private  double totalSales=0;


    public Store(String name,Address address,String description,String phone,String password) {
        this.phone=phone;
        this.name=name;
        this.address=address;
        this.description=description;
        this.password=password;
    }

    public Store() {
        name="empty";
        address=null;
        description="empty";
        phone="0";
        password="0";
    }





    public ArrayList<Employee> getEmployees(){
        return employees;
    }


    public ArrayList<Item> getItems(){
        return items;
    }

    public ArrayList<Client> getClients(){
        return clients;
    }


    public ArrayList<Order> getOrders(){
        return orders;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStorage(Storage storage){
        this.storage=storage;
    }

    public Storage getStorage(){return storage;}



    public Employee searchCashiers(String fname,String lname) {
        for(int i=2;i<employees.size();i++) {
            if(fname.equalsIgnoreCase(employees.get(i).getFname()) && lname.equalsIgnoreCase(employees.get(i).getLname())) {
                return employees.get(i);
            }
        }
        return null;
    }


    public Item searchItems(String name) {
        for(int i=0;i<items.size();i++) {
            if(name.equalsIgnoreCase(items.get(i).getName())) {
                return items.get(i);
            }

        }
        return null;
    }


    public Client searchClient(String fname,String lname) {
        for(int i=0;i<clients.size();i++) {
            if(fname.equalsIgnoreCase(clients.get(i).getFname()) && lname.equalsIgnoreCase(clients.get(i).getLname())) {
                return clients.get(i);
            }
        }
        return null;
    }



    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses){
        this.totalExpenses=totalExpenses;
    }


    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales){
        this.totalSales=totalSales;
    }

    void setOrders(ArrayList<Order> orders){
        this.orders=orders;
    }


    public String toString(){

        String emp;

        if(employees.size()==0) {

            return "Company name:"+name+"\n"+
                    "address:"+address+"\n"+
                    "descripton:"+description+"\n"+
                    "phone number:"+phone+"\n"+"no employees yet";
        }
        else

            emp="employees:\n";
        for(int i=0;i<employees.size();i++) {
            emp+=(i+1)+")"+employees.get(i);
        }





        return "Company name:"+name+"\n"+
                "address:"+address+"\n"+
                "descripton:"+description+"\n"+
                "phone number:"+phone+"\n"+emp;


    }

	public ArrayList<Supplier> getSuppliers() {
		return Suppliers;
	}

	public void setSuppliers(ArrayList<Supplier> suppliers) {
		Suppliers = suppliers;
	}





}