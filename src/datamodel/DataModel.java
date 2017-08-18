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

	private DataModel() {
		// private constructor;
	}

	private String myDormNumber = "1";

	private String myStudentResidancy = "On Campus";

	private String myStudentVehicle = "Has Vehicle";

	private String myStudentVehicleDecade = "2010";

	private String myStudentGrade = "0.0";

	private String myStudentGender = Constants.MALE.getMyString();

	private String myBirthYear = "2017";

	public ResultSet getTable() throws SQLException {

		StringBuilder sb = new StringBuilder();

		/**
		 * Build Query Here
		 */

		if (myStudentGender == Constants.MALE.getMyString()) {
			sb.append("SELECT * FROM fnStudentGender (" + Constants.MALE.getMyString() + ") as gender ");
		} else if (myStudentGender == Constants.FEMALE.getMyString()) {
			sb.append("SELECT * FROM fnStudentGender (" + Constants.FEMALE.getMyString() + ") as gender ");
		} else {
			sb.append("SELECT * FROM fnStudentGender (" + Constants.GENDER_NOT_SPECIFIED.getMyString() + ") as gender ");
		}

		if (myStudentResidancy == "On Campus") {
			sb.append("inner join fnOffCampusStudents () as campus" + " on gender.StudentID =  campus.StudentId ");
		} else {
			sb.append("inner join fnStudentsInDorm (" + myDormNumber + ") as campus" + " on gender.StudentID =  campus.StudentId ");
		}

		if (myStudentVehicle == "Has Vehicle") {
			sb.append("inner join fnAllStudentVehicles () as vehicle on gender.StudentId = vehicle.StudentId ");
			sb.append("inner join fnStudentVehiclesDecade(" + myStudentVehicleDecade + ") as decade "
					+ "on vehicle.LicenceNo = decade.LicenceNo ");
		}

		// Grade
		sb.append("inner join fnStudentGrades (" + myStudentGrade + ") as grade"
				+ " on gender.StudentId = grade.StudentId ");

		// BirthYear
		sb.append("inner join fnStudentBornInYear (" + myBirthYear + ") as year"
				+ " on gender.StudentId = year.StudentId ");

		System.out.println(sb.toString());
		
		/**
		 * Executes Query here.
		 */
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;
		try {
			/*
			 * This requires the JDBC SQL driver, available from Microsoft at
			 * https://www.microsoft.com/en-us/download/details.aspx?displaylang
			 * =en&id=11774 Once the download is complete and unzipped, the .jar
			 * file must be added to the project build path through Project >
			 * Properties > Libraries > Add External Jar; You also need to go to
			 * that same window > Order and Export and make sure the box is
			 * checked for that .jar. Then, you also need to go to Run > Run
			 * Configurations > Classpath, select User Defined Entries, and Add
			 * External Jar to add the same jar to the classpath. You must also
			 * go to Run > Run Configurations > Argumets and add the following
			 * as a VM Argument (NOT as a Program Argument!!):
			 * "-Djava.library.path="$PATH\sqljdbc_6.2\enu\auth\x64", where
			 * $PATH is the path to the folder where you unzipped the driver.
			 * Also be sure to point to the right folder for x64 or x86
			 * depending on the driver you used.
			 */

			// we're using Windows authentication so we don't need the username
			// and password
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

	public void setMyStudentResidancy(String myStudentResidancy) {
		this.myStudentResidancy = myStudentResidancy;
	}

	public void setMyStudentVehicle(String myStudentVehicle) {
		this.myStudentVehicle = myStudentVehicle;
	}

	public void setMyStudentGrade(String myStudentGrade) {
		this.myStudentGrade = myStudentGrade;
	}

	public void setMyStudentGender(String myStudentGender) {
		this.myStudentGender = myStudentGender;
	}

	public void setMyBirthYear(String myBirthYear) {
		this.myBirthYear = myBirthYear;
	}

	public void setMyStudentVehicleDecade(String myStudentVehicleDecade) {
		this.myStudentVehicleDecade = myStudentVehicleDecade;
	}

}