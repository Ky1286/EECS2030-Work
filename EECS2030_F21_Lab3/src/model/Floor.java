package model;

public class Floor {

	private int space;
	private int usedSpace = 0;
	private String status;
	private Unit[] units = new Unit[0];
	
	public Floor(int space) {
		this.space = space;
		this.status = "Floor's utilized space is " + this.usedSpace + " sq ft (" + this.space + " sq ft remaining): []";
	}

	public Floor(Floor floor) {
		this.space = floor.getSpace();
		this.usedSpace = floor.getUsedSpace();
		this.status = floor.getStatus();
		this.units = floor.getUnits();
	}
	public String toString() {
		return this.status;
	}

	public void addUnit(String name, int x, int y) throws InsufficientFloorSpaceException {
		if (this.space - (x * y) < 0) {
			throw new InsufficientFloorSpaceException ("Insufficient Floor Space");
		}
		Unit temp = new Unit(name, x, y);
		Unit[] tempUnits = new Unit[this.units.length + 1];
		for (int i = 0; i < this.units.length; i++) {
			tempUnits[i] = this.units[i];
		}
		tempUnits[tempUnits.length - 1] = temp;
		this.usedSpace += (x * y);
		this.space -= (x * y);
		String result = "Floor's utilized space is " + this.usedSpace + " sq ft (" + this.space + " sq ft remaining): [";
		this.units = tempUnits;
		for (int i = 0; i < this.units.length; i++) {
			result += this.units[i].getName() + ": " + this.units[i].getArea() + " sq ft (" 
					+ this.units[i].getWideInches() + "' by " + this.units[i].getLongInches() + "')";
			if (i < this.units.length - 1) {
				result += ", ";
			}
		}
		result += "]";
		this.status = result;
	}
	
	public int getSpace() {
		return space;
	}
	
	public Unit[] getUnits() {
		return units;
	}

	public int getUsedSpace() {
		return usedSpace;
	}

	public String getStatus() {
		return status;
	}

	public boolean equalCheck(Floor x) {
		boolean result = false;
		int counter = 0;
		
		Unit[] unitX = x.getUnits();
		
		for (int i = 0; i < this.units.length; i++) {
			for (int j = 0; j < unitX.length; j++) {
				if (unitX[j].isChecked() == false) {
					if (this.units[i].equals(unitX[j])) {
						counter++;
						unitX[j].setChecked(true);
					}					
				}

			}
		}
		if (counter == this.units.length) {
			result = true;
		}
		for (int i = 0; i < unitX.length; i++) {
			unitX[i].setChecked(false); //reset checked state
		}
		
		return result;
	}

	public boolean equals(Object x) {
		Floor temp = (Floor) x;
		if (this == x) {
			return true;
		}
		if (x == null || this.space != temp.getSpace() || this.units.length != temp.getUnits().length) {
			return false;
		}
		return this.equalCheck(temp);
	}
}
