package model;

public class WeatherStation {

	private String name;
	private int applimit;
	private String status;
	
	private String forecast;
	
	private WeatherApp[] apps = new WeatherApp[0];
	private SensorApp[] senors = new SensorApp[0];
	
	private int maxTemp = 0;
	private int count = 0;
	private int pressure;
	private int totalTemp = 0;
	private String rain = "unlikely to rain";
	
	
	public WeatherStation(String name, int apps) {
		this.applimit = apps;
		this.name = name;
		this.status = this.name + " has no connected apps.";
	}

	public String toString() {
		return this.status;
	}

	public SensorApp[] getSensors() {
		// TODO Auto-generated method stub
		return this.senors;
	}

	public String getName() {
		return name;
	}

	public void connect(WeatherApp app) {
		if (app instanceof SensorApp) {
			SensorApp[] tempSensors = new SensorApp[this.senors.length + 1];
			for (int i = 0; i < this.senors.length; i++) {
				tempSensors[i] = this.senors[i];
			}
			tempSensors[tempSensors.length - 1] = (SensorApp) app;
			this.senors = tempSensors;
		}
		WeatherApp[] temp = new WeatherApp[this.apps.length + 1];
		for (int i = 0; i < this.apps.length; i++) {
			temp[i] = this.apps[i];
		}
		temp[temp.length - 1] = app;
		this.apps = temp;
		app.addConnection(this);
		if (app instanceof SensorApp) {
			app.updateStatus("Sensor");
		} else {
			app.updateStatus("Forecast");
		}
		
		this.status = this.name + " is connected by " + this.apps.length + " apps: <";
		for (int i = 0; i < this.apps.length; i++) {
			if (this.apps[i] instanceof SensorApp) {
				this.status += "Weather Sensor App " + this.apps[i].getName();
			} else {
				this.status += "Weather Forecast App " + this.apps[i].getName();
			}
			if (i < this.apps.length - 1) {
				this.status += ", ";
			}
		}
		this.status += ">.";
	}

	public void setForecast(int temp, int pressure) {
		if (temp > this.maxTemp) {
			this.maxTemp = temp;
		}
		this.count++;
		this.totalTemp = this.totalTemp + temp;
		if (count > 0) {
			if (this.pressure > pressure) {
				this.rain = "likely to rain";
			} else if (this.pressure < pressure) {
				this.rain = "unlikely to rain";
			}
		}
		this.pressure = pressure;
		String result = "{max temperature: " + this.maxTemp + ", avg temperature: " 
						+ String.format("%.1f", ((double) this.totalTemp) / ((double) this.count)) + ", " + this.rain + "}";
		
		this.forecast = result;
		for (int i = 0; i < this.apps.length; i++) {
			if (this.apps[i] instanceof ForecastApp) {
				this.apps[i].updateForcast();
			}
		}
	}

	public String getForecast() {
		return forecast;
	}

	public WeatherApp[] getApps() {
		return apps;
	}
}
