package mainClasses;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Delivery {
	private Address AddressFrom;
	private Address AddressTo;
	private Employee Deliveryman;
	private LocalDateTime DeliveryTime;
	private String Transportaion;

	public Delivery(Address AddressFrom, Address AddressTo, Employee Deliveryman, String Transportaion) {
		this.AddressFrom = AddressFrom;
		if (!AddressTo.equals(AddressFrom)) {
			this.AddressTo = AddressTo;
		}
		this.Deliveryman = Deliveryman;
		this.Transportaion = Transportaion;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DeliveryTime = LocalDateTime.now();
		DeliveryTime.format(formatter);

	}

	public Address getAddressFrom() {
		return AddressFrom;
	}

	public void setAddressFrom(Address addressFrom) {
		AddressFrom = addressFrom;
	}

	public Address getAddressTo() {
		return AddressTo;
	}

	public void setAddressTo(Address addressTo) {
		AddressTo = addressTo;
	}

	public Employee getDeliveryman() {
		return Deliveryman;
	}

	public void setDeliveryman(Employee deliveryman) {
		Deliveryman = deliveryman;
	}

	public String getTransportaion() {
		return Transportaion;
	}

	public void setTransportaion(String transportaion) {
		Transportaion = transportaion;
	}

	public LocalDateTime getDeliveryTime() {
		return DeliveryTime;
	}
	

}