package model;

public class App {

	private String name;
	private int maxNumofRatings;
	private String latestUpdate = "n/a";
	private Log[] updates = new Log[0];
	private int ratingCount = 0;
	
	private int one = 0;
	private int two = 0;
	private int three = 0;
	private int four = 0;
	private int five = 0;
	
	public App(String name, int maxNumofRatings) {
		this.name = name;
		this.maxNumofRatings = maxNumofRatings;
	}

	public String getName() {
		// Returns name of app
		return this.name;
	}

	public String getWhatIsNew() {
		String result = "";
		if (this.updates.length == 0) {
			result = "n/a";
		} else {
			result = this.updates[this.updates.length - 1].toString();
		}
		return result;
	}

	public Log[] getUpdateHistory() {
		// TODO Auto-generated method stub
		return this.updates;
	}

	public Log getVersionInfo(String version) {
		Log temp = null;
		for (int i = 0; i < this.updates.length; i++) {
			if (this.updates[i].getVersion().equals(version)) {
				temp = this.updates[i];
			}
		}
		return temp;
	}

	public String getRatingReport() {
		String result = "";
		
		if (ratingCount < 1) {
			result = "No ratings submitted so far!";
		} else {
			result = "Average of " + this.ratingCount + " ratings: " + String.format("%.1f", getAverage()) 
					+ " (Score 5: " + this.five 
					+ ", Score 4: " + this.four 
					+ ", Score 3: " + this.three
					+ ", Score 2: " + this.two
					+ ", Score 1: " + this.one + ")";
		}
		
		return result;
	}

	public String toString() {
		String result = "";
		if (ratingCount == 0) {
			result += this.name + " (Current Version: " + this.latestUpdate + "; Average Rating: n/a)";	
		} else {
			result += this.name + " (Current Version: Version " + this.latestUpdate 
					+ " contains " + this.updates.length + " fixes " 
					+ this.updates[this.updates.length - 1].getFixes() 
					+ "; Average Rating: " + String.format("%.1f", getAverage()) + ")";
		}
		return result;
	}

	public void releaseUpdate(String version) {
		this.latestUpdate = version;
		Log[] temp = new Log[this.updates.length + 1];
		for (int i = 0; i < this.updates.length; i++) {
			temp[i] = this.updates[i];
		}
		temp[temp.length - 1] = new Log(version);
		this.updates = temp;
	}

	public void submitRating(int i) {
		this.ratingCount++;
		switch (i) {
		case 1:
			one++;
			break;
		case 2:
			two++;
			break;
		case 3:
			three++;
			break;
		case 4:
			four++;
			break;
		case 5:
			five++;
			break;
		}
	}
	
	private double getAverage() {
		double avg = 0.0;
		
		avg = (((one * 1.0) + (two * 2.0) + (three * 3.0) + (four * 4.0) + (five * 5.0)) / Double.valueOf(this.ratingCount));
		
		return avg;
	}

	public String getLatestUpdate() {
		return latestUpdate;
	}

	public Log[] getUpdates() {
		return updates;
	}
}
