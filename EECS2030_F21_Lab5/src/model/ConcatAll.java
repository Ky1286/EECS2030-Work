package model;

public class ConcatAll extends SeqEvaluator {

	protected int limit;
	
	public ConcatAll(int i) {
		this.limit = i;
	}
	
	public String toString() {
		String result = "Concat([";
		int[][] temp = super.seq;
		if (super.occursCount > 0) {
			result = "Concat cannot be evaluated due to " + super.occursCount + " incompatile operations.";
		} else {
			for (int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp[i].length; j++) {
					result += temp[i][j];
					if (j < temp[i].length - 1) {
						result += ", ";
					}
				}
				if (i < temp.length - 1) {
					result += "], [";
				}
			}
			result += "]) = [";
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp[i].length; j++) {
					result += temp[i][j];
					if (j < temp[i].length) {
						if (j == temp[i].length - 1 && temp[i + 1].length == 0) {
							result += "";
						} else {
							result += ", ";
						}
					}
				}
			}
			result += "]";
		}
		return result;
	}
}
