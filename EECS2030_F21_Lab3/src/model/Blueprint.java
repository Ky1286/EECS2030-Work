package model;

public class Blueprint {

	private int floorNum;
	private Floor[] floors = new Floor[0];
	private String status;
	
	public Blueprint(int floorNum) {
		this.floorNum = floorNum;
		
		this.status = "0.0 percents of building blueprint completed (" + this.floors.length + " out of " + this.floorNum + " floors)";
	}

	public Blueprint(Blueprint bp) {
		this.floorNum = bp.getFloorNum();
		Floor[] temp = new Floor[bp.getFloors().length];
		Floor[] tempBp = bp.getFloors();
		for (int i = 0; i < tempBp.length; i++) {
			temp[i] = new Floor(tempBp[i]);
		}
		this.floors = temp;
		this.status = bp.toString();
	}

	public void addFloorPlan(Floor f1) {
		Floor[] temp = new Floor[this.floors.length + 1];
		Floor floor = new Floor(f1);
		for (int i = 0; i < this.floors.length; i++) {
			temp[i] = this.floors[i];
		}
		temp[temp.length - 1] = floor;
		this.floors = temp;
		this.status = String.format("%.1f", ((double) this.floors.length / this.floorNum) * 100) 
						+ " percents of building blueprint completed (" + this.floors.length + " out of " 
						+ this.floorNum + " floors)";
	}

	public Floor[] getFloors() {
		return this.floors;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public String toString() {
		return this.status;
	}
}
