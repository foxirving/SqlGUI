package datamodel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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

	private String myStudentResidancy = NOT_SPECIFIED;

	private String myStudentVehicle = NOT_SPECIFIED;

	private String myStudentGrade = "0.0";
	
	private boolean getStudent = false;

	private boolean getInstructor = false;

	private boolean getCourses = false;

	private String myStudentGender = NOT_SPECIFIED;
	
	private  String myFirstName = NOT_SPECIFIED;

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
		
		StringBuilder sb = new StringBuilder();
		
		/**
		 * Build Query Here
		 */
	
		
		
		/**
		 * Executes Query here.
		 */
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;
		try {
			/* This requires the JDBC SQL driver, available from Microsoft at
			 * https://www.microsoft.com/en-us/download/details.aspx?displaylang=en&id=11774
			 * Once the download is complete and unzipped, the .jar file must be added to the 
			 * project build path through Project > Properties > Libraries > Add External Jar;
			 * You also need to go to that same window >  Order and Export and make sure the box
			 * is checked for that .jar. 
			 * Then, you also need to go to Run > Run Configurations > Classpath, select
			 * User Defined Entries, and Add External Jar to add the same jar to the classpath.
			 * You must also go to Run > Run Configurations > Argumets and add the following
			 * as a VM Argument (NOT as a Program Argument!!):
			 * "-Djava.library.path="$PATH\sqljdbc_6.2\enu\auth\x64", where $PATH is the
			 * path to the folder where you unzipped the driver. Also be sure to point to the right
			 * folder for x64 or x86 depending on the driver you used. 
			 */

			// we're using Windows authentication so we don't need the username and password
			String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=StudentRecords;integratedSecurity=true";

			conn = DriverManager.getConnection(url);

			DatabaseMetaData dbmd = conn.getMetaData();
			
			// this translates the string into a SQL query
			stmt = conn.prepareStatement(sb.toString());
			
			// Executes the query
			rset = stmt.executeQuery();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}

		
		return rset;

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


	public String getMyLastName() {
		return myLastName;
	}

	public void setMyFirstName(String myFirstName) {
		this.myFirstName = myFirstName;
	}



	public void setMyLastName(String myLastName) {
		this.myLastName = myLastName;
	}

	public void setMyStudentGender(String s) {
		this.myStudentGender = s;
		
	}

	public String getMyStudentResidancy() {
		return myStudentResidancy;
	}

	public void setMyStudentResidancy(String myStudentResidancy) {
		this.myStudentResidancy = myStudentResidancy;
	}


	public String getMyStudentVehicle() {
		return myStudentVehicle;
	}


	public void setMyStudentVehicle(String myStudentVehicle) {
		this.myStudentVehicle = myStudentVehicle;
	}

	public String getMyStudentGrade() {
		return myStudentGrade;
	}

	public void setMyStudentGrade(String myStudentGrade) {
		this.myStudentGrade = myStudentGrade;
	}
	
	

}
