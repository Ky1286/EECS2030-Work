package model;

public class StudentRecord {

	private String courseName;
	private Assessment[] assessments = new Assessment[0];
	private double compRate = 0;
	private double rawMarks = 0;
	
	public StudentRecord(String courseName) {
		this.courseName = courseName;
	}

	public Object getCourse() {
		return this.courseName;
	}

	public String getAssessmentReport() {
		String result = "Number of assessments in " + this.courseName + ": " + this.assessments.length;
		if (this.assessments.length == 0) {
			result += " []";
		} else {
			result += " [";
			for (int i = 0; i < this.assessments.length; i++) {
				result += this.assessments[i].getName() + " (weight: " + String.format("%.3f", this.assessments[i].getWeight()) 
						+ " percents; marks: " + this.assessments[i].getMarks() + ")";
				if (i < this.assessments.length - 1) {
					result += ", ";
				}
			}
			result += "]";
		}
		return result;
	}

	public double getCompletionRate() {
		this.compRate = 0;
		if (this.assessments.length != 0) {
			for (int i = 0; i < this.assessments.length; i++) {
				this.compRate += this.assessments[i].getWeight() / 100;
			}
		}
		return this.compRate;
	}

	public double getRawMarks() {
		this.rawMarks = 0;
		if (this.assessments.length != 0) {
			for (int i = 0; i < this.assessments.length; i++) {
				this.rawMarks += (this.assessments[i].getWeight() / 100) * (this.assessments[i].getMarks());
			}
		}
		return this.rawMarks;
	}

	public void addAssessment(String name, double weight, int mark) {
		Assessment[] temp = new Assessment[this.assessments.length + 1];
		for (int i = 0; i < this.assessments.length; i++) {
			temp[i] = this.assessments[i];
		}
		temp[temp.length - 1] = new Assessment(name, weight);
		temp[temp.length - 1].setMarks(mark);
		this.assessments = temp;
	}

	public void addAssessment(Assessment m1) {
		Assessment[] temp = new Assessment[this.assessments.length + 1];
		for (int i = 0; i < this.assessments.length; i++) {
			temp[i] = this.assessments[i];
		}
		temp[temp.length - 1] = m1;
		this.assessments = temp;
	}

	public void changeMarksOf(String name, int mark) {
		for (int i = 0; i < this.assessments.length; i++) {
			if (this.assessments[i].getName().equals(name)) {
				this.assessments[i].setMarks(mark);
			}
		}
	}

	public void removeAssessment(String string) {
		int spot = 0;
		int count = 0;
		boolean check = false;
		for (int i = 0; i < this.assessments.length; i++) {
			if (this.assessments[i].getName().equals(string)) {
				spot = i;
				check = true;
			}
		}
		if (check == true) {
			Assessment[] temp = new Assessment[this.assessments.length - 1];
			for (int i = 0; i < this.assessments.length; i++) {
				if (i != spot) {
					temp[count] = this.assessments[i];
					count++;
				}
			}
			this.assessments = temp;
		}
	}

}
