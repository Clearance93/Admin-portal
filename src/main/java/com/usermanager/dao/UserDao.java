package com.usermanager.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usermanager.bean.User;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "1!Clearmorumudi";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String InsertUser = "INSERT INTO users" + " ( firstName, lastName, email, country) Values " + " (?,?,?); ";
	private static final String SelectUsers = "SELECT * FROM users";
	private static final String SelectUserById = "SELECT id, firstName, lastName, email country FROM users WHERE id = ?";
	private static final String DeleteUserById = "DELETE FROM users WHERE id = ?";
	private static final String UpdateUserByd = "UPDATE users SET firtsName = ?, lastName = ?, email = ?, country = ? WHERE id = ?";
	
	
	public UserDao() {
	}
	
	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("jdbcDriver");
			con = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		} catch (SQLException e) {
//			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public void insertUser(User user) throws SQLException {
		System.out.println(InsertUser);
		try (Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(InsertUser)){
			preparedStatement.setString(1,  user.getFirstName());
			preparedStatement.setString(2,  user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4,  user.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SelectUserById);){
			preparedStatement.setInt(1,  id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, firstName, lastName, email, country);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	
	public List<User> selectAllUsers(){
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SelectUsers);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firtsName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String  country = rs.getString("country");
				users.add(new User(id, firstName, lastName, email, country));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateUserByd);){
					System.out.println("Updated User: " + preparedStatement);
					preparedStatement.setString(1,user.getFirstName());
					preparedStatement.setString(2, user.getLastName() );
					preparedStatement.setString(3, user.getEmail());
					preparedStatement.setString(4, user.getCountry());
					preparedStatement.setInt(5, user.getId());
					
					rowUpdated = preparedStatement.executeUpdate() > 0;
				}
		return rowUpdated;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DeleteUserById);){
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return  rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException ) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
 			}
		}
	}
	
	
}
