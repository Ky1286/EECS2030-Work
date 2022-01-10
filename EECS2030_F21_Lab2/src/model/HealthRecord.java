package model;

public class HealthRecord {

	private String name;
	private int limit;
	private Vaccine[] vaccines = new Vaccine[0];
	private String[] locations = new String[0];
	private String[] date = new String[0];
	private String[] appointments = new String[0];
	private String checked;	
	public HealthRecord(String name, int limit) {
		this.name = name;
		this.limit = limit;
	}

	public String getVaccinationReceipt() {
		String result = "";
		if (this.vaccines.length == 0) {
			result = this.name + " has not yet received any doses.";
		} else {
			result += "Number of doses " + this.name + " has received: " + this.vaccines.length + " [";
			for (int i = 0; i < this.vaccines.length; i++) {
				result += this.vaccines[i].toString() + " in " + this.locations[i] + " on " + this.date[i];
				if (i < this.vaccines.length - 1) {
					result += "; ";
				}
			}
			result += "]";
		}
		return result;
	}

	public String getAppointmentStatus() {
		String result = "";
		if (this.appointments.length == 0) {
			result = "No vaccination appointment for " + this.name + " yet";
		} else {
			result = "Last vaccination appointment for " + this.name + " with " + this.appointments[appointments.length - 1] + " " + this.checked;
		}
		return result;
	}

	public void addRecord(Vaccine vaccine, String location, String date) {
		Vaccine[] temp1 = new Vaccine[this.vaccines.length + 1];
		String[] temp2 = new String[this.locations.length + 1];
 		String[] temp3 = new String[this.date.length + 1];
		for (int i = 0; i < this.vaccines.length; i++) {
			temp1[i] = this.vaccines[i];
		}
		temp1[temp1.length - 1] = vaccine;
		for (int i = 0; i < this.locations.length; i++) {
			temp2[i] = this.locations[i];
		}
		temp2[temp2.length - 1] = location;
		for (int i = 0; i < this.date.length; i++) {
			temp3[i] = this.date[i];
		}
		temp3[temp3.length - 1] = date;
		this.vaccines = temp1;
		this.locations = temp2;
		this.date = temp3;
	}

	public String[] getAppointments() {
		return appointments;
	}

	public void setAppointments(String[] appointments) {
		this.appointments = appointments;
	}
	
	public void setChecked(String checked) {
		this.checked = checked;
	}

}
