package mainClasses;

import mainClasses.Address;
import mainClasses.Person;

public class Client extends Person {
private Address address;
private int NbPr=0;
public int getNbPr() {
	return NbPr;
}

public void IncreaseNbPr() {
	NbPr++;
}
public void setNbPr(int nbPr) {
	NbPr = nbPr;
}


public Client(String fname,String lname,String phone,Address address){
	super(fname,lname,phone);	
	this.address=address;
}


public String toString() {
	return "client:"+super.getFname()+" "+super.getLname()+" phone number:"+super.getPhone()+" number of purchases:"+getNbPr();
}

public Address getAddress() {
	return address;
}








	
}