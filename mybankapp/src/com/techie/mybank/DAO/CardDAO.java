package com.techie.mybank.DAO;

import java.util.List;

import com.techie.mybank.model.CreditCards;

public interface CardDAO {
	
	public List<CreditCards> viewAll();
	public CreditCards viewByAccountId(Long card_id);
	public CreditCards update(CreditCards card_id);
	public CreditCards delete(CreditCards cc);
	public CreditCards deposit(CreditCards cc);
	public CreditCards enrollment(CreditCards cc);
	public List<String> viewTransactions(Long cardId);
	public CreditCards debit(CreditCards cc);	
}
