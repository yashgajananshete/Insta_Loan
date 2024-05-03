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
            System.out.println("UPDATE User Details\n " +
                    "1.FullName\n" + // Added missing newline character
                    "2.Email\n" + // Added missing newline character
                    "3.Phone\n" + // Added missing newline character
                    "4.Address"); // Added missing newline character
            System.out.println("Enter the choice");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter Updated Full Name");
                    String newFullName = sc.nextLine();
                    System.out.println("Enter your customerID");
                    int customerID = sc.nextInt();
                    String updateQuery1 = "UPDATE Customers SET FullName=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQuery1);
                    pstmt2.setString(1, newFullName);
                    pstmt2.setInt(2, customerID);
                    break;
                case 2:
                    System.out.println("Enter Updated Email");
                    String newEmail = sc.nextLine();
                    System.out.println("Enter your customerID");
                    int CustomerIDEmail = sc.nextInt();
                    sc.nextLine(); // Consume newline character
                    String updateQueryEmail = "UPDATE Customers SET Email=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQueryEmail);
                    pstmt2.setString(1, newEmail);
                    pstmt2.setInt(2, CustomerIDEmail);
                    break;

                case 3:
                    System.out.println("Enter Updated mobile Number");
                    String newPhone = sc.nextLine();
                    System.out.println("Enter your customerID");
                    int CustomerIDPhone = sc.nextInt();
                    sc.nextLine(); // Consume newline character
                    String updateQueryMobile = "UPDATE Customers SET Phone=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQueryMobile);
                    pstmt2.setString(1, newPhone);
                    pstmt2.setInt(2, CustomerIDPhone);
                    break;

                case 4:
                    System.out.println("Enter Updated Address");
                    String newAddress = sc.nextLine();
                    System.out.println("Enter your customerID");
                    int CustomerIDAddress = sc.nextInt();
                    sc.nextLine(); // Consume newline character
                    String updateQueryAddress = "UPDATE Customers SET Address=? WHERE CustomerID=?";
                    pstmt2 = conn.prepareStatement(updateQueryAddress);
                    pstmt2.setString(1, newAddress);
                    pstmt2.setInt(2, CustomerIDAddress);
                    break;
            }

            int updateStatus = pstmt2.executeUpdate();
            if (updateStatus > 0) {
                System.out.println(updateStatus + " Record Updated Successfully");
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
