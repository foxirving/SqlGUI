package gui;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import datamodel.Constants;
import datamodel.DataModel;

public class MainPanel extends JPanel {

	/**
	 * Generated Serial Version.
	 */
	private static final long serialVersionUID = 2949687444255909471L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setBackground(Color.ORANGE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/** --------General Panel-------------------- */

		JPanel panelGeneralInfo = new JPanel();
		panelGeneralInfo.add(new JLabel("Database Used: " + DataModel.databaseName));
		panelGeneralInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		add(panelGeneralInfo);

		/** -----------Student Panel------------------- */

		JPanel panelStudent = new JPanel();
		panelStudent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelStudent.setLayout(new BoxLayout(panelStudent, BoxLayout.Y_AXIS));
		panelStudent.add(new JLabel("Search by Student Information: "));
		add(panelStudent);

		JPanel pInfo1 = new JPanel();
		panelStudent.add(pInfo1);
		pInfo1.add(new JLabel("Student First Name: "));
		JTextField tfStuFName = new JTextField();
		pInfo1.add(tfStuFName);
		tfStuFName.setColumns(10);
		
		JPanel pInfo2 = new JPanel();
		panelStudent.add(pInfo2);
		pInfo2.add(new JLabel("Student Middle Name: "));
		JTextField tfStuMName = new JTextField();
		pInfo2.add(tfStuMName);
		tfStuMName.setColumns(10);
		
		JPanel pInfo3 = new JPanel();
		panelStudent.add(pInfo3);
		pInfo3.add(new JLabel("Student Last Name: "));
		JTextField tfStuLName = new JTextField();
		pInfo3.add(tfStuLName);
		tfStuLName.setColumns(10);
		
		JPanel pInfo4 = new JPanel();
		panelStudent.add(pInfo4);
		pInfo4.add(new JLabel("Student Birth Year: "));
		JTextField tfStuBirth = new JTextField();
		pInfo4.add(tfStuBirth);
		tfStuBirth.setColumns(10);

		JPanel pInfo5 = new JPanel();
		panelStudent.add(pInfo5);
		pInfo5.add(new JLabel("Student Gender: "));
		JCheckBox maleButton = new JCheckBox(Constants.MALE.getMyString());
		maleButton.setSelected(true);
		pInfo5.add(maleButton);
		JCheckBox femaleButton = new JCheckBox(Constants.FEMALE.getMyString());
		femaleButton.setSelected(true);
		pInfo5.add(femaleButton);
		JCheckBox nbButton = new JCheckBox("Gender Not Specified");
		nbButton.setSelected(true);
		pInfo5.add(nbButton);
		
		JPanel pInfo6 = new JPanel();
		panelStudent.add(pInfo6);
		pInfo6.add(new JLabel("Residancy: "));
		JCheckBox isOnCampusButton = new JCheckBox("On campus");
		isOnCampusButton.setSelected(true);
		pInfo6.add(isOnCampusButton);
		JCheckBox isNotOnCampusButton = new JCheckBox("Off campus");
		isNotOnCampusButton.setSelected(true);
		pInfo6.add(isNotOnCampusButton);
	
		/** -----------Vehicle Panel------------------- */

		JPanel panelVehicle = new JPanel();
		panelVehicle.setBorder(BorderFactory.createLineBorder(Color.black));
		panelVehicle.setLayout(new BoxLayout(panelVehicle, BoxLayout.Y_AXIS));
		panelVehicle.add(new JLabel("Search by Vehicle Information: "));
		add(panelVehicle);
		
		JPanel pInfo12 = new JPanel();
		panelVehicle.add(pInfo12);
		JCheckBox hasVehicle = new JCheckBox("Has a vehicle");
		hasVehicle.setSelected(true);
		pInfo12.add(hasVehicle);
		JCheckBox notHasVehicle = new JCheckBox("Does not have a vehicle");
		notHasVehicle.setSelected(true);
		pInfo12.add(notHasVehicle);
		
		JPanel pInfo14 = new JPanel();
		panelVehicle.add(pInfo14);
		pInfo14.add(new JLabel("Vehicle Licence Plate: "));
		JTextField tfVehicleLicence = new JTextField();
		pInfo14.add(tfVehicleLicence);
		tfVehicleLicence.setColumns(10);
		
		JPanel pInfo15 = new JPanel();
		panelVehicle.add(pInfo15);
		pInfo15.add(new JLabel("Vehicle Licence Make: "));
		JTextField tfVehicleMake = new JTextField();
		pInfo15.add(tfVehicleMake);
		tfVehicleMake.setColumns(10);
		
		JPanel pInfo16 = new JPanel();
		panelVehicle.add(pInfo16);
		pInfo16.add(new JLabel("Vehicle Licence Model: "));
		JTextField tfVehicleModel = new JTextField();
		pInfo16.add(tfVehicleModel);
		tfVehicleModel.setColumns(10);
		
		JPanel pInfo17 = new JPanel();
		panelVehicle.add(pInfo17);
		pInfo17.add(new JLabel("Vehicle Licence Color: "));
		JTextField tfVehicleColor = new JTextField();
		pInfo17.add(tfVehicleColor);
		tfVehicleColor.setColumns(10);
		
		
		
		
		/** -------------Instructor Panel----------------- */

		JPanel panelInstructor = new JPanel();
		panelInstructor.setBorder(BorderFactory.createLineBorder(Color.black));
		panelInstructor.setLayout(new BoxLayout(panelInstructor, BoxLayout.Y_AXIS));
		panelInstructor.add(new JLabel("Search by Instructor Information: "));
		add(panelInstructor);

		JPanel pInfo7 = new JPanel();
		panelInstructor.add(pInfo7);
		pInfo7.add(new JLabel("Instructors First Name: "));
		JTextField ftInstFName = new JTextField();
		pInfo7.add(ftInstFName);
		ftInstFName.setColumns(10);
		
		JPanel pInfo8 = new JPanel();
		panelInstructor.add(pInfo8);
		pInfo8.add(new JLabel("Instructors Last Name: "));
		JTextField ftInstLName = new JTextField();
		pInfo8.add(ftInstLName);
		ftInstLName.setColumns(10);
		
		JPanel pInfo9 = new JPanel();
		panelInstructor.add(pInfo9);
		pInfo9.add(new JLabel("Instructors Pnone Number: "));
		JTextField ftInstPhoneNumb = new JTextField();
		pInfo9.add(ftInstPhoneNumb);
		ftInstPhoneNumb.setColumns(10);

		
		/** ---------------Course Panel----------------- */
		JPanel panelCourse = new JPanel();
		panelCourse.setBorder(BorderFactory.createLineBorder(Color.black));
		panelCourse.setLayout(new BoxLayout(panelCourse, BoxLayout.Y_AXIS));
		panelCourse.add(new JLabel("Search by Course Information: "));
		add(panelCourse);
		
		JPanel pInfo10 = new JPanel();
		panelCourse.add(pInfo10);
		pInfo10.add(new JLabel("Course Name: "));
		JTextField tfCourseName = new JTextField();
		pInfo10.add(tfCourseName);
		tfCourseName.setColumns(10);
		
		JPanel pInfo11 = new JPanel();
		panelCourse.add(pInfo11);
		pInfo11.add(new JLabel("Course Instructor: "));
		JTextField tfCorseInst = new JTextField();
		pInfo11.add(tfCorseInst);
		tfCorseInst.setColumns(10);

		/** ---------Display Panel-------------------- */

		JPanel panelDisplay = new JPanel();
		add(panelDisplay);

		panelDisplay.setLayout(new BoxLayout(panelDisplay, BoxLayout.Y_AXIS));

		JButton btnStart = new JButton("Search Database");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DataModel.INSTANCE.setMyFirstName(tfStuFName.getText());
				DataModel.INSTANCE.setMyMiddleName(tfStuMName.getText());
				DataModel.INSTANCE.setMyLastName(tfStuLName.getText());
				
				DataModel.INSTANCE.setMyBirthYear(tfStuBirth.getText());
				
				DataModel.INSTANCE.setMale(maleButton.isSelected());
				DataModel.INSTANCE.setFemale(femaleButton.isSelected());
				DataModel.INSTANCE.setGenderNotSpecified(nbButton.isSelected());
				
				DataModel.INSTANCE.setOnCampus(isOnCampusButton.isSelected());
				DataModel.INSTANCE.setNotOnCampus(isNotOnCampusButton.isSelected());
				
				
			
				
				try {
					ResultSet rs = DataModel.INSTANCE.getTable();
					JTable table = new JTable(DisplayData.buildTableModel(rs));
					JOptionPane.showMessageDialog(null, new JScrollPane(table));
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panelDisplay.add(btnStart);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		panelDisplay.add(textPane);

	}
}
