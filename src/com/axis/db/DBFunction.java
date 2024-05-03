package com.axis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFunction {
	private static Connection connection;
	private Statement statement;
	private PreparedStatement pstatement;
	private ResultSet resultSet;

	public static Connection connectDB(String dbName, String userName, String password) throws SQLException {
		String url = "localhost";
		if (dbName != null) {
			url = "jdbc:mysql://" + url + ":3306/myaxis";
			if (userName == null)
				userName = "root";
			if (password == null)
				password = "root";
		} else {
			System.out.println("DBName cannot be Empty or Null.");
		}
		if (userName != null && password != null) {
			connection = DriverManager.getConnection(url, userName, password);
		}
		return connection;
	}

	public void closeResouces() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (pstatement != null) {
			pstatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}