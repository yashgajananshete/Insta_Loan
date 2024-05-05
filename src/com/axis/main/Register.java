package com.axis.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class Register {

	public void CustomerDetails() {
		try {

			int Customerid = 0;
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter Username: ");
			String CusUsername = sc.next();
			System.out.print("Enter Password: ");
			String CusPassword = sc.next();

			Connection conn = null;

			conn = DBFunction.connectDB("mysql", "root", "root");

			String checkUsernameExistQuery = "select * from account where Username=?";
			PreparedStatement pst = conn.prepareStatement(checkUsernameExistQuery);

			pst.setString(1, CusUsername);

			ResultSet r = pst.executeQuery();

			if (r.next()) {
				System.out.println("\n-------------------------   UserName Already Exist   -------------------------");
			} else {
				System.out.print("Enter Your Name : ");
				String name = sc.next();

				System.out.print("Enter DOB (yyyy-dd-MM): ");
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

				pstmt.executeUpdate();

				String selectCustomerId = "select CustomerID from customers where Email=?";

				PreparedStatement pstmt1 = conn.prepareStatement(selectCustomerId);
				pstmt1.setString(1, email);
				ResultSet rs = pstmt1.executeQuery();

				if (rs.next()) {
					Customerid = rs.getInt("CustomerID");
				}

				String insertAccountQuery = "INSERT INTO account (CustomerID, Username, PasswordHash) VALUES (?, ?, ?);";

				PreparedStatement pstmt2 = conn.prepareStatement(insertAccountQuery);

				pstmt2.setInt(1, Customerid);
				pstmt2.setString(2, CusUsername);
				pstmt2.setString(3, CusPassword);

				pstmt2.executeUpdate();

				System.out.println("\n-------------------------  Registration Successfull  -------------------------");
			}
			
		} catch (Exception e) {
			System.out.println("------------Something Went Wrong: "+ e.getMessage());
			
		}
	}

}
