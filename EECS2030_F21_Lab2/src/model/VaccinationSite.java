package model;

public class VaccinationSite {

	private String name;
	private int limit;
	private int numOfDoses = 0;
	private Vaccine vaccines[] = new Vaccine[0];
	private int doseCount[] = new int[0];
	private HealthRecord[] booking = new HealthRecord[0];
	
	public VaccinationSite(String name, int limit) {
		this.name = name;
		this.limit = limit;
	}

	public int getNumberOfAvailableDoses() {
		
		return this.numOfDoses;
	}

	public String toString() {
		String result = "";
		
		if (this.vaccines.length == 0) {
			result = this.name + " has " + this.numOfDoses + " available doses: <>";
		} else {
			result += this.name + " has " + this.numOfDoses + " available doses: <";
			for (int i = 0; i < this.vaccines.length; i++) {
				result += this.doseCount[i] + " doses of " + this.vaccines[i].getManufacturer();
				if (i < this.vaccines.length - 1) {
					result += ", ";
				}
			}
			result += ">";
		}
		
		return result;
	}

	public int getNumberOfAvailableDoses(String name) {
		int result = 0;
		
		for (int i = 0; i < this.vaccines.length; i++) {
			if (this.vaccines[i].getCodename() == name) {
				result = this.doseCount[i];
			}
		}
		
		return result;
	}

	public void addDistribution(Vaccine vaccine, int doses) throws TooMuchDistributionException, UnrecognizedVaccineCodeNameException {
		if (vaccine.isValid() == false) {
			throw new UnrecognizedVaccineCodeNameException ("Unrecognized");
		} else if (this.numOfDoses + doses > this.limit) {
			throw new TooMuchDistributionException ("Over-limit");
		}
		this.numOfDoses += doses;
		boolean check = true;
		for (int i = 0; i < this.vaccines.length; i++) {
			if (this.vaccines[i].getManufacturer() == vaccine.getManufacturer()) {
				this.doseCount[i] += doses;
				check = false;
			}
		}
		if (check == true) {
			Vaccine[] tempVaccine = new Vaccine[this.vaccines.length + 1];
			int[] tempDoses = new int[this.doseCount.length + 1];
			for (int i = 0; i < this.vaccines.length; i++) {
				tempVaccine[i] = this.vaccines[i];
				tempDoses[i] = this.doseCount[i];
			}
			tempVaccine[tempVaccine.length - 1] = vaccine;
			tempDoses[tempDoses.length - 1] = doses;
			
			this.vaccines = tempVaccine;
			this.doseCount = tempDoses;
		}
	}

	public void bookAppointment(HealthRecord name) throws InsufficientVaccineDosesException {
		if (this.booking.length == this.numOfDoses) {
			String[] holder = name.getAppointments();
			String[] tempLocation = new String[holder.length + 1]; 
			for (int i = 0; i < holder.length; i++) {
				tempLocation[i] = holder[i];
			}
			tempLocation[tempLocation.length - 1] = this.name;
			name.setAppointments(tempLocation);
			name.setChecked("failed");
			throw new InsufficientVaccineDosesException("Insufficient Vaccine Doses");
		}
		String[] holder = name.getAppointments();
		String[] tempLocation = new String[holder.length + 1];
		HealthRecord[] tempBooking = new HealthRecord[this.booking.length + 1];
		for (int i = 0; i < holder.length; i++) {
			tempLocation[i] = holder[i];
		}
		for (int i = 0; i < this.booking.length; i++) {
			tempBooking[i] = this.booking[i];	
		}
		tempLocation[tempLocation.length - 1] = this.name;
		tempBooking[tempBooking.length - 1] = name;
		name.setAppointments(tempLocation);
		this.booking = tempBooking;
		name.setChecked("succeeded");
	}

	public void administer(String date) {
		int count = 0;
		this.numOfDoses -= this.booking.length;
		for (int i = 0; i < this.doseCount.length; i++) {
			if (this.doseCount[i] == 0) {
				count++;
			}
		}
		for (int i = 0; i < this.booking.length; i++) {
			if (this.doseCount[count] > 0) {
				this.booking[i].addRecord(this.vaccines[count], this.name, date);
				this.doseCount[count]--;
				if (this.doseCount[count] == 0) {
					count++;
				}
			}
		}
		HealthRecord[] temp = new HealthRecord[0];
		this.booking = temp; //resets the bookings
	}
}
