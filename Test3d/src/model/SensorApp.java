package model;

public class SensorApp extends WeatherApp {

	protected String name;
	protected int connections;
	private int maxTemp = 0;
	private int count = 0;
	private int pressure;
	private int totalTemp = 0;
	private String rain = "unlikely to rain";
	
	public SensorApp(String name, int connections) {
		this.name = name;
		super.name = name;
		this.connections = connections;
		super.status = "Weather Sensor App " + this.name + " is connected to no stations.";
	}

	public void updateMeasurements(String stationName, int temp, int pressure) {
		for (int i = 0; i < super.connected.length; i++) {
			if (super.connected[i].getName().equals(stationName)) {
				super.connected[i].setForecast(temp, pressure);
			}
		}
	}

	public String[] getConnectedForcastersOf(String string) {
		WeatherApp[] temp = new WeatherApp[0];
		int count = 0;
		for (int i = 0; i < super.connected.length; i++) {
			if (super.connected[i].getName().equals(string)) {
				temp = super.connected[i].getApps();
			}
		}
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] instanceof ForecastApp) {
				count++;
			}
		}
		String[] result = new String[count];
		count = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] instanceof ForecastApp) {
				result[count] = temp[i].getName() + " at index " + i;
				count++;
			}
		}			
		return result;
	}
}
