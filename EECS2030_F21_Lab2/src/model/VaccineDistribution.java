package model;

public class VaccineDistribution {

	private Vaccine vaccine;
	private int amount;
	private String status;
	
	public VaccineDistribution(Vaccine vaccine, int amount) {
		this.vaccine = vaccine;
		this.amount = amount;
		this.status = this.amount + " doses of " + this.vaccine.getCodename() + " by " + this.vaccine.getManufacturer();
	}
	
	public String toString() {
		return this.status;
	}
}
