package model;

public class Vaccine {

	private String codename;
	private String type;
	private String manufacturer;
	private String status;
	private boolean isValid = true;
	
	public Vaccine(String codename, String type, String manufacturer) {
		this.codename = codename;
		this.type = type;
		this.manufacturer = manufacturer;
		if (manufacturer == "Moderna" || manufacturer == "Pfizer/BioNTech" 
				|| manufacturer == "Janssen" || manufacturer == "Oxford/AstraZeneca") {
			this.status = "Recognized vaccine: " + this.codename + " (" + this.type + "; " + this.manufacturer + ")";
		} else {
			this.status = "Unrecognized vaccine: " + this.codename + " (" + this.type + "; " + this.manufacturer + ")";
			this.isValid = false;
		}
	}

	public String toString() {
		return this.status;
	}

	public String getCodename() {
		return codename;
	}

	public String getType() {
		return type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public boolean isValid() {
		return isValid;
	}
}
