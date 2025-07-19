package com.techie.mybank.main;

import java.util.Scanner;

import com.techie.mybank.Controller.AccountController;
import com.techie.mybank.Controller.CardsController;
import com.techie.mybank.Controller.InsuranceController;
import com.techie.mybank.Controller.LoansController;
import com.techie.mybank.Controller.LoginController;

public class BankClient {

	public static void main(String[] args) {
		if (login()) {
			coreBanking();
		}
		else {
			System.out.println("Login failed");
		}
	}
	
	
	private static boolean login() {
		System.out.println("=================================");
		System.out.println("Welcome to MyBank!");
		System.out.println("Please login with valid credentials!");
		System.out.println("=================================");


		LoginController lc=new LoginController();
		return lc.login();
			
		}

	private static void coreBanking() {
		
		System.out.println("\n================================================");	
		System.out.println("Welcome to MyBank!");
		System.out.println("================================================== ");
		System.out.println("Please choose the desired Option: ");
		System.out.println("================================================== ");

		System.out.println("Select Option 1 to process Login: ");
		System.out.println("Select Option 2 if you want to process an Account: ");
		System.out.println("Select Option 3 if you want to process Creditcards: ");
		System.out.println("Select Option 4 if you want to process Loans: ");
		System.out.println("Select Option 5 if you want to process Insurance: ");


		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();

		switch (option) {
		case 1:
			System.out.println("You have choosen to Login . ");
			LoginController loc = new LoginController();
			if (!loc.login()) {
                System.out.println("Login failed");
            }
			 else {
                 System.out.println("Login successfull!");
             }
			break;
		case 2:
			System.out.println("You have choosen Accounting Operations");
			System.out.println("================================================== ");
			AccountController ac = new AccountController();
			ac.home();
			break;
		case 3:
			System.out.println("You have choosen Creditcard Process");
			System.out.println("================================================== ");
			CardsController cc = new CardsController();
			cc.home();
			break;
		case 4:
			System.out.println("You have choosen Loan Process");
			System.out.println("================================================== ");
			LoansController ln = new LoansController();
			ln.home();
			break;
		case 5:
			System.out.println("You have choosen Insurance Operations");
			System.out.println("================================================== ");
			InsuranceController in = new InsuranceController();
			in.home();
			break;
		default:
			System.out.println("Invalid option");
		}
		sc.close();
	}
}