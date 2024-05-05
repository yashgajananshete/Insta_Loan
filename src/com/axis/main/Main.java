package com.axis.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.axis.db.DBFunction;
import com.axis.service.ApplyForNewLoan;
import com.axis.service.DisplayLoanDetails;
import com.axis.service.PayEmi;
import com.axis.service.UpdateDetails;

public class Main {

	public static int customerId;
	public static String username = "";
	public static String email = "";

	public static void main(String[] args) throws SQLException {

		outermostloop: while (true) {

			DBFunction dbf = new DBFunction();

			System.out.println(
					"\n*********************************---WELCOME TO INSTA LOAN---*********************************");
			System.out.println("\nEnter 1 : Login");
			System.out.println("Enter 2 : Register");
			System.out.print("--->");

			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			outerloop: while (true) {
				if (a == 1) {
					System.out.println("\n**********************   LOGIN    **********************");

					System.out.print("\nEnter Username : ");
					String username = sc.next();
					System.out.print("Enter Password : ");
					String password = sc.next();

					Login log = new Login();

					boolean check = Login.CheckLogiin(username, password);

					if (check) {

						while (true) {
							System.out.println("\n\n\t\t 1. Display Existing Loan details ");
							System.out.println("\t\t 2. Apply for a new Loan ");
							System.out.println("\t\t 3. Pay EMI for the Loan ");
							System.out.println("\t\t 4. Update Profile details");
							System.out.println("\t\t 5. Exit ");

							System.out.print("\nEnter Your Choice -->");
							int choice = sc.nextInt();
							ApplyForNewLoan applyForNewLoan = new ApplyForNewLoan();
							switch (choice) {
							case 1:
								DisplayLoanDetails.Display();
								break;

							case 2:
								if (applyForNewLoan.checkExistingLoan()) {
									System.out.println("You have already existing loan...");
									System.out.println("Please contact bank manager to apply for another loan...\n");
									DisplayLoanDetails.Display();
								} else {
									applyForNewLoan.applyNewLoan();
								}
								break;

							case 3:
								PayEmi payEmiForLoan = new PayEmi();
								if (applyForNewLoan.checkExistingLoan()) {
									payEmiForLoan.payEmiForTheLoan();
								} else {
									System.out.println("\nYou Don't have any Existing loan.....");
								}
								break;

							case 4:
								UpdateDetails updateDetails = new UpdateDetails();
								updateDetails.userUpdate();
								break;

							case 5:
								System.out
										.println("\n*****************************************************************");
								System.out.println("Thank you for using our Insta Loan application. Have a great day!");
								System.out.println("*****************************************************************");
								break outermostloop;
							}

						}
					} else {
						System.out.println("Plz Try Again...");
						continue;
					}

				} else {
					System.out.println("\n**********************   REGISTRATION    **********************\n");

					Register register = new Register();
					register.CustomerDetails();

					break outerloop;

				}
			}

		}

	}

}