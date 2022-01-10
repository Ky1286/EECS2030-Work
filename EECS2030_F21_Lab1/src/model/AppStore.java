package model;

public class AppStore {

	private String country;
	private int maxApps;
	private App[] apps = new App[0];
	private String[] stableApps = new String[0];
	
	public AppStore(String country, int maxApps) {
		this.country = country;
		this.maxApps = maxApps;
	}

	public String getBranch() {
		return this.country;
	}

	public App getApp(String name) {
		App temp = null;
		for (int i = 0; i < this.apps.length; i++) {
			if (this.apps[i].getName().equals(name)) {
				temp = this.apps[i];
			}
		}
		return temp;
	}

	public String[] getStableApps(int num) {
		String[] temp;
		if (this.apps.length > 0) {
			int count = 0;
			for (int i = 0; i < apps.length; i++) {
				if (apps[i].getUpdateHistory().length >= num) {
					count++;
				}
			}
			temp = new String[count];
			count = 0;
			for (int i = 0; i < apps.length; i++) {
				if (apps[i].getUpdateHistory().length >= num) {
					temp[count] = apps[i].getName() + " (" + apps[i].getUpdates().length +" versions; Current Version: Version " + apps[i].getLatestUpdate() 
							+ " contains " + apps[i].getUpdates()[apps[i].getUpdates().length - 1].getNumberOfFixes() + " fixes " 
							+ apps[i].getUpdates()[apps[i].getUpdates().length - 1].getFixes() + ")";
					count++;
				}
			}
			this.stableApps = temp;
		}
		return this.stableApps;
	}

	public void addApp(App newApp) {
		App[] temp = new App[this.apps.length + 1];
		for (int i = 0; i < this.apps.length; i++) {
			temp[i] = this.apps[i];
		}
		temp[temp.length - 1] = newApp;
		this.apps = temp;
	}

	
}
