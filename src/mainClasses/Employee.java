package mainClasses;

import mainClasses.Address;
import mainClasses.Person;

import java.util.Random;
import java.text.NumberFormat;


public class Employee extends Person {
	
	
private String id,post,password,email;
double salary;
private Address address;
private String timeshift;

public void setAddress(Address address) {
	this.address = address;
}


Random gen=new Random();
NumberFormat fmt1=NumberFormat.getCurrencyInstance();


public Employee(String fname,String lname,double salary,String post,Address address,String email,String phone) {//fname,lname more the 2 char,salary btwn 500 2500

	
	super(fname,lname,phone);
	this.post=post;//manager,cashier,owner
	this.salary=salary;
	this.email=email;
	this.address=address;
	
	
	id=fname.substring(0, 2)+"_"+lname.substring(0, 2)+"_"+address.getZipcode();
	password="0";
	
}


public Employee(String fname,String lname,double salary,String post,Address address,String email,String phone,String timeshift) {
	super(fname,lname,phone);
	this.post=post;//manager,cashier,owner
	this.salary=salary;
	this.email=email;
	this.address=address;
	this.password=fname.substring(0,2)+lname.substring(0,2);
	this.timeshift=timeshift;
	id=fname.substring(0, 2)+"_"+lname.substring(0, 2)+"_"+address.getZipcode();
	password="0";
	
}
public String getPost() {
	return post;
}

public Address getAddress() {
	return address;
}

public double getSalary() {
	return salary;
}

public String getEmail() {
	return email;
}

public String getId() {
	return id;
}


public void setPost(String post) {
	this.post = post;
}

public void setEmail(String email) {
	this.email = email;
}
public void setSalary(double salary) {
	this.salary = salary;
}

public void setPassword(String password) {
	this.password=password;
}
public String getPassword() {
	return password;
}
	
	
	public String toString() {
		return post+":"+super.getFname()+" "+super.getLname()+" has id "+id+" and salary of "+fmt1.format(salary)+" address:"+address
		+ " email:"+email+" phone:"+super.getPhone()+" account password:"+password+"\n";
	}


	public String getTimeshift() {
		return timeshift;
	}


	public void setTimeShift(String newValue) {
		// TODO Auto-generated method stub
		this.timeshift=newValue;
	}
	
	
	
	








	
	
	
	
	
	
	
	
}