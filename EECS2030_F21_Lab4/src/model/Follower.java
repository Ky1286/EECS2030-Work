package model;

public class Follower {

	protected Channel[] following = new Channel[0];
	private String name;
	private String status;
	protected String[][] recommendedVids = new String[2][0];
	private String[][] watchStats = new String[2][0];
	
	private int watchTime = 0;
	private double avgWatch = 0;
	private int count = 0;
	private int maxTime = 0;
	
	private double data[][] = new double[4][0];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void subTo(Channel name, String followerType) {
		Channel[] temp = new Channel[this.following.length + 1];

		for (int i = 0; i < this.following.length; i++) {
			temp[i] = this.following[i];
		}
		temp[temp.length - 1] = name;
		this.status = followerType + " " + this.name + " " + "follows [";
		for (int i = 0; i < temp.length; i++) {
			this.status += temp[i].getName();
			if (i < temp.length - 1) {
				this.status += ", ";
			}
		}
		if (followerType == "Subscriber") {
			this.status += "] and has no recommended videos.";
		} else {
			this.status += "].";
		}
		this.following = temp;
	}

	public void unsubTo(Channel name, String followType) {
		if (this.following.length == 1) {
			Channel[] temp = new Channel[0];
			this.following = temp;
			if (followType == "Subscriber") {
				this.status = followType + " " + this.name + " follows no channels and has no recommended videos.";
			} else {
				this.status = followType + " " + this.name + " follows no channels.";
			}
		} else {
			Channel[] temp = new Channel[this.following.length - 1];
			int counter = 0;
			for (int i = 0; i < this.following.length; i++) {
				if (this.following[i] != name) {
					temp[counter] = this.following[i];
					counter++;
				}
			}
			this.status = followType + " " + this.name + " " + "follows [";
			for (int i = 0; i < temp.length; i++) {
				this.status += temp[i].getName();
				if (i < temp.length - 1) {
					this.status += ", ";
				}
			}
			if (followType == "Subscriber") {
				this.status += "] and has no recommended videos.";
			} else {
				this.status += "].";
			}
			this.following = temp;
		}
	}

	public void recommend(String name, String channelName) {
		this.status = "Subscriber " + this.name + " follows [";
		for (int i = 0; i < this.following.length; i++) {
			this.status += this.following[i].getName();
			if (i < this.following.length - 1) {
				this.status += ", ";
			}
		}
		String[][] temp = new String[2][this.recommendedVids[0].length + 1];
		for (int i = 0; i < this.recommendedVids.length; i++) {
			for (int j = 0; j < this.recommendedVids[i].length; j++) {
				temp[i][j] = this.recommendedVids[i][j];
			}
		}
		temp[0][temp[0].length - 1] = name;
		temp[1][temp[1].length - 1] = channelName;
		this.status += "] and is recommended <";
		for (int i = 0; i < temp[0].length; i++) {
			this.status += temp[0][i];
			if (i < temp[0].length - 1) {
				this.status += ", ";
			}
		}
		this.status += ">.";
		this.recommendedVids = temp;
	}
	
	public String toString() {
		return this.status;
	}
	
	public void monUpdate(String name, int time) {
		String[][] filler = null;
		double[][] fillerData = null;
		boolean sameChannel = false;
		int counter = 0;
		for (int i = 0; i < this.watchStats[0].length; i++) {
			if (this.watchStats[0][i].equals(name)) {
				sameChannel = true;
				counter = i;
			}
		}
		if (sameChannel == false) {
			double[][] tempdata = new double[4][this.data[0].length + 1];
			for (int i = 0; i < this.data.length; i++) {
				for (int j = 0; j < this.data[i].length; j++) {
					tempdata[i][j] = this.data[i][j];
				}
			}
			tempdata[0][tempdata[0].length - 1] = time; //total watch time
			tempdata[1][tempdata[1].length - 1] = 1; //first view
			tempdata[2][tempdata[2].length - 1] = tempdata[0][tempdata[0].length - 1] / tempdata[1][tempdata[1].length - 1]; //average watch time
			tempdata[3][tempdata[3].length - 1] = time; //first view, which means the top time watched, is the time
			
			String stats = "{#views: " + (int) tempdata[1][tempdata[1].length - 1] + ", max watch time: " 
					+ (int) tempdata[0][tempdata[0].length - 1] + ", avg watch time: " 
					+ String.format("%.2f", tempdata[2][tempdata[2].length - 1]) + "}";
			
			String[][] temp = new String[2][this.watchStats[0].length + 1];
			for (int i = 0; i < this.watchStats.length; i++) {
				for (int j = 0; j < this.watchStats[i].length; j++) {
					temp[i][j] = this.watchStats[i][j];
				}
			}
			temp[0][temp[0].length - 1] = name;
			temp[1][temp[1].length - 1] = stats;
			filler = temp;
			fillerData = tempdata;
		} else {
			double[][] tempdata = this.data;
			tempdata[0][counter] += time; //total watch time
			tempdata[1][counter] = tempdata[1][counter] + 1;
			tempdata[2][counter] = tempdata[0][counter] / tempdata[1][counter]; //average watch time
			if (tempdata[3][counter] < time) {
				tempdata[3][counter] = time; 
			}
			
			String stats = "{#views: " + (int) tempdata[1][counter] + ", max watch time: " 
					+ (int) tempdata[3][counter] + ", avg watch time: " 
					+ String.format("%.2f", tempdata[2][counter]) + "}";
			
			String[][] temp = this.watchStats;
			for (int i = 0; i < this.watchStats[1].length; i++) {
				if (this.watchStats[0][i].equals(name)) {
					temp[1][i] = stats;
				}
			}
			filler = temp;
			fillerData = tempdata;
		}
		this.watchStats = filler;
		this.data = fillerData;
		this.status = "Monitor " + this.name + " follows [";
		for (int i = 0; i < this.following.length; i++) {
			this.status += this.following[i].getName();
			if (i < filler[0].length) {
				if (this.following[i].getName().equals(filler[0][i])) {
					this.status += " "+ watchStats[1][i];
				}
			}
			if (i < this.following.length - 1) {
				this.status += ", ";
			}
		}
		this.status += "].";
	}
}
