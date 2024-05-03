package com.axis.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;

public class ApplyForNewLoan {
	    public void applyNewLoan() throws SQLException {
	    	
	    	
	        Scanner scan = new Scanner(System.in);
	        System.out.println("Please Enter details For New Loan: ");
//			System.out.println("* Field are Mandatory");
	        int choice;
	        System.out.println("\t\t\t 1. Apply for Personal Loan ");
	        System.out.println("\t\t\t 2. Apply for a Business Loan ");
	        System.out.println("\t\t\t 3. Apply for a Gold Loan ");
	        System.out.println("\t\t\t 4. Apply for a Home Loan");
	        System.out.println("\t\t\t 5. Apply for a Car Loan");
	        System.out.println("\t\t\t 6. Apply for a Education Loan");
	        System.out.println("\t\t\t 7. Exit ");
	        System.out.print(" Please enter the choice of loan that you want:");

	        choice = scan.nextInt();
	        switch (choice) {
	            case 1:
	                System.out.println(" Details for Personal Loan ");
	                applyLoan();
	                break;
	            case 2:
	                System.out.println(" Details for Business Loan ");
	                applyLoan();
	                break;
	            case 3:
	                System.out.println(" Details for Gold Loan ");
	                applyLoan();
	                break;
	            case 4:
	                System.out.println(" Details for Home Loan ");
	                applyLoan();
	                break;
	            case 5:
	                System.out.println(" Details for Car Loan ");
	                applyLoan();
	                break;
	            case 6:
	                System.out.println(" Details for Education Loan ");
	                applyLoan();
	                break;
	            case 7:
	                System.out.println(" Exiting.....");
	                break;
	            default:
	                System.out.println("Invalid choice! Please select again.");
	        }
	    }


	    public static void applyLoan() throws SQLException {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter the  loan amount : ");

	        int loanAmount = sc.nextInt();
	        System.out.print("Enter the loan tenure : ");
	        int loanTenure = sc.nextInt();
	        

	        Connection conn = DBFunction.connectDB("mysql", null, null);
	        String query1 ="insert into new_loan values(?,?)";
			PreparedStatement pst=conn.prepareStatement(query1);
			
			int status=0;
			pst.setInt(1, loanAmount);
			pst.setInt(2, loanTenure);
			
			
			
			status=pst.executeUpdate();
			if(status>0) {
				System.out.println(status+"records added");
			}

	        System.out.println("Please confirm your Application");
	        System.out.println("Press enter true for Confirm application : ");
	        boolean b = sc.nextBoolean();
	        if (b == true) {
	            System.out.println("Congratulation you have applied fo the loan");
	        }
	    }
	
	
}
