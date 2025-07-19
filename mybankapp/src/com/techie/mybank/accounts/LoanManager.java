package com.techie.mybank.accounts;

import com.techie.mybank.model.Loans;

public interface LoanManager {
	String start = null;
	public Loans enrollment(Loans loan);
	public Loans deposit(Loans loan);
	public Loans debit(Loans loan);
	public Loans closure(Loans loan);
	public void start();
	
	
	
}
