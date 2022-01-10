package model;

public class Account {

	private String name;
	private int bal;
	private String type = "regular";
	private String status;
	private int vipCost;
	
	public Account(String string, int i) {
		this.name = string;
		this.bal = i;
		this.status = "A " + this.type + " account owned by " + this.name + " with balance $" + this.bal;
	}

	public Account(Account a) {
		this.bal = a.getBal();
		this.name = a.getName();
		this.status = a.getStatus();
		this.vipCost= a.getVipCost();
		this.type = a.getType();
	}

	public String toString() {
		return this.status;
	}

	public void switchToVIP(int i) throws InvalidStatusToSwitchException, InsufficientBalanceException {
		if (type == "VIP") {
			throw new InvalidStatusToSwitchException("Already VIP");
		}
		if ((this.bal - i) < 0) {
			throw new InsufficientBalanceException("Not enough funds");
		}
		this.type = "VIP";
		this.bal -= i;
		this.vipCost = i;
		this.status = "A " + this.type + " account owned by " + this.name + " with balance $" + this.bal +" ($" + i + " deposited for maintaining the VIP stauts)";
	}

	public void switchToRegular() throws InvalidStatusToSwitchException {
		if (type == "regular") {
			throw new InvalidStatusToSwitchException("Already regular");
		}
		this.type = "regular";
		this.bal += this.vipCost;
		this.status = "A " + this.type + " account owned by " + this.name + " with balance $" + this.bal;
	}
	
	public String getName() {
		return name;
	}

	public int getBal() {
		return bal;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public int getVipCost() {
		return vipCost;
	}

	public boolean equals(Account a) {
		return this.name == a.getName() && this.bal == a.getBal() && this.type == a.getType();

	}
}
