package com.axis.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.axis.db.DBFunction;

public class LoginDetails {
	public static String userName = "";
	public static String userEmail = "";
	public static int customerId ;

	public static void getUserLoginDetails(int customerId) throws SQLException {

		Connection conn = null;

		try {
			conn = DBFunction.connectDB("mysql", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String selectQuery = "select * from customers where CustomerID="+customerId;
		PreparedStatement pstmt = conn.prepareStatement(selectQuery);
		ResultSet rs = pstmt.executeQuery();
		
//
//		java.sql.Statement statement = conn.createStatement();
//		statement.execute(selectQuery);
//		
		if(rs.next()) {
			setUserEmail(rs.getString("Email"));
			setCustomerId(rs.getInt("CustomerID"));
			setUserName(rs.getString("FullName"));
		}
		
//		System.out.println("customerId"+customerId);
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		LoginDetails.userName = userName;
	}

	public static String getUserEmail() {
		return userEmail;
	}

	public static void setUserEmail(String userEmail) {
		LoginDetails.userEmail = userEmail;
	}

	public static int getCustomerId() {
		return customerId;
	}

	public static void setCustomerId(int customerId) {
		LoginDetails.customerId = customerId;
	}
	
	

}
