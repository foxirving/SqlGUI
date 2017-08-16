package datamodel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public enum DataModel {

	INSTANCE;

	public static final String NOT_SPECIFIED = "";

	public static String SqlUrl = "";
	public static String SqlUsername = "";
	public static String SqlPassword = "";
	public static String databaseName = "";

	private DataModel() {
		// private constructor;
	}

	private boolean isOnCampus = false;

	private boolean isNotOnCampus = false;

	private boolean hasVehicle = false;

	private boolean getStudent = false;

	private boolean getInstructor = false;

	private boolean getCourses = false;

	private boolean isMale = false;

	private boolean isFemale = false;

	private boolean isGenderNotSpecified = false;

	private  String myFirstName = NOT_SPECIFIED;

	private  String myMiddleName = NOT_SPECIFIED;

	private  String myLastName = NOT_SPECIFIED;

	private String myBirthYear = NOT_SPECIFIED;

	private String myVehicleLicenceNo = NOT_SPECIFIED;

	private String myVehicleYear = NOT_SPECIFIED;

	private Constants myVehicleMake = Constants.NOT_SPECIFIED;

	private Constants myVehicleModel = Constants.NOT_SPECIFIED;

	private Constants myVehicleColor = Constants.NOT_SPECIFIED;

	private String myInstructorFirstName = NOT_SPECIFIED;

	private String myInstructorLastName = NOT_SPECIFIED;

	private String myInstructorPhoneNumber = NOT_SPECIFIED;

	private String myCourseName = NOT_SPECIFIED;

	private double myLowerGrade = 0.0;

	private double myUpperGrade = 4.0;

	public ResultSet getTable() throws SQLException {
	
		/**
		 * Executes Query here.
		 */
		Statement stmt = null;
		Connection conn = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(SqlUrl, SqlUsername, SqlPassword);
			DatabaseMetaData dbmd = conn.getMetaData();
			databaseName = dbmd.getDatabaseProductName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		
		// Gets list with all males in it.
		String fnStudentGender = "{call dbo.fnStudentGender (?)}";
		CallableStatement cs2 = conn.prepareCall(fnStudentGender);
		cs2.setString(1, Constants.MALE.getMyString());
		ResultSet rs = cs2.executeQuery();
	
		
		return null;

	}

	public boolean isOnCampus() {
		return isOnCampus;
	}

	public void setOnCampus(boolean isOnCampus) {
		this.isOnCampus = isOnCampus;
	}

	public boolean isNotOnCampus() {
		return isNotOnCampus;
	}

	public void setNotOnCampus(boolean isNotOnCampus) {
		this.isNotOnCampus = isNotOnCampus;
	}

	public boolean isHasVehicle() {
		return hasVehicle;
	}

	public void setHasVehicle(boolean hasVehicle) {
		this.hasVehicle = hasVehicle;
	}

	public boolean isGetStudent() {
		return getStudent;
	}

	public void setGetStudent(boolean getStudent) {
		this.getStudent = getStudent;
	}

	public boolean isGetInstructor() {
		return getInstructor;
	}

	public void setGetInstructor(boolean getInstructor) {
		this.getInstructor = getInstructor;
	}

	public boolean isGetCourses() {
		return getCourses;
	}

	public void setGetCourses(boolean getCourses) {
		this.getCourses = getCourses;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}

	public boolean isGenderNotSpecified() {
		return isGenderNotSpecified;
	}

	public void setGenderNotSpecified(boolean isGenderNotSpecified) {
		this.isGenderNotSpecified = isGenderNotSpecified;
	}

	public String getMyBirthYear() {
		return myBirthYear;
	}

	public void setMyBirthYear(String myBirthYear) {
		this.myBirthYear = myBirthYear;
	}

	public String getMyVehicleLicenceNo() {
		return myVehicleLicenceNo;
	}

	public void setMyVehicleLicenceNo(String myVehicleLicenceNo) {
		this.myVehicleLicenceNo = myVehicleLicenceNo;
	}

	public String getMyVehicleYear() {
		return myVehicleYear;
	}

	public void setMyVehicleYear(String myVehicleYear) {
		this.myVehicleYear = myVehicleYear;
	}

	public Constants getMyVehicleMake() {
		return myVehicleMake;
	}

	public void setMyVehicleMake(Constants myVehicleMake) {
		this.myVehicleMake = myVehicleMake;
	}

	public Constants getMyVehicleModel() {
		return myVehicleModel;
	}

	public void setMyVehicleModel(Constants myVehicleModel) {
		this.myVehicleModel = myVehicleModel;
	}

	public Constants getMyVehicleColor() {
		return myVehicleColor;
	}

	public void setMyVehicleColor(Constants myVehicleColor) {
		this.myVehicleColor = myVehicleColor;
	}

	public String getMyInstructorFirstName() {
		return myInstructorFirstName;
	}

	public void setMyInstructorFirstName(String myInstructorFirstName) {
		this.myInstructorFirstName = myInstructorFirstName;
	}

	public String getMyInstructorLastName() {
		return myInstructorLastName;
	}

	public void setMyInstructorLastName(String myInstructorLastName) {
		this.myInstructorLastName = myInstructorLastName;
	}

	public String getMyInstructorPhoneNumber() {
		return myInstructorPhoneNumber;
	}

	public void setMyInstructorPhoneNumber(String myInstructorPhoneNumber) {
		this.myInstructorPhoneNumber = myInstructorPhoneNumber;
	}

	public String getMyCourseName() {
		return myCourseName;
	}

	public void setMyCourseName(String myCourseName) {
		this.myCourseName = myCourseName;
	}

	public double getMyLowerGrade() {
		return myLowerGrade;
	}

	public void setMyLowerGrade(double myLowerGrade) {
		this.myLowerGrade = myLowerGrade;
	}

	public double getMyUpperGrade() {
		return myUpperGrade;
	}

	public void setMyUpperGrade(double myUpperGrade) {
		this.myUpperGrade = myUpperGrade;
	}

	public String getMyFirstName() {
		return myFirstName;
	}

	public String getMyMiddleName() {
		return myMiddleName;
	}

	public String getMyLastName() {
		return myLastName;
	}

	public void setMyFirstName(String myFirstName) {
		this.myFirstName = myFirstName;
	}

	public void setMyMiddleName(String myMiddleName) {
		this.myMiddleName = myMiddleName;
	}

	public void setMyLastName(String myLastName) {
		this.myLastName = myLastName;
	}
	
	

}
