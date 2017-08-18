package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/** --------General Panel-------------------- */

		JPanel panelGeneralInfo = new JPanel();
		panelGeneralInfo.add(new JLabel("Database Used: StudentRecords"));
		panelGeneralInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGeneralInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(panelGeneralInfo);

		/** -----------Student Panel------------------- */

		JPanel panelStudent = new JPanel();
		panelStudent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelStudent.setLayout(new BoxLayout(panelStudent, BoxLayout.Y_AXIS));
		panelStudent.add(new JLabel("Search by Student Information: "));
		add(panelStudent);

		JPanel pInfo4 = new JPanel();
		panelStudent.add(pInfo4);
		pInfo4.add(new JLabel("Student Birth Year: "));
		String[] birthDecade = new String[100];
		for (int i = 0; i < 100; i++) {
			birthDecade[i] = String.valueOf(2017 - i);
 		}
		JComboBox birthDecadeBox = new JComboBox(birthDecade);
		birthDecadeBox.setSelectedIndex(0);
		birthDecadeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) birthDecadeBox.getSelectedItem();
				DataModel.INSTANCE.setMyBirthYear(s);
			}
			
		});
		pInfo4.add(birthDecadeBox);

		JPanel pInfo5 = new JPanel();
		panelStudent.add(pInfo5);
		pInfo5.add(new JLabel("Student Gender: "));
		String[] studentGender = { Constants.MALE.getMyString(), Constants.FEMALE.getMyString(), Constants.NOT_SPECIFIED.getMyString() };
		JComboBox studentGenderBox = new JComboBox(studentGender);
		studentGenderBox.setSelectedIndex(0);
		studentGenderBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) studentGenderBox.getSelectedItem();
				DataModel.INSTANCE.setMyStudentGender(s);
			}
			
		});
		pInfo5.add(studentGenderBox);
		
		JPanel pInfo9 = new JPanel();
		panelStudent.add(pInfo9);
		pInfo9.add(new JLabel("Student Grade: "));
		String[] studentGrade = new String[40];
		for (int i = 0; i < 40; i++) {
			studentGrade[i] = String.valueOf(0.0 + (i*0.1)).substring(0, 3);
		}
		JComboBox studentGradeBox = new JComboBox(studentGrade);
		studentGradeBox.setSelectedIndex(0);
		studentGradeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) studentGradeBox.getSelectedItem();
				DataModel.INSTANCE.setMyStudentGrade(s);
			}
			
		});
		pInfo9.add(studentGradeBox);

		JPanel pInfo6 = new JPanel();
		panelStudent.add(pInfo6);
		pInfo6.add(new JLabel("Residency: "));
		String[] studentResidancy = {"On Campus", "Off Campus"};
		JComboBox studentResidancyBox = new JComboBox(studentResidancy);
		studentResidancyBox.setSelectedIndex(0);
		studentResidancyBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) studentResidancyBox.getSelectedItem();
				DataModel.INSTANCE.setMyStudentResidancy(s);
			}
			
		});
		pInfo6.add(studentResidancyBox);

		/** -----------Vehicle Panel------------------- */

		JPanel panelVehicle = new JPanel();
		panelVehicle.setBorder(BorderFactory.createLineBorder(Color.black));
		panelVehicle.setLayout(new BoxLayout(panelVehicle, BoxLayout.Y_AXIS));
		panelVehicle.add(new JLabel("Search by Vehicle Information: "));
		add(panelVehicle);

		JPanel pInfo12 = new JPanel();
		panelVehicle.add(pInfo12);
		JCheckBox hasVehicle = new JCheckBox("Has a vehicle");
		String[] studentVehicle = {"Has Vehicle", "Does Not Have Vehicle"};
		JComboBox studentVehicleBox = new JComboBox(studentVehicle);
		studentVehicleBox.setSelectedIndex(0);
		studentVehicleBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) studentVehicleBox.getSelectedItem();
				DataModel.INSTANCE.setMyStudentVehicle(s);
			}
			
		});
		pInfo12.add(studentVehicleBox);

		JPanel pInfo13 = new JPanel();
		panelVehicle.add(pInfo13);
		pInfo13.add(new JLabel("Search by Vehicle Decade: "));
		String[] vehicleDecade = { "2010", "2000", "1990", "1980", "1970", "1960", "1950" };
		JComboBox vehicleDecadeBox = new JComboBox(vehicleDecade);
		vehicleDecadeBox.setSelectedIndex(0);
		vehicleDecadeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) vehicleDecadeBox.getSelectedItem();
				DataModel.INSTANCE.setMyStudentVehicleDecade(s);
			}
			
		});
		pInfo13.add(vehicleDecadeBox);


		/** ---------Display Panel-------------------- */

		JPanel panelDisplay = new JPanel();
		add(panelDisplay);

		panelDisplay.setLayout(new BoxLayout(panelDisplay, BoxLayout.Y_AXIS));

		JButton btnStart = new JButton("Search Database");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

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

	}
}
