package model;

public class Account {

	private String name;
	private AppStore store;
	private String[] downloadedApps = new String[0];
	private App[] downloadedAppsObjs = new App[0];
	private String status;
	
	public Account(String name, AppStore store) {
		this.name = name;
		this.store = store;
	}

	public String[] getNamesOfDownloadedApps() {
		return this.downloadedApps;
	}

	public App[] getObjectsOfDownloadedApps() {
		return this.downloadedAppsObjs;
	}

	public void uninstall(String name) {
		int slot = 0;
		int count = 0;
		String[] temp;
		App[] temp2;
		if (this.downloadedApps.length == 0) {
			this.status = "Error: " + name + " has not been downloaded for " + this.name + ".";
		} else {
			boolean check = false;
			for (int i = 0; i < this.downloadedApps.length; i++) {
				if (this.downloadedApps[i].equals(name)) {
					check = true;
					slot = i;
				}
			}
			if (check == false) {
				this.status = "Error: " + name + " has not been downloaded for " + this.name + ".";
			} else {
				temp = new String[this.downloadedApps.length - 1];
				temp2 = new App[this.downloadedAppsObjs.length - 1];
				for (int i = 0; i < this.downloadedApps.length; i++) {
					if (i != slot) {
						temp[count] = this.downloadedApps[i];
						temp2[count] = this.downloadedAppsObjs[i];
						count++;
					}
				}
				this.downloadedApps = temp;
				this.downloadedAppsObjs = temp2;
				this.status = name + " is successfully uninstalled for " + this.name + ".";
			}
		}
	}
	
	public String toString() {
		if (this.status == null) {
			this.status = "An account linked to the " + this.store.getBranch() + " store is created for " + this.name + ".";
		}
		return this.status;
	}

	public void submitRating(String name, int rating) {
		int slot = 0;
		if (this.downloadedApps.length == 0) {
			this.status = "Error: " + name + " is not a downloaded app for " + this.name + ".";
		} else {
			boolean check = false;
			for (int i = 0; i < this.downloadedApps.length; i++) {
				if (this.downloadedApps[i].equals(name)) {
					check = true;
					slot = i;
				}
			}
			if (check == false) {
				this.status = "Error: " + name + " is not a downloaded app for " + this.name + ".";
			} else {
				this.downloadedAppsObjs[slot].submitRating(rating);
				this.status = "Rating score " + rating + " of " 
							+ this.name + " is successfully submitted for " + name + ".";
			}
		}
	}

	public void switchStore(AppStore store) {
		this.store = store;
		this.status = "Account for " + this.name + " is now linked to the " + this.store.getBranch() + " store.";
	}

	public void download(String name) {
		String[] temp = new String[this.downloadedApps.length + 1];
		App[] temp2 = new App[this.downloadedAppsObjs.length + 1];
		boolean check = true;
		for (int i = 0; i < this.downloadedApps.length; i++) {
			temp[i] = this.downloadedApps[i];
			temp2[i] = this.downloadedAppsObjs[i];
			if (this.downloadedApps[i].equals(name)) {
				this.status = "Error: " + name + " has already been downloaded for " + this.name + ".";
				check = false;
			}
		}
		if (check == true) {
			temp[temp.length - 1] = name;
			temp2[temp2.length - 1] = this.store.getApp(name);
			this.downloadedApps = temp;
			this.downloadedAppsObjs = temp2;
			this.status = name + " is successfully downloaded for " + this.name + ".";
		}
	}

}
