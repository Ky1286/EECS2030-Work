package model;

public class Course {

	private String name;
	private double fee;
	
	public Course(String string, double d) {
		this.name = string;
		this.fee = d;
	}

	public double getFee() {
		// TODO Auto-generated method stub
		return this.fee;
	}

}
