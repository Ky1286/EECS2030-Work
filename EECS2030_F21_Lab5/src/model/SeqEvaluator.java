package model;

public class SeqEvaluator {

	protected int projectCount = 0;
	protected int sumOfCount = 0;
	protected int occursCount = 0;
	
	protected int[][] seq = new int[0][];
	protected String[] withinResults = new String[0];
	
	public void addOperation(String string, int[] seq1, int[] seq2) throws IllegalOperationException {
		if (string.equals("op:projection")) {
			this.projectCount++;
			int count = 0;
			for (int i = 0; i < seq2.length; i++) {
				for (int j = 0; j < seq1.length; j++) {
					if (seq2[i] == seq1[j]) {
						count++;
						j += seq1.length;
					}
				}
			}
			int[] temp = new int[count];
			count = 0;
			for (int i = 0; i < seq2.length; i++) {
				for (int j = 0; j < seq1.length; j++) {
					if (seq2[i] == seq1[j]) {
						temp[count] = seq1[j];
						count++;
						j += seq1.length;
					}
				}
			}
			int[][] tempa = new int[this.seq.length + 1][];
			for (int i = 0; i < this.seq.length; i++) {
				tempa[i] = this.seq[i];
			}
			tempa[tempa.length - 1] = temp;
			this.seq = tempa;
		} else if (string.equals("op:sumsOfPrefixes")) {
			this.sumOfCount++;
			int[] temp = new int[seq1.length + 1];
			int holder = 0;
			int count = 1;
			int arrayCounter = 1;
			temp[0] = 0;
			for (int i = 0; i < seq1.length; i++) {
				for (int j = 0; j < count; j++) {
					holder += seq1[j];
				}
				temp[arrayCounter] = holder;
				holder = 0;
				arrayCounter++;
				count++;
			}
			int[][] tempa = new int[this.seq.length + 1][];
			for (int i = 0; i < this.seq.length; i++) {
				tempa[i] = this.seq[i];
			}
			tempa[tempa.length - 1] = temp;
			this.seq = tempa;
		} else if (string.equals("op:occursWithin")) {
			this.occursCount++;
			int count = 0;
			boolean found = false;
			for (int i = 0; i < seq2.length; i++) {
				if (found == false) {
					if (seq2[i] == seq1[count]) {
						count++;
						if (count == seq1.length) {
							found = true;
						}
					} else {
						count = 0;
					}
				}
			}
			String[] temp = new String[this.withinResults.length + 1];
			for (int i = 0; i < this.withinResults.length; i++) {
				temp[i] = this.withinResults[i];
			}
			if (found == false) {
				temp[temp.length - 1] = "_";
			} else if (found == true) {
				temp[temp.length - 1] = "true";
			}
			this.withinResults = temp;
		} else {
			throw new IllegalOperationException ("Illegal Operation");
		}
	}

}
