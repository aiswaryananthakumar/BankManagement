package com.techie.mybank.service;

import java.util.List;
import com.techie.mybank.model.Loans;

public interface LoansService {

	public Loans enrollment (Loans loan);
	
	public long viewBalance(Long loan_id);
	
	public Loans payment(Long loan_id);
	
	public Loans closure (Long loan_id);
	
	public void start();
	
	public List<Loans> view();
	
	public Loans update(Loans loan);
	
	public Loans delete(Loans loan);
	
	public Loans viewByAccountId(Long loan_id);
	
	public List<String> viewAllTransactions(Long loan_id);

	public Loans debit(Long loan_id, Long debitAmount);

	Loans deposit(Long loan_id, Long amount);
}
