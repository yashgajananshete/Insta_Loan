package com.axis.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;

public class Register {
	
	public void CustomerDetails() throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Username: ");
		String CusUsername = sc.next();
		System.out.print("Enter Password: ");
		String CusPassword = sc.next();
		
		Connection conn = null;
		try {
			conn = DBFunction.connectDB("mysql", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Enter Your Name : ");
		String name = sc.next();
		
		System.out.print("Enter DOB : ");
		String dob = sc.next();
		
		System.out.print("Enter Email : ");
		String email = sc.next();
		
		System.out.print("Enter Mobile Number: ");
		long mobile = sc.nextLong();
		
		System.out.print("Enter Pan Number : ");
		String pan = sc.next();
		
		PreparedStatement pstmt = null;
        String insertQuery = "INSERT INTO customers (FullName, DateOfBirth, Email, Phone, PanNumber) VALUES (?, ?, ?, ?, ?);";
        pstmt = conn.prepareStatement(insertQuery);
        pstmt.setString(1, name);
        pstmt.setString(2, dob);
        pstmt.setString(3, email);
        pstmt.setLong(4, mobile);
        pstmt.setString(5, pan);
       
        int insertStatus =0;	
        
		insertStatus = pstmt.executeUpdate();
		if(insertStatus>0) {
			System.out.println(insertStatus+"Record Inserted Successfully");
		}
		
	}

}
