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
	
		int customer_id=1;
		String selectQuery = "select * from loans where CustomerID=?";
		PreparedStatement pstmt = conn.prepareStatement(selectQuery);
		pstmt.setInt(1, customer_id);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			System.out.println("\nLoan Id: "+rs.getInt(1)+"\nLoan Type: "+rs.getString(9)+
					"\nLoan Amount: "+rs.getLong(3)+"\nInterest Rate: "+rs.getLong(4)+
					"\nStart Date: "+rs.getInt(5)+"\nEnd Date: "+rs.getInt(6)+"\nMonthly payment: "+rs.getLong(7));
		}

	}
}
