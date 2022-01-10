package model;

public class SumsOfPrefixes extends SeqOperation {

	protected int[] seq;
	protected String sumResult;
	
	public SumsOfPrefixes(int[] seq) {
		this.seq = seq;
		int temp = 0;
		int count = 1;
		if (this.seq.length > 0) {
			this.sumResult = "Sums of prefixes of [";
			for (int i = 0; i < this.seq.length; i++) {
				this.sumResult += this.seq[i];
				if (i < this.seq.length - 1) {
					this.sumResult += ", ";
				}
			}
			this.sumResult += "] is: [0, ";
			for (int i = 0; i < this.seq.length; i++) {
				for (int j = 0; j < count; j++) {
					temp += this.seq[j];
				}
				this.sumResult += temp;
				if (i < this.seq.length - 1) {
					this.sumResult += ", ";
				}
				temp = 0;
				count++;
			}
			this.sumResult += "]";
		} else if (this.seq.length == 0){
			this.sumResult = "Sums of prefixes of [] is: []";
		}
		super.status = this.sumResult;
	}

}
