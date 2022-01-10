package model;

public class FilterAll extends SeqEvaluator {

	protected int limit;
	
	public FilterAll(int i) {
		this.limit = i;
	}

	public String toString() {
		String result = "Filter result is: ";
		String [] temp = super.withinResults;
		
		for (int i = 0; i < temp.length; i++) {
			result += temp[i];
			if (i < temp.length - 1) {
				result += ", ";
			}
		}
		
		if (super.projectCount > 0 || super.sumOfCount > 0) {
			result = "Filter cannot be evaluated due to " + (super.projectCount + super.sumOfCount) + " incompatile operations.";
		}
		return result;
	}
}
