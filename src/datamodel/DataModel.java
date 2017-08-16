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

	public static final String NOT_SPECIFIED = "NA";

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

	private final String myFirstName = NOT_SPECIFIED;

	private final String myMiddleName = NOT_SPECIFIED;

	private final String myLastName = NOT_SPECIFIED;

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

	public void getTable() throws SQLException {
	
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
	
		
	

	}

}
