package model;

public class Assessment {

	private String name;
	private double weight;
	private int marks = 0;
	private String status;
	
	public Assessment(String name, double weight) {
		this.name = name;
		this.weight = weight * 100;
		this.status = "Assessment created: " + this.name + " accounts for " + String.format("%.3f", this.weight) + " percents of the course.";
	}

	public String toString() {
		return this.status;
	}

	public void setMarks(int i) {
		int temp = this.marks;
		this.marks = i;
		this.status = "Marks of assessment " + this.name + " (accounting for " 
						+ String.format("%.3f", this.weight) + " percents of the course) is changed from " + temp + " to " + this.marks + ".";
	}

	public void setWeight(double d) {
		double temp = this.weight;
		this.weight = d * 100;
		this.status = "Weight of assessment " + this.name + " (with marks " + this.marks
						+ ") is changed from " + String.format("%.3f", temp) + " percents to " 
						+ String.format("%.3f", this.weight) + " percents.";
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public int getMarks() {
		return marks;
	}
}
