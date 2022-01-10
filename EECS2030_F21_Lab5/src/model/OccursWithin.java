package model;

public class OccursWithin extends BinarySeqOperation {

	private int[] seq1;
	private int[] seq2;
	protected String occursResult;
	
	public OccursWithin(int[] seq1, int[] seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		if (seq1.length > 0) {
			int count = 0;
			boolean found = false;
			for (int i = 0; i < this.seq2.length; i++) {
				if (found == false) {
					if (this.seq2[i] == seq1[count]) {
						count++;
						if (count == this.seq1.length) {
							found = true;
						}
					} else {
						count = 0;
					}
				}
			}
			occursResult = "[";
			for (int i = 0; i < this.seq1.length; i++) {
				this.occursResult += this.seq1[i];
				if(i < this.seq1.length - 1) {
					this.occursResult += ", ";
				}
			}
			if (found == true) {
				this.occursResult += "] occurs within [";
			} else {
				this.occursResult += "] does not occur within [";
			}
			for (int i = 0; i < this.seq2.length; i++) {
				this.occursResult += this.seq2[i];
				if(i < this.seq2.length - 1) {
					this.occursResult += ", ";
				}
			}
			this.occursResult += "]";
		} else {
			this.occursResult = "[] occurs within [";
			for (int i = 0; i < this.seq2.length; i++) {
				this.occursResult += this.seq2[i];
				if(i < this.seq2.length - 1) {
					this.occursResult += ", ";
				}
			}
			this.occursResult += "]";
		}
		super.status = this.occursResult;
	}

}
