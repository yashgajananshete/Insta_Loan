package com.axis.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.axis.main.Main;
import com.mysql.cj.xdevapi.Statement;

public class PayEmi {
	public void payEmiForTheLoan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Paying the emi for the loan");
        System.out.print("Enter the amount: ");
        int amount = scanner.nextInt();
                
        insertDataForThePayment(Main.customerId, amount, Main.email);
        System.out.println("EMI successfully paid the amount of " + amount + " for the UserId " + Main.customerId + ", UserName " + Main.username + ", userEmail " + Main.email);
    }

    public static void insertDataForThePayment(int userId, int amount, String userEmail) {
    	Connection conn = null;
		try {
			conn = DBFunction.connectDB("mysql", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS emi_payment (" +
                    "payment_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "customerId INT," +
                    "amount DECIMAL(10, 2)," +
                    "user_email VARCHAR(255)," +
                    "status VARCHAR(50)," +
                    "payment_datetime DATETIME" + // Add datetime column here
                    ")";

            java.sql.Statement statement = conn.createStatement();
            statement.execute(createTableSQL);
            // Insert data
            String insertDataSQL = "INSERT INTO emi_payment (customerId, amount, user_email, status,payment_datetime) VALUES (?, ?, ?, ?,?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertDataSQL);

            insertStatement.setInt(1, userId);
            insertStatement.setDouble(2, amount);
            insertStatement.setString(3, userEmail);
            insertStatement.setString(4, "success");
            insertStatement.setString(5, String.valueOf(LocalDateTime.now()));

            insertStatement.executeUpdate();
            insertStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
//	public void payEmiForTheLoan() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Paying the emi for the loan");
//        System.out.print("Enter Customer Id: ");
//        int cusId = sc.nextInt();
//        System.out.print("Enter the amount: ");
//        int amount = sc.nextInt();
//        
//        insertDataForThePayment(cusId, amount);
//        
//        sc.close();
//        System.exit(0);
//    }
//
//    public static void insertDataForThePayment(int userId, int amount) {
//    	Connection conn = null;
//		try {
//			conn = DBFunction.connectDB("mysql", "root", "root");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//        try {
//            // Create table
//            String createTableSQL = "CREATE TABLE IF NOT EXISTS emi_payment (" +
//                    "id INT AUTO_INCREMENT PRIMARY KEY," +
//                    "userId INT," +
//                    "amount DECIMAL(10, 2)," +
//                    "user_email VARCHAR(255)," +
//                    "status VARCHAR(50))";
//            java.sql.Statement statement =  conn.createStatement();
//            statement.execute(createTableSQL);
//            
////            System.out.println("hello111");
//            String getData= "select * from customers where CustomerID = ?";
//            PreparedStatement selectStatement = conn.prepareStatement(getData);
//            selectStatement.setInt(1, userId);
//    		ResultSet rs = selectStatement.executeQuery();
//    		
//    		String email = null;
//    		String cusName = null;
//    		
////    		System.out.println("hello");
//    		if(rs.next()) {
//    			 email = rs.getString(8);
//        		 cusName = rs.getString(2);
//        		
//    		}
////    		System.out.println(email+"   "+cusName);
//    		
//                       
//            
//            // Insert data
//            String insertDataSQL = "INSERT INTO emi_payment (userId, amount, user_email, status) VALUES (?, ?, ?, ?)";
//            PreparedStatement insertStatement = conn.prepareStatement(insertDataSQL);
//            insertStatement.setInt(1, userId);
//            insertStatement.setDouble(2, amount);
//            insertStatement.setString(3, email);
//            insertStatement.setString(4, "success");
//            insertStatement.executeUpdate();
//            insertStatement.close();
//                                  
//            System.out.println("EMI successfully paid the amount of " + amount + " for the UserId " + userId + ", UserName " + cusName );
//                       
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
