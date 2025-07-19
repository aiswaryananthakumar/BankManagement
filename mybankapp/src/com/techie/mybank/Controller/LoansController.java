package com.techie.mybank.Controller;

import java.util.List;
import java.util.Scanner;
import com.techie.mybank.model.Loans;
import com.techie.mybank.service.LoansService;
import com.techie.mybank.service.LoansServiceImpl;

public class LoansController {

		Scanner sc=null;
		LoansService ls=new LoansServiceImpl();
		
		public void home() {
			System.out.println("Please choose the desired Option");
			System.out.println("=================================");
			System.out.println("Please select Option 1 if you want to open Loan Account"); 
			System.out.println("Please select Option 2 if you want to view Loan Account Details");
			System.out.println("Please select Option 3 if you want to view Loan details using ViewAccountbyId ");
			System.out.println("Please select Option 4 if you want to update Loan details");
			System.out.println("Please select Option 5 if you want to close Loan details");
			System.out.println("Please select Option 6 if you want to view Loan transactions");
			System.out.println("Please select Option 7 if you want to deposit amount");
			System.out.println("Please select Option 8 if you want to debit amount");
			System.out.println("Please select Option 9 if you want to view loanaccount balance");
			System.out.println("Please select Option 10 if you want to delete loan details");
			
		sc=new Scanner(System.in);
		int option=sc.nextInt();
		switch(option){
		case 1: System.out.println("You have choosen to open/enroll a Loan Account");
		enrollment();
		break;
		case 2: System.out.println("You have choosen to view Loan Account");
		view();
		break;	
		case 3: System.out.println("You have choosen to view Loan Account using ViewAccountById");
		ViewAccountById();
		break;
		case 4: System.out.println("You have choosen to Update Loan Account ");
		update();
		break;
		case 5: System.out.println("You have choosen to close Loan Account");
		closure();
		break;
		case 6: System.out.println("You have choosen to view Loan transactions");
		viewAllTransactions();
		break;
		case 7: System.out.println("You have choosen to deposit amount");
		deposit();
		break;
		case 8: System.out.println("You have choosen to debit amount");
		debit();
		break;
		case 9: System.out.println("You have choosen to view loanaccount balance");
		viewBalance();
		break;
		case 10: System.out.println("You have choosen to delete Loan Details");
		delete();
		break;
		default: 
			System.out.println("Please select the correct desired option");
			break;
		}
		}

		private void enrollment() {
			System.out.println("You have choosen LoanAccount Opening Process");
			System.out.println("===========================================");
			System.out.println("Please enter Loan Account Holder Details");
			Loans loan=new Loans();
			
			System.out.println("===========================================");
			System.out.println("Please enter Loan Account Holder Name :");
			String name=sc.next();
			loan.setName(name);
			
			System.out.println("Please enter Loan No:");
			long loanNumber=sc.nextLong();
			loan.setLoanNumber(loanNumber);
			
			System.out.println("Please enter Mobile No:");
			long mobileNo=sc.nextLong();
			loan.setMobileNo(mobileNo);
			
			System.out.println("Please enter Email Id:");
			String email=sc.next();
			loan.setEmail(email);
			
			System.out.println("Please enter Address:");
			String address=sc.next();
			loan.setAddress(address);
			
			System.out.println("Please enter Loan Enrollment date:");
			String loanDate=sc.next();
			loan.setLoanDate(loanDate);
			
			System.out.println("Please enter Account Balance:");
			Long accountBalance=sc.nextLong();
			loan.setAccountBalance(accountBalance);
			
			System.out.println("Please enter Deposit Amount:");
			Long deposit=sc.nextLong();
			loan.setDeposit(deposit);
			
			System.out.println("Please enter Debit Amount:");
			Long debit=sc.nextLong();
			loan.setDebit(debit);
			
			 Loans enrolled = ls.enrollment(loan);
		        if (enrolled != null) {
		            System.out.println("Loan Created Successfully: " + enrolled);
		        } else {
		            System.out.println("Loan Creation Failed.");
		        }
		        home();
		}

		private void view() {
			System.out.println("You have choosen option to view LoanAccount .");
			System.out.println("===========================================");
			List<Loans> list = ls.view();
	        if (list.isEmpty()) {
	            System.out.println("No loan accounts found.");
	        } else {
	            for (Loans loan : list) {
	                System.out.println(loan);
	            }
	        }
		    home(); 
		}	
			
