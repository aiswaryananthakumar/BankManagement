package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.model.CreditCards;

public interface CardsService {

	public CreditCards enrollment(CreditCards creditCard);
	public long checkBalance(CreditCards creditCard);
	public CreditCards payment(CreditCards creditCard);
	public CreditCards closure(CreditCards creditCard);
	public void start();
	public CreditCards open(CreditCards cc);
	public List<CreditCards> viewAll();
	public CreditCards viewByAccountId(Long card_id);
	public CreditCards update(CreditCards cc);
	public CreditCards delete(CreditCards cc);
	public List<String> viewTransactions(Long cardId);
}

