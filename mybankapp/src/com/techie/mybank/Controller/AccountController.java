package com.techie.mybank.Controller;

import java.util.List;
import java.util.Scanner;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;
import com.techie.mybank.service.AccountService;
import com.techie.mybank.service.AccountServiceImpl;

public class AccountController {
	
	Scanner sc = new Scanner(System.in);
	AccountService as=new AccountServiceImpl();
	
public void home() {
	System.out.println("Please choose the desired Option");
	System.out.println("=================================");
	System.out.println("Please select Option 1 if you want to open an account");
	System.out.println("Please select Option 2 if you want to view an account");
	System.out.println("Please select Option 3 if you want to ViewAccountby an accountNo");
	System.out.println("Please select Option 4 if you want to update an account");
	System.out.println("Please select Option 5 if you want to delete an account");
	System.out.println("Please select Option 6 if you want to view account transactions");
	System.out.println("Please select Option 7 if you want to deposit amount");
	System.out.println("Please select Option 8 if you want to debit amount");
	System.out.println("Please select Option 9 if you want to view account balance");

	int option=sc.nextInt();
	
	switch(option) {
	case 1:System.out.println("You have selected Account Opening process");
	open();
	break;
	case 2:System.out.println("You have selected ViewAll Account process");
	viewAll();
	break;
	case 3: System.out.println("You have selected View Account by Accountnumber process");
	viewAccountById();
	break;
	case 4: System.out.println("You have selected update Account process");
	update();
	break;
	case 5: System.out.println("You have selected Delete Account process");
	delete();
	break;
	case 6: System.out.println("You have selected viewAll Transactions process");
	viewAllTransactions();
	break;
	case 7: System.out.println("You have selected Deposit Account process");
	deposit();
	break;
	case 8: System.out.println("You have selected Debit Account process");
	debit();
	break;
	case 9: System.out.println("You have selected view Account Balance process");
	viewBalance();
	break;
	default: 
		System.out.println("Please select the correct desired option");
		break;
	
	}
	
}

private void open() {
System.out.println("You have choosen Account Opening Process");
System.out.println("=========================================");
System.out.println("Please enter Account Holder Details");

Account account=new Account();

sc.nextLine();
System.out.println("Please enter Account Holder Name");
String accountName=sc.next();
account.setAccountName(accountName);

System.out.println("Please enter Account Holder AccountNo.");
Long accountNo=sc.nextLong();
account.setAccountNo(accountNo);

System.out.println("Please enter Account Holder Email");
String email=sc.next();
account.setEmail(email);

System.out.println("Please enter Account Holder MobileNo.");
Long mobileno=sc.nextLong();
account.setMobileno(mobileno);

sc.nextLine();
System.out.println("Please enter Account Holder Address");
String address=sc.next();
account.setAddress(address);

System.out.println("Please enter Account Holder Account balance");
Long accountBalance=sc.nextLong();
account.setAccountBalance(accountBalance);

System.out.println("Please enter Account Holder Credit Amount");
Long creditAmount=sc.nextLong();
account.setCreditAmount(creditAmount);

System.out.println("Please enter Account Holder Debit Amount");
Long debitAmount=sc.nextLong();
account.setDebitAmount(debitAmount);

account = as.open(account);
System.out.println("Account opened successfully!" + account.getAccountid());
home();
}

private void viewAll() {
	System.out.println("You have choosen viewAll Account Process");
	System.out.println("=========================================");
	List<Account> accounts = as.view();
	if (accounts.isEmpty()) {
	    System.out.println("No accounts found.");
	} else {
	    accounts.forEach(System.out::println);
	}
	home();
}

private void viewAccountById() {
	System.out.println("You have choosen viewAccountById Process");
	System.out.println("=========================================");
    System.out.println("Enter the AccountId : ");
    Long account_id = sc.nextLong();
    Account acc = as.viewAccountById(account_id);
    if (acc != null) {
        System.out.println(acc);
    } else {
        System.out.println("Account not found!");
    }
    home();
}

private void update() {
	System.out.println("You have choosen Account updation Process");
	System.out.println("=========================================");
	
	Account account=new Account();
	
	    System.out.println("Enter Account Id to continue Account updation Process:");
		Long account_id=sc.nextLong();
		account.setAccountid(account_id);

		System.out.println("Enter Mobile No. to update :");	
		Long mobileno=sc.nextLong();
		account.setMobileno(mobileno);
		sc.nextLine();
		
		System.out.println("Enter Address for updatation :");	
		String address=sc.nextLine();
		account.setAddress(address);
			
	    as.update(account);
        System.out.println("Account updated successfully!");
        home();
}


private void delete() {
	System.out.println("You have choosen Account Delete Process");
	System.out.println("=========================================");
	
	System.out.print("Enter Account ID to delete: ");
	Long account_id = sc.nextLong();
    Account account = new Account();
    account.setAccountid(account_id);
    as.delete(account);
    System.out.println("Account deleted successfully!");
    home();
}
private void deposit() {
	System.out.println("\n=== Deposit Amount ===");
	System.out.println("=========================================");
    Account account = new Account();

    System.out.print("Enter Account ID: ");
    account.setAccountid(sc.nextLong());

    System.out.print("Enter Deposit Amount: ");
    account.setCreditAmount(sc.nextLong());

    Account updated = as.deposit(account);
    System.out.println("Deposit successful. Updated account: " + updated);
    home();
	
}

private void debit() {
	System.out.println("\n=== Debit Amount ===");
	System.out.println("=========================================");
    Account account = new Account();

    System.out.print("Enter Account ID: ");
    account.setAccountid(sc.nextLong());

    System.out.print("Enter Debit Amount: ");
    account.setDebitAmount(sc.nextLong());

    Account updated = as.debit(account);
    System.out.println("Debit successful. Updated account: " + updated);
    home();
	
}

private void viewAllTransactions() {
    System.out.println("\n=== View Account Transactions ===");
    System.out.print("Enter Account ID: ");
    long accountId = sc.nextLong();
    sc.nextLine();

    Account acc = new Account();
    acc.setAccountid(accountId);

    List<Transactions> transactions = as.transactions(acc);

    if (transactions == null || transactions.isEmpty()) {
        System.out.println("No transaction data found.");
    } else {
        for (Transactions tx : transactions) {
            System.out.println("-------------------------------");
            System.out.println("Account ID: " + tx.getAccountId());
            System.out.println("Type      : " + tx.getType());
            System.out.println("Amount    : " + tx.getTransactionAmount());
            System.out.println("Date Info : " + tx.getTransactionDate());
        }
    }
     home();
}

private void viewBalance() {
	 System.out.println("\n=== View Account Balance ===");
	 System.out.println("=========================================");
     Account account = new Account();

     System.out.print("Enter Account ID: ");
     account.setAccountid(sc.nextLong());

     long balance = as.viewBalance(account);
     System.out.println("Current Balance: ₹" + balance);
     home();
}

}
