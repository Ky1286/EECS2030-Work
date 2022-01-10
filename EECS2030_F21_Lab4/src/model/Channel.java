package model;

public class Channel {

	private String name;
	private int maxFollows;
	private int maxVids;
	private String status;
	private String[] videos = new String[0];
	private Follower[] subbed = new Follower[0];

	public Channel(String name, int maxFollows, int maxVids) {
		this.name = name;
		this.maxFollows = maxFollows;
		this.maxVids = maxVids;
		this.status = this.name + " released no videos and has no followers.";
	}

	public String toString() {
		return this.status;
	}

	public void releaseANewVideo(String title) {
		String[] temp = new String[this.videos.length + 1];

		for (int i = 0; i < this.videos.length; i++) {
			temp[i] = this.videos[i];
		}
		temp[temp.length - 1] = title;
		this.status = this.name + " released <";
		for (int i = 0; i < temp.length; i++) {
			this.status += temp[i];
			if (i < temp.length - 1) {
				status += ", ";
			}
		}
		if (this.subbed.length == 0) {
			this.status += "> and has no followers.";
		} else {
			this.status += "> and is followed by [";
			for (int i = 0; i < this.subbed.length; i++) {
				if (this.subbed[i] instanceof Subscriber) {
					this.status += "Subscriber " + this.subbed[i].getName();
					this.subbed[i].recommend(title, this.name);
				} else if (this.subbed[i] instanceof Monitor) {
					this.status += "Monitor " + this.subbed[i].getName();
				}
				if (i < this.subbed.length - 1) {
					status += ", ";
				}
			}
			this.status += "].";
		}
		
		this.videos = temp;
	}

	public void follow(Follower f) {
		Follower[] temp = new Follower[this.subbed.length + 1];

		for (int i = 0; i < this.subbed.length; i++) {
			temp[i] = this.subbed[i];
		}
		temp[temp.length - 1] = f;

		if (this.videos.length == 0) {
			this.status = this.name + " released no videos and is followed by [";
		} else {
			this.status = this.name + " released <";
			for (int i = 0; i < this.videos.length; i++) {
				this.status += this.videos[i];
				if (i < this.videos.length - 1) {
					this.status += ", ";
				}
			}
			this.status += "> and is followed by [";
		}

		for (int i = 0; i < temp.length; i++) {
			if (temp[i] instanceof Subscriber) {
				this.status += "Subscriber " + temp[i].getName();
			} else if (temp[i] instanceof Monitor) {
				this.status += "Monitor " + temp[i].getName();
			}
			if (i < temp.length - 1) {
				status += ", ";
			}
		}

		this.status += "].";
		this.subbed = temp;
		if (temp[temp.length - 1] instanceof Subscriber) {
			f.subTo(this, "Subscriber");
		} else {
			f.subTo(this, "Monitor");
		}
	}

	public String getName() {
		return name;
	}

	public void watchStat(int time) {
		for (int i = 0; i < this.subbed.length; i++) {
			if (this.subbed[i] instanceof Monitor) {
				this.subbed[i].monUpdate(this.name, time);
			}
		}
	}
	
	public void unfollow(Follower f) {
		boolean notSubbed = false;
		for (int i = 0; i < this.subbed.length; i++) {
			if (this.subbed[i].equals(f)) {
				notSubbed = true;
			}
		}
		if (notSubbed == true) {
			if (this.subbed.length == 1) {
				Follower[] temp = new Follower[0];
				if (this.subbed[this.subbed.length - 1] instanceof Subscriber) {
					f.unsubTo(this, "Subscriber");
				} else {
					f.unsubTo(this, "Monitor");
				}
				this.subbed = temp;
				this.status = this.name + " released no videos and has no followers.";
			} else {
				Follower[] temp = new Follower[this.subbed.length - 1];
				int counter = 0;
				for (int i = 0; i < this.subbed.length; i++) {
					if (this.subbed[i] != f) {
						temp[counter] = this.subbed[i];
						counter++;
					} else {
						if (this.subbed[i] instanceof Subscriber) {
							f.unsubTo(this, "Subscriber");
						} else {
							f.unsubTo(this, "Monitor");
						}
					}
				}
				if (this.videos.length == 0) {
					this.status = this.name + " released no videos and is followed by [";
				} else {
					this.status = this.name + " released <";
					for (int i = 0; i < this.videos.length; i++) {
						this.status += this.videos[i];
						if (i < this.videos.length - 1) {
							this.status += ", ";
						}
					}
					this.status += "> and is followed by [";
				}

				for (int i = 0; i < temp.length; i++) {
					if (temp[i] instanceof Subscriber) {
						this.status += "Subscriber " + temp[i].getName();
					} else if (temp[i] instanceof Monitor) {
						this.status += "Monitor " + temp[i].getName();
					}
					if (i < temp.length - 1) {
						status += ", ";
					}
				}
				this.status += "].";
				this.subbed = temp;
			}
		}
	}
}
