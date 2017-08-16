package datamodel;

public enum Constants {

	FEMALE("F"), MALE("M"), GENDER_NOT_SPECIFIED("-"), ALL_GENDERS("A"),
	
	NOT_SPECIFIED("NA");
	
	private String myString;
	
	private Constants(final String theString) {
		this.myString = theString;
	}
	

	public String getMyString() {
		return myString;
	}
	
	
}
