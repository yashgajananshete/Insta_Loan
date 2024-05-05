package com.axis.main;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.axis.service.DisplayLoanDetails;
import com.axis.service.LoginDetails;
 
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
		System.out.println("\n");
		displayStyle();
		System.out.println("\n**********************       WELCOME TO INSTA LOAN       **********************");
		displayStyle();
		System.out.println("\n");
		
		PreparedStatement pstmt = conn.prepareStatement(selectQuery);
		
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			dbPassword = rs.getString("PasswordHash");
			if ((password != null || password != " ") && password.equals(dbPassword)) {
				System.out.println("\t\tWelcome " + username + " to the Insta Loan Application");
				
				int id = rs.getInt("CustomerID");
			
	            Main.customerId = id;
	            LoginDetails.getUserLoginDetails(id);
	            
				return true;	
			} else {
				System.out.println("--------------------   Invalid Password!!! Please Try Again!!!!   --------------------");
				return false;
			}
		} else {
			System.out.println("--------------------   Invalid Username!!! Please Try Again!!!   --------------------");
			return false;
		}
		
	}
 
	public static void displayStyle() {
		for (int i = 0; i < 80; i++) {
			System.out.print("*");
		}
	}
 
}
 