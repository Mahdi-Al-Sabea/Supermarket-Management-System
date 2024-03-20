package mainClasses;

public class Address {
 private String city,building;
 private String state , country ,street;
 private int zipcode;
 
 public Address(String country,String state, String city ,String street,
		 String building ,int zipcode) {
	 this.country=country;
	 this.street=street;
	 this.building=building;
	 this.city=city;
	 this.zipcode=zipcode;
	 this.state=state;
 }
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getBuilding() {
	return building;
}
public void setBuilding(String building) {
	this.building = building;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public int getZipcode() {
	return zipcode;
}
public void setZipcode(int zipcode) {
	this.zipcode = zipcode;
}

}
