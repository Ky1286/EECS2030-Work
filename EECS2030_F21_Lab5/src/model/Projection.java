package model;

public class Projection extends BinarySeqOperation {

	private int[] seq1;
	private int[] seq2;
	protected String projectionResult;
	
	public Projection(int[] seq1, int[] seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		
		boolean moreThanOne = false;
		
		this.projectionResult = "Projecting [";
		for (int i = 0; i < this.seq1.length; i++) {
			this.projectionResult += this.seq1[i];
			if(i < this.seq1.length - 1) {
				this.projectionResult += ", ";
			}
		}
		this.projectionResult += "] to [";
		for (int i = 0; i < this.seq2.length; i++) {
			this.projectionResult += this.seq2[i];
			if(i < this.seq2.length - 1) {
				this.projectionResult += ", ";
			}
		}
		this.projectionResult += "] results in: [";
		for (int i = 0; i < this.seq2.length; i++) {
			for (int j = 0; j < seq1.length; j++) {
				if (this.seq2[i] == this.seq1[j]) {
					if (moreThanOne == true) {
						this.projectionResult += ", ";
					}
					this.projectionResult += this.seq1[j];
					moreThanOne = true;
					j += seq1.length;
				}
			}
		}
		this.projectionResult += "]";
		super.status = this.projectionResult;
	}

}
