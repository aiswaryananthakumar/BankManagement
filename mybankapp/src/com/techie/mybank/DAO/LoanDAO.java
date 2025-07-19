package com.techie.mybank.DAO;

import java.util.List;

import com.techie.mybank.model.Loans;

public interface LoanDAO {

	public Loans enrollment(Loans loan);

	public Loans ViewAccountById(long loan_id);

	public Loans update(Loans loan);

	public Loans closure(long loan_id);
	
	public Loans deposit(long loan_id, long amount);
	
    public Loans debit(long loan_id, long amount);

	public List<Loans> view();

	public Loans delete(Loans loan);
	
    public List<String> viewAllTransactions(long loan_id);

	public Loans payment(Long loan_id);

	long viewBalance(long loan_id);


}
