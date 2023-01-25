package com.quest.student.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.quest.student.config.DatabaseConfig;
import com.quest.student.interfaces.CrudRepository;
import com.quest.student.models.Student;

public class StudentJdbcRepository implements CrudRepository {
	
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(DatabaseConfig.DRIVER_URL);
		return DriverManager.getConnection(DatabaseConfig.CONNECTION_URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);

	}
	

	@Override
	public void add(Student student) {

		String sql = "INSERT INTO students_application_tb(name,address,bloodgroup) VALUES(?,?,?)";

		try {

			Connection connection = getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getAddress());
			ps.setString(3, student.getBloodGroup());

			int noOfRowsAffected = ps.executeUpdate();

			if (noOfRowsAffected > 0) {
				System.out.println("Student Added Sucessfully!");
			} else {
				System.out.println("Oops! Something Went Wrong");
			}

			ps.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Student student) {

		String sql = "UPDATE students_application_tb SET name = ?,address = ?,bloodgroup = ? WHERE id = ?";

		try {

			Connection connection = getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);			
			ps.setString(1, student.getName());
			ps.setString(2, student.getAddress());
			ps.setString(3, student.getBloodGroup());
			ps.setInt(4, student.getId());


			int noOfRowsAffected = ps.executeUpdate();

			if (noOfRowsAffected > 0) {
				System.out.println("Student Updated Sucessfully!");
			} else {
				System.out.println("Oops! Something Went Wrong");
			}

			ps.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM students_application_tb WHERE id = ?";

		try {

			Connection connection = getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);			
			ps.setInt(1, id);

			int noOfRowsAffected = ps.executeUpdate();

			if (noOfRowsAffected > 0) {
				System.out.println("Student Deleted Sucessfully!");
			} else {
				System.out.println("Oops! Something Went Wrong");
			}

			ps.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public Student[] getAll() {
		
		ArrayList<Student> students = new ArrayList<>();

		String sql = "SELECT * FROM students_application_tb";

		try {

			Connection connection = getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Student student = new Student();

				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAddress(rs.getString("address"));
				student.setBloodGroup(rs.getString("bloodgroup"));
				
				students.add(student);

			}

			rs.close();
			ps.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students.toArray(new Student[students.size()]);
	}

	@Override
	public Student getById(int id) {

		Student student = null;

		String sql = "SELECT * FROM students_application_tb WHERE id = ?";

		try {

			Connection connection = getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				student = new Student();

				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAddress(rs.getString("address"));
				student.setBloodGroup(rs.getString("bloodgroup"));

			}

			rs.close();
			ps.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	// TODO NOT USED HERE
	public void setLength(int length) {
		System.out.println("Not Applicable in JDBC Scenario");
	}

	public int getPosition() {
		System.out.println("Not Applicable in JDBC Scenario");
		return -1;
	}

	public int getLength() {
		
		int length = 0;

		String sql = "SELECT COUNT(*) as no_of_records FROM students_application_tb";

		try {

			Connection connection = getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				length = rs.getInt("no_of_records");

			}

			rs.close();
			ps.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return length;
	}

}
