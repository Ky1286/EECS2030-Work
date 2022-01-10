package model;

public class WeatherApp {

	protected String status;
	protected WeatherStation[] connected = new WeatherStation[0];
	protected String name;
	
	public String toString() {
		return this.status;
	}

	public String getName() {
		return name;
	}

	public void addConnection(WeatherStation station) {
		WeatherStation[] temp = new WeatherStation[this.connected.length + 1];
		for (int i = 0; i < this.connected.length; i++) {
			temp[i] = this.connected[i];
		}
		temp[temp.length - 1] = station;
		this.connected = temp;
	}
	
	public void updateStatus(String appType) {
		this.status = "Weather " + appType + " App " + this.name + " is connected to " 
						+ this.connected.length + " stations: <";
		
		for (int i = 0; i < this.connected.length; i++) {
			this.status += this.connected[i].getName();
			if (i < this.connected.length - 1) {
				this.status += ", ";
			}
		}
		
		this.status += ">.";
	}
	
	public void updateForcast() {
		String result = "Weather Forecast App " + this.name + " is connected to " + this.connected.length + " stations: <";
		
		for (int i = 0; i < this.connected.length; i++) {
			result += this.connected[i].getName();
			if (this.connected[i].getForecast() != null) {
				result += " " + this.connected[i].getForecast();
			}
			if (i < this.connected.length - 1) {
				result += ", ";
			}
		}
		result += ">.";
		this.status = result;
	}
}
