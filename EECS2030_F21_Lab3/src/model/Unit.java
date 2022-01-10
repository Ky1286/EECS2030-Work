package model;

public class Unit {

	private String name;
	private int wideInches;
	private int longInches;
	
	private double wideMeter;
	private double longMeter;
	
	private boolean isFeet = false;
	private boolean checked = false;
	
	private String status;
	
	public Unit(String name, int wideInches, int longInches) {
		this.name = name;
		this.wideInches = wideInches;
		this.longInches = longInches;
		
		this.wideMeter = wideInches * 0.3048;
		this.longMeter = longInches * 0.3048;
		
		this.status = "A unit of " + (this.longInches * this.wideInches) 
					+ " square feet (" + this.wideInches + "' wide and " + this.longInches 
					+ "' long) functioning as " + this.name;
	}

	public String toString() {
		return this.status;
	}

	public void toogleMeasurement() {
		if (this.isFeet == false) {
			this.isFeet = true;
			this.status = "A unit of " + String.format("%.2f", this.longMeter * this.wideMeter) 
					+ " square meters (" + String.format("%.2f",this.wideMeter) + " m wide and " 
					+ String.format("%.2f",this.longMeter) + " m long) functioning as " + this.name;
		} else if (this.isFeet == true) {
			this.isFeet = false;
			this.status = "A unit of " + (this.longInches * this.wideInches) 
					+ " square feet (" + this.wideInches + "' wide and " + this.longInches 
					+ "' long) functioning as " + this.name;
		}
	}
	
	public int getArea() {
		return this.longInches * this.wideInches;
	}
	
	public String getName() {
		return name;
	}

	public int getWideInches() {
		return wideInches;
	}

	public int getLongInches() {
		return longInches;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean equals(Object x) {
		if (this == x) {
			return true;
		}
		if (x == null) {
			return false;
		}
		Unit temp = (Unit) x;
		return
				this.name == temp.name
			 && this.getArea() == temp.getArea();
	}
}
