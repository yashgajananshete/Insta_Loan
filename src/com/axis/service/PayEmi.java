package com.axis.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.mysql.cj.xdevapi.Statement;

public class PayEmi {
	
	public void payEmiForTheLoan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Paying the emi for the loan");
        System.out.print("Enter the amount: ");
        int amount = scanner.nextInt();
        insertDataForThePayment(userId, amount, userEmail);
        System.out.println("EMI successfully paid the amount of " + amount + " for the UserId " + userId + ", UserName " + userName + ", userEmail " + userEmail);
        scanner.close();
        System.exit(0);
    }

    public static void insertDataForThePayment(int userId, int amount, String userEmail) {
    	
        try {
            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS emi_payment (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "userId INT," +
                    "amount DECIMAL(10, 2)," +
                    "user_email VARCHAR(255)," +
                    "status VARCHAR(50))";
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            // Insert data
            String insertDataSQL = "INSERT INTO emi_payment (userId, amount, user_email, status) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertDataSQL);

            insertStatement.setInt(1, userId);
            insertStatement.setDouble(2, amount);
            insertStatement.setString(3, userEmail);
            insertStatement.setString(4, "success");
            insertStatement.executeUpdate();
            insertStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
