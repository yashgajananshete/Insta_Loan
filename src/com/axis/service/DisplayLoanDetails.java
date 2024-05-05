package com.axis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.axis.db.DBFunction;

public class DisplayLoanDetails {
	
	public static void Display() throws SQLException{
		
		Connection conn = null;
		try {
			conn = DBFunction.connectDB("mysql", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		int customer_id=LoginDetails.getCustomerId();
//		System.out.println(LoginDetails.userEmail + " ffff"+ LoginDetails.userName);
		
//		System.out.println("CUstomerID From Diaplsy"+customer_id);
		String selectQuery = "select * from loans where CustomerID=?";
		PreparedStatement pstmt = conn.prepareStatement(selectQuery);
		pstmt.setInt(1, customer_id);
		ResultSet rs = pstmt.executeQuery();

		if(rs.next()) {
			while(rs.next()) {
//				System.out.println("\nLoan Id: "+rs.getInt(1)+"\nLoan Type: "+rs.getString(9)+
//						"\nLoan Amount: "+rs.getLong(3)+"\nInterest Rate: "+rs.getLong(4)+
//						"\nStart Date: "+rs.getInt(5)+"\nEnd Date: "+rs.getInt(6)+"\nMonthly payment: "+rs.getLong(7));
				System.out.println();			
				displayStyle();
				System.out.printf("\n\n\t%-10s %-15s %-15s %-15s %-15s %-15s %-20s %n", "Loan Id", "Loan Type", "Loan Amount", "Interest Rate", "Start Date", "End Date", "Monthly Payment");
				System.out.printf("\n\t%-10d %-15s %-15d %-15d %-15d %-15d %-20d %n", rs.getInt(1), rs.getString(9), rs.getLong(3), rs.getLong(4), rs.getInt(5), rs.getInt(6), rs.getLong(7));
				System.out.println();
				displayStyle();
				System.out.println();
			}
		}
		else {
			 System.out.println("----------------------  You have not applied for any loan  ----------------------");
		}

	}
	public static void displayStyle() {
		for (int i = 0; i < 120; i++) {
			System.out.print("*");
		}
	}
}
