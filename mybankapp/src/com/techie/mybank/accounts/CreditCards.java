package com.techie.mybank.accounts;

public interface CreditCards {

		String start = null;
	public CreditCards createAccount(CreditCards cc);
	public CreditCards deposit(CreditCards cc);
	public CreditCards debit(CreditCards cc);
	public CreditCards closure(CreditCards cc);
	public void start();
}
