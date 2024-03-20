package mainClasses;

public class Person {
	
private String fname;
private String lname;
private String phone;

public Person(String fname,String lname,String phone) {
	this.fname=fname;
	this.lname=lname;
	this.phone=phone;
}


public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

public void setPhone(String phone) {
	this.phone=phone;
}
	
public String getPhone() {
	return phone;
}
	
	
	
}
