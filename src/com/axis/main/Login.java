package com.axis.main;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.axis.service.DisplayLoanDetails;
 
public class Login {
	DisplayLoanDetails home = new DisplayLoanDetails();
 
	public static boolean CheckLogiin(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String dbPassword = null;
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		try {
			conn = DBFunction.connectDB("mysql", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String selectQuery = "select * from account where Username=?";
 
		displayStyle();
		System.out.println("\n**********************       Welcome to Insta Loan        **********************");
		displayStyle();
		
		PreparedStatement pstmt = conn.prepareStatement(selectQuery);
		
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			dbPassword = rs.getString("PasswordHash");
			System.out.println(dbPassword);
			System.out.println(password);
		} else {
			System.out.println("Invalid username!!! Pls Try Again!!!");
			System.exit(0);
		}
		if ((password != null || password != " ") && password.equals(dbPassword)) {
			System.out.println("Welcome " + username + " to the Insta Loan Application");
			return true;	
		} else {
			System.out.println("Invalid Password!!! Pls Try Again!!!!");
			return false;
		}
	}
 
	public static void displayStyle() {
		for (int i = 0; i < 80; i++) {
			System.out.print("*");
		}
	}
 
}
 