package com.axis.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;

public class UpdateDetails {
	public void userUpdate() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt2 = null;
        try {
            conn = DBFunction.connectDB("mysql", null, null);
            Scanner sc = new Scanner(System.in);
            System.out.println("\nUPDATE User Details:");
            System.out.println("[1] Full Name");
            System.out.println("[2] Email");
            System.out.println("[3] Phone");
            System.out.println("[4] Address");
            System.out.print("Enter your choice (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
            
            int customerID = LoginDetails.getCustomerId();
            switch (choice) {
                case 1:
                    System.out.print("\nEnter Updated Full Name --> ");
                    String newFullName = sc.nextLine();              
                    String updateQuery1 = "UPDATE Customers SET FullName=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQuery1);
                    pstmt2.setString(1, newFullName);
                    pstmt2.setInt(2, customerID);
                    break;
                case 2:
                    System.out.print("\nEnter Updated Email -->");
                    String newEmail = sc.nextLine();
                    // Consume newline character
                    String updateQueryEmail = "UPDATE Customers SET Email=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQueryEmail);
                    pstmt2.setString(1, newEmail);
                    pstmt2.setInt(2, customerID);
                    break;

                case 3:
                    System.out.print("Enter Updated mobile Number -->");
                    String newPhone = sc.nextLine();
                    // Consume newline character
                    String updateQueryMobile = "UPDATE Customers SET Phone=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQueryMobile);
                    pstmt2.setString(1, newPhone);
                    pstmt2.setInt(2, customerID);
                    break;

                case 4:
                    System.out.print("Enter Updated Address --> ");
                    String newAddress = sc.nextLine();
                    // Consume newline character
                    String updateQueryAddress = "UPDATE Customers SET Address=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQueryAddress);
                    pstmt2.setString(1, newAddress);
                    pstmt2.setInt(2, customerID);
                    break;
            }

            int updateStatus = pstmt2.executeUpdate();
            if (updateStatus > 0) {
                System.out.println("--------------- Record Updated Successfully ---------------");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (conn != null) {
                conn.close();
            }


}}

}
