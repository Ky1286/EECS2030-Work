package model;

public class Log {

	private String versionNum;
	private int numOfFixes = 0;
	private String fixes = "";
	
	public Log (String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersion() {
		// Returns versionNum
		return this.versionNum;
	}

	public int getNumberOfFixes() {
		// Returns number of fixes
		return this.numOfFixes;
	}

	public String getFixes() {
		// Returns fixes in a string form
		return "[" + this.fixes + "]";
	}
	
	public String toString() {
		String result = "Version " + this.versionNum + " contains " + this.numOfFixes + " fixes " + getFixes();
		
		return result;
	}

	public void addFix(String fix) {
		if (numOfFixes == 0) {
			this.fixes += fix;			
		} else {
			this.fixes += ", " + fix;
		}
		this.numOfFixes++;
	}

}
