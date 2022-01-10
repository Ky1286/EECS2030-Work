package model;

public class ForecastApp extends WeatherApp {

	protected String name;
	protected int connections;
	
	public ForecastApp(String name, int connections) {
		this.name = name;
		super.name = name;
		this.connections = connections;
		super.status = "Weather Forecast App " + this.name + " is connected to no stations.";
	}

}
