package model;

public class Monitor extends Follower {

	private String name;
	private int maxNoC; // Noc = Number of Channels

	public Monitor(String name, int maxNoC) {
		this.name = name;
		this.maxNoC = maxNoC;
		this.setName(name);
		this.setStatus("Monitor " + this.name + " follows no channels.");
	}

}
