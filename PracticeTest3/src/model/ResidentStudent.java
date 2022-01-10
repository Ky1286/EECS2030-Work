package model;

public class ResidentStudent extends Student{

	private String name;
	private double rate;
	
	public ResidentStudent(String name) {
		this.name = name;
	}

	public void setPremiumRate(double d) {
		this.rate = d;
		
	}

	public double getPremiumRate() {
		// TODO Auto-generated method stub
		return this.rate;
	}

}
