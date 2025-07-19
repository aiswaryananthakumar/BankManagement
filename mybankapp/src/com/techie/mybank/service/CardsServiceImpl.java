package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.DAO.CardDAO;
import com.techie.mybank.DAO.CardDAOImpl;
import com.techie.mybank.model.CreditCards;

public class CardsServiceImpl implements CardsService{
	
	
CardDAO cd=new CardDAOImpl();


	
	public CreditCards enrollment(CreditCards cc) {
		return cd.enrollment(cc);
	}

	@Override
	public List<CreditCards> viewAll() {
	    return cd.viewAll();
	}
	
	public CreditCards viewByAccountId(Long card_id) {
		 return cd.viewByAccountId(card_id);
	}
	

	public CreditCards update(CreditCards card_id) {
		return cd.update(card_id);
	}
	
	
	public CreditCards delete(CreditCards cc) {
		return cd.delete(cc);
	}

	@Override
	public long checkBalance(CreditCards creditCard) {
		return cd.viewByAccountId(creditCard.getCardid()).getAmount();
	}

	@Override
	public CreditCards payment(CreditCards creditCard) {
		return cd.deposit(creditCard);
	}

	@Override
	public CreditCards closure(CreditCards creditCard) {
		return cd.debit(creditCard);
	}

	@Override
	public void start() {
		 System.out.println("Credit Card Service Started");
	}

	@Override
	public CreditCards open(CreditCards cc) {
		return cd.enrollment(cc);
	}


	@Override
	public List<String> viewTransactions(Long cardId) {
		return cd.viewTransactions(cardId);
	}
}
