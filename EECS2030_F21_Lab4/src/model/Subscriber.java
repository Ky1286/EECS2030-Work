package model;

public class Subscriber extends Follower {

	private String name;
	private int maxNoC; // number of channels
	private int maxNoV; // number of vids

	public Subscriber(String name, int maxNoC, int maxNoV) {
		this.name = name;
		this.maxNoC = maxNoC;
		this.maxNoV = maxNoV;
		this.setName(name);
		this.setStatus("Subscriber " + this.name + " follows no channels and has no recommended videos.");
	}

	public void watch(String vidName, int time) {
		String temp = null;
		for (int i = 0; i < super.recommendedVids[0].length; i++) {
			if (super.recommendedVids[0][i].equals(vidName)) {
				temp = super.recommendedVids[1][i];
			}
		}
		for (int i = 0; i < super.following.length; i++) {
			if (super.following[i].getName().equals(temp)) {
				super.following[i].watchStat(time);
			}
		}
	}
	
}
