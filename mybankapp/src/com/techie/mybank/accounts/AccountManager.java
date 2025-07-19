package com.techie.mybank.accounts;

import com.techie.mybank.model.Account;

public interface AccountManager {

	String start = null;
	public Account createAccount(Account account);
	public Account deposit(Account account);
	public Account debit(Account account);
	public Account closure(long accountNumber);
	public void start();
	
}
