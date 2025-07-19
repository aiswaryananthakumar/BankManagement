package com.techie.mybank.Controller;

import java.util.List;
import java.util.Scanner;

import com.techie.mybank.model.CreditCards;
import com.techie.mybank.service.CardsService;
import com.techie.mybank.service.CardsServiceImpl;

public class CardsController {

		Scanner sc = new Scanner(System.in);
		CardsService cs=new CardsServiceImpl();
		
		public void home() {
			System.out.println("Please choose the desired Option");
			System.out.println("=================================");
			System.out.println("Please select Option 1 if you want to open CreditCard"); 
			System.out.println("Please select Option 2 if you want to view an Creditcard accountdetails");
			System.out.println("Please select Option 3 if you want to ViewAccountby Creditcard details");
			System.out.println("Please select Option 4 if you want to update Creditcard details");
			System.out.println("Please select Option 5 if you want to delete Creditcard details");
			System.out.println("Please select Option 6 if you want to view account transactions");
			System.out.println("Please select Option 7 if you want to deposit amount");
			System.out.println("Please select Option 8 if you want to debit amount");
			System.out.println("Please select Option 9 if you want to view account balance");
		
		int selectoption=sc.nextInt();
		
		switch(selectoption){
			case 1: System.out.println("You have selected Creditcard Opening Process");
			enrollment();
			break;
			case 2: System.out.println("You have selected View Creditcard Details");
			viewAll();
			break;
			case 3: System.out.println("You have selected ViewByAccountId");
			viewByAccountId();
			break;
			case 4: System.out.println("You have selected Update CreditCard Details");
			update();
			break;
			case 5: System.out.println("You have selected Delete CreditCard Details");
			delete();
			break;
			case 6: System.out.println("You have selected viewAll Transactions in Creditcard");
			viewAllTransactions();
			break;
			case 7: System.out.println("You have selected Deposit Amount in Creditcard");
			deposit();
			break;
			case 8: System.out.println("You have selected Debit Amount in Creditcard");
			debit();
			break;
			case 9: System.out.println("You have selected view Account Balance in Creditcard");
			viewBalance();
			break;
			default: 
				System.out.println("Please select the correct desired option");
				break;
				}

		}
		
		private void enrollment() {
			System.out.println("You have choosen CreditCard Opening Process");
			System.out.println("===========================================");
			System.out.println("Please enter Card Holder Details");
			CreditCards cc = new CreditCards();

	        System.out.println("Enter Card Holder Name:");
	        cc.setCardHolderName(sc.next());

	        System.out.println("Enter Card Number:");
	        cc.setCardNo(sc.nextLong());

	        System.out.println("Enter Email:");
	        cc.setEmail(sc.next());

	        System.out.println("Enter Mobile Number:");
	        cc.setMobileNo(sc.nextLong());

	        System.out.println("Enter Address:");
	        cc.setAddress(sc.next());
	        
	        System.out.println("Enter Amount:");
	        cc.setAmount(sc.nextLong());

	        CreditCards enrolledCard = cs.enrollment(cc);
	        System.out.println("Card Enrolled Successfully: " + enrolledCard);
	        home();
		}

		private void viewAll() {
			System.out.println("You have choosen option to view CreditCard details");
			System.out.println("===========================================");
			 List<CreditCards> cards = cs.viewAll();		    
			    if (cards.isEmpty()) {
			        System.out.println("No credit card records found.");
			    } else {
			        for (CreditCards cc : cards) {
			            System.out.println("-------- Credit Card Details --------");
			            System.out.println("Card ID: " + cc.getCardid());
			            System.out.println("Card Holder Name: " + cc.getCardHolderName());
			            System.out.println("Card Number: " + cc.getCardNo());
			            System.out.println("Email: " + cc.getEmail());
			            System.out.println("Mobile Number: " + cc.getMobileNo());
			            System.out.println("Address: " + cc.getAddress());
			            System.out.println("Balance: ₹" + cc.getAmount());
			            System.out.println("------------------------------------");
			        }
			    }
			    home(); 
		}
				
		private void viewByAccountId() {
			System.out.println("You have choosen option to view CreditCard details");
			System.out.println("===========================================");
			System.out.println("Please enter Card Id: ");
			 Long card_id = sc.nextLong();
		        CreditCards cc = cs.viewByAccountId(card_id);
		        if (cc != null) {
		            System.out.println("Card Details: " + cc);
		        } else {
		            System.out.println("Card not found.");
		        }
		        home();
		}
			
private void update() {
	System.out.println("You have choosen option to Update CreditCard details");
	System.out.println("===========================================");
	System.out.println("Please enter Card Id: ");	
	CreditCards cc = new CreditCards();

    System.out.println("Enter Card ID to update:");
    cc.setCardid(sc.nextLong());

    System.out.println("Enter New Mobile Number:");
    cc.setMobileNo(sc.nextLong());

    CreditCards updated = cs.update(cc);
    if (updated != null) {
        System.out.println("Card Updated: " + updated);
    } else {
        System.out.println("Update failed.");
    } 
    home();
}
		
private void delete() {
	System.out.println("You have choosen option to delete CreditCard details");
	System.out.println("===========================================");
	System.out.println("Please enter Card Id: ");	
	Long card_id = sc.nextLong();
    CreditCards cc = new CreditCards();
    cc.setCardid(card_id);
    CreditCards deleted = cs.delete(cc);
    if (deleted != null) {
        System.out.println("Card Deleted Successfully.");
    } else {
        System.out.println("Delete operation failed.");
    }
    home();
}

		private void viewBalance() {
			System.out.println("Enter Card ID to check balance:");
	        Long card_id = sc.nextLong();
	        CreditCards cc = new CreditCards();
	        cc.setCardid(card_id);
	        long balance = cs.checkBalance(cc);
	        System.out.println("Current Balance: " + balance);
		}

		private void viewAllTransactions() {
			 System.out.println("Enter Card ID to view transactions: ");
			    Long card_id = sc.nextLong();
			    List<String> transactions = cs.viewTransactions(card_id);

			    if (transactions.isEmpty()) {
			        System.out.println("No transactions found for card ID: " + card_id);
			    } else {
			        System.out.println("Transaction History for Card ID " + card_id + ":");
			        for (String t : transactions) {
			            System.out.println(t);
			        }
			    }
	        home();
		}

		private void debit() {
			System.out.println("Enter Card ID to debit:");
	        Long card_id = sc.nextLong();
	        System.out.println("Enter debit amount:");
	        Long amount = sc.nextLong();

	        CreditCards cc = new CreditCards();
	        cc.setCardid(card_id);
	        cc.setAmount(amount);

	        CreditCards debited = cs.closure(cc);
	        if (debited != null) {
	            System.out.println("Amount debited successfully. Updated Balance: " + debited.getAmount());
	        } else {
	            System.out.println("Debit failed.");
	        }
	        home();
		}

		private void deposit() {
			System.out.println("Enter Card ID for deposit: ");
			 Long card_id = sc.nextLong();
		        System.out.println("Enter deposit amount:");
		        Long amount = sc.nextLong();

		        CreditCards cc = new CreditCards();
		        cc.setCardid(card_id);
		        cc.setAmount(amount);

		        CreditCards deposited = cs.payment(cc);
		        if (deposited != null) {
		            System.out.println("Amount deposited successfully. Updated Balance: " + deposited.getAmount());
		        } else {
		            System.out.println("Deposit failed.");
		        }
		        home();
		}
	}