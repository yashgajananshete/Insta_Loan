package com.axis.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.axis.service.ApplyForNewLoan;
import com.axis.service.DisplayLoanDetails;
import com.axis.service.UpdateDetails;

public class Main {

	public static void main(String[] args) throws SQLException {

		DBFunction dbf = new DBFunction();

		System.out.println(
				"\n*********************************---WELCOME TO INSTA LOAN---*********************************");
		System.out.println("Enter 1 : Login");
		System.out.println("Enter 2 : Register");
		System.out.print("--->");

		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		outermostloop:
		while (true) {
			if (a == 1) {
				System.out.print("\nEnter Username : ");
				String username = sc.next();
				System.out.print("Enter Password : ");
				String password = sc.next();

				Login log = new Login();

				boolean check = Login.CheckLogiin(username, password);

				
				if (check) {
					while(true) {
						System.out.println("\n\n\t\t\t 1. Display Existing Loan details ");
						System.out.println("\t\t\t 2. Apply for a new Loan ");
						System.out.println("\t\t\t 3. Pay EMI for the Loan ");
						System.out.println("\t\t\t 4. Update Profile details");
						System.out.println("\t\t\t 5. Exit ");
						
						System.out.print("Enter Your Choice -->");
						int choice = sc.nextInt();
						
						  switch (choice) {
	                      case 1:
	                    	  DisplayLoanDetails.Display();
	                         break;

	                      case 2:
	                          ApplyForNewLoan applyForNewLoan = new ApplyForNewLoan();
	                          applyForNewLoan.applyNewLoan();
	                          break;

	                      case 3:
//	                          PayEmiForLoan payEmiForLoan = new PayEmiForLoan();
//	                          payEmiForLoan.payEmiForTheLoan();
	                          break;

	                      case 4:
	                          UpdateDetails updateDetails = new UpdateDetails();
	                          updateDetails.userUpdate();
	                          break;

	                          case 5:
//							Display
	                          break;
	                  }
						
					}					
				}
				else {
					System.out.println("Plz Try Again...");
					continue;
				}

			}
			else {
				System.out.println("Registration Details..\n\n\n");
				
				Register register = new Register();
				
				register.CustomerDetails();
				
			}
		}

	}
}