		private void ViewAccountById() {
			System.out.println("You have choosen option to view LoanAccount details using LoanId.");
			System.out.println("===========================================");
			System.out.println("Please enter Loan Id :");
			System.out.println("===========================================");
			Long id = sc.nextLong();
	        Loans loan = ls.viewByAccountId(id);
	        if (loan != null) {
	            System.out.println("Loan Details: " + loan);
	        } else {
	            System.out.println("Loan not found for ID: " + id);
	        }
		        home();
		}
		
			
		private void update() {
			System.out.println("You have choosen option to Update Loan details");
			System.out.println("===========================================");
			
			System.out.println("Please enter Loan Id to be updated: ");	
			System.out.println("===========================================");
			Loans loan=new Loans();
				
			System.out.println("Enter Loan ID to update:");
			loan.setLoan_id(sc.nextLong());
								
			System.out.println("Please enter Mobile No: ");
			loan.setMobileNo(sc.nextLong());
			
			System.out.println("Please enter Address to be updated: ");		
			loan.setAddress(sc.next());
			
			Loans updated = ls.update(loan);
		    if (updated != null) {
		        System.out.println("Loan Updated Successfull: " + updated);
		    } else {
		        System.out.println("Loan Update failed.");
		    } 
		    home();
		}
		
		private void viewBalance() {
			System.out.println("You have choosen option to View Balance details");
			System.out.println("===========================================");
			
			System.out.println("Please enter Loan Id to be view balance: ");	
			System.out.println("===========================================");
			 Long loan_id = sc.nextLong();

			    Loans loan = ls.viewByAccountId(loan_id);
			    if (loan != null) {
			        System.out.println("Current Loan Account Balance: ₹" + loan.getAccountBalance());
			    } else {
			        System.out.println("Loan account not found.");
			    }
			    home();
		}

		private void viewAllTransactions() {
			System.out.println("You have choosen option to View All Transaction details");
			System.out.println("===========================================");
			
			System.out.println("Please enter Loan Id to be updated: ");	
			System.out.println("===========================================");
			Long loan_id = sc.nextLong();

	        List<String> transactions = ls.viewAllTransactions(loan_id);
	        if (transactions != null && !transactions.isEmpty()) {
	            for (String txn : transactions) {
	                System.out.println(txn);
	            }
	        } else {
	            System.out.println("No transactions found for this loan.");
	        }
	        home();
		}
		
		private void closure() {
			
			System.out.println("You have choosen option to close Loan account");
			System.out.println("===========================================");
	
			System.out.println("Please enter Loan Id to be updated: ");	
			System.out.println("===========================================");
			
			Long loan_id = sc.nextLong(); 

		    Loans loan = ls.viewByAccountId(loan_id);

		    if (loan != null) {
		      
		        loan.setLoanDate("Closed");

		        Loans closedLoan = ls.closure(loan_id); 

		        if (closedLoan != null) {
		            System.out.println("Loan Closed Successfully: " + closedLoan);
		        } else {
		            System.out.println("Loan Closure Failed.");
		        }
		    } else {
		        System.out.println("Loan not found for ID: " + loan_id);
		    }

		    home();
}
		
		private void debit() {
			    System.out.println("Enter Loan ID to debit:");
			    Long loan_id = sc.nextLong();
			    System.out.println("Enter debit amount:");
			    Long debitAmount = sc.nextLong(); 

			    Loans loan = ls.viewByAccountId(loan_id);

			    if (loan != null) {
			        Long currentBalance = loan.getAccountBalance();
			        if (currentBalance >= debitAmount) {
			            loan.setDebit(debitAmount);
			            loan.setAccountBalance(currentBalance - debitAmount);
			            
			            Loans updatedLoan = ls.debit(loan_id, debitAmount); 

			            if (updatedLoan != null) {
			                System.out.println("Amount debited successfully. Updated Balance: " + updatedLoan.getAccountBalance());
			            } else {
			                System.out.println("Debit operation failed.");
			            }
			        } else {
			            System.out.println("Insufficient balance to debit the amount.");
			        }
			    } else {
			        System.out.println("Loan not found for ID: " + loan_id);
			    }

			    home();
			}

		private void deposit() {
			System.out.println("Enter Loan ID for deposit: ");
			 Long loan_id = sc.nextLong();
		        System.out.println("Enter deposit amount:");
		        Long depositAmount = sc.nextLong();

		        Loans loan = new Loans();
		        loan.setLoan_id(loan_id);
		        loan.setDeposit(depositAmount);

		        Loans deposited = ls.deposit(loan_id, depositAmount);
		        if (deposited != null) {
		            System.out.println("Amount deposited successfully. Updated Balance: " + deposited.getAccountBalance());
		        } else {
		            System.out.println("Deposit failed.");
		        }
		        home();
		}
		
		private void delete() {
			System.out.println("You have choosen option to delete Loan details");
			System.out.println("===========================================");
			System.out.println("Please enter Loan Id: ");	
			Long loan_id = sc.nextLong();

		    Loans loan = new Loans();
		    loan.setLoan_id(loan_id);

		    Loans deleted = ls.delete(loan);
		    if (deleted != null) {
		        System.out.println("Loan Deleted Successfully.");
		    } else {
		        System.out.println("Delete operation failed.");
		    }
		    home();
		}
}

				