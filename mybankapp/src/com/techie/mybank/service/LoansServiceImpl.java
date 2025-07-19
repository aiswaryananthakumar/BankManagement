package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.DAO.LoanDAO;
import com.techie.mybank.DAO.LoanDAOImpl;
import com.techie.mybank.model.Loans;

public class LoansServiceImpl implements LoansService {

LoanDAO ld = new LoanDAOImpl();

	public Loans enrollment(Loans loan) {
		return ld.enrollment(loan);		
	}
	
	public Loans update(Loans loan) {
		return ld.update(loan);
	}

	@Override
	public Loans delete(Loans loan) {
		return ld.delete(loan);
	}

	@Override
	public Loans viewByAccountId(Long loan_id) {
		return ld.ViewAccountById(loan_id);
	}


	 @Override
	    public Loans payment(Long loan_id) {
	        return ld.payment(loan_id);
	    }

	@Override
	public List<Loans> view() {
		List<Loans> loanList = ld.view();
        if (loanList == null || loanList.isEmpty()) {
            System.out.println("No Loan Accounts found.");
            return List.of(); 
        } else {
            for (Loans l : loanList) {
                System.out.println(l);
            }
            return loanList;
        }
	}

	@Override
    public Loans closure(Long loan_id) {
        return ld.closure(loan_id);
    }

	@Override
	public long viewBalance(Long loan_id) {
		return ld.viewBalance(loan_id);
	}

	@Override
	public void start() {
		System.out.println("Loan Service Started");
	}

	@Override
	public List<String> viewAllTransactions(Long loan_id) {
		return ld.viewAllTransactions(loan_id);
	}

	@Override
	public Loans debit(Long loan_id, Long debitAmount) {
		 return ld.debit(loan_id, debitAmount);
	}

	@Override
	public Loans deposit(Long loan_id, Long amount) {
		return ld.deposit(loan_id, amount);
	}
}
