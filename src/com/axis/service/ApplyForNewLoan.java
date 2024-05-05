package com.axis.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.axis.db.DBFunction;

public class ApplyForNewLoan {
	public void applyNewLoan() throws SQLException {

		String loanType;
		float interest;
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
			interest = 11;
			loanType = "Personal Loan";
			applyLoan(interest, loanType);
			break;
		case 2:
			System.out.println(" Details for Business Loan ");
			interest = 12;
			loanType = "Business Loan";
			applyLoan(interest, loanType);
			break;
		case 3:
			System.out.println(" Details for Gold Loan ");
			interest = 10;
			loanType = "Gold Loan";
			applyLoan(interest, loanType);
			break;
		case 4:
			System.out.println(" Details for Home Loan ");
			interest = 9;
			loanType = "Home Loan";
			applyLoan(interest, loanType);
			break;
		case 5:
			System.out.println(" Details for Car Loan ");
			interest = 8.5f;
			loanType = "Car Loan";
			applyLoan(interest, loanType);
			break;
		case 6:
			System.out.println(" Details for Education Loan ");
			interest = 7;
			loanType = "Education Loan";
			applyLoan(interest, loanType);
			break;
		case 7:
			System.out.println(" Exiting.....");
			break;
		default:
			System.out.println("Invalid choice! Please select again.");
		}
	}

	public static void applyLoan(float interest, String loanType) throws SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the  loan amount : ");
		double loanAmount = sc.nextDouble();

		System.out.print("Enter the loan tenure : ");
		double loanTenure = sc.nextDouble();

		LocalDate startDateTest = LocalDate.now();

		LocalDate endDateTest = startDateTest.plusYears((long) loanTenure);

		// Format the date if needed
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String endDate = endDateTest.format(formatter);
		String startDate = startDateTest.format(formatter);

		double emi = EmiCalculator(loanAmount, loanTenure, interest);

		// Calculate total payment
		double totalPayment = emi * loanTenure * 12;

		System.out.println("\nPlease confirm your Application");
		System.out.println("Press enter 1 for Confirm application : ");
		int b = sc.nextInt();

		if (b == 1) {

			Connection conn = DBFunction.connectDB("mysql", null, null);
			String query1 = "INSERT INTO loans (CustomerID, LoanAmount, InterestRate, StartDate, EndDate, "
					+ "MonthlyPayment, loanTypeName, TotalPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = conn.prepareStatement(query1);

			int status = 0;
			int customerId = LoginDetails.getCustomerId();
			pst.setInt(1, customerId);
			pst.setDouble(2, loanAmount);
			pst.setFloat(3, interest);
			pst.setString(4, startDate);
			pst.setString(5, endDate);
			pst.setDouble(6, emi);
			pst.setString(7, loanType);
			pst.setDouble(8, totalPayment);

			pst.executeUpdate();
			
			System.out.println("Congratulation you have applied fo the loan");
		} else {
			System.out.println(
					"You have chosen to cancel the loan application. If this was a mistake, please select 'Reapply' to start the process again.");
		}
	}

	public static double EmiCalculator(double loanAmount, double tenure, double annualRate) {

		double monthlyRate, emi;

		// Convert annual rate to monthly and tenure to months
		monthlyRate = annualRate / (12 * 100);
		tenure = tenure * 12;

		// Calculate EMI
		emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenure)) / (Math.pow(1 + monthlyRate, tenure) - 1);
		double totalPayment = emi * tenure;
//		System.out.printf("Monthly EMI is: %.2f%n", emi);
//		System.out.printf("Total Payment: %.2f%n", totalPayment);

		return emi;
	}
}
