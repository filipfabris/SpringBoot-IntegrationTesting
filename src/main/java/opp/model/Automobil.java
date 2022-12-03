package opp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Automobil {
	
    @Id
    @GeneratedValue
	Long id;
    
    String manufacturer;
    
    String model;
    
    Long serialNumber;

    
	public Automobil(Long id, String manufacturer, String model, Long serialNumber) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.serialNumber = serialNumber;
	}

	public Automobil(String manufacturer, String model, Long serialNumber) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.serialNumber = serialNumber;
	}

	public Automobil() {
		super();
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
