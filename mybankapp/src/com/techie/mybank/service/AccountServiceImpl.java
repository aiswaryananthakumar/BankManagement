package com.techie.mybank.service;

import java.util.List;

import com.techie.mybank.DAO.AccountDAO;
import com.techie.mybank.DAO.AccountDAOImpl;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;

public class AccountServiceImpl implements AccountService {

	AccountDAO ad=new AccountDAOImpl();
	LoginService  lss=new LoginServiceImpl();
	
	@Override
	public Account open(Account account) {
		return ad.open(account);
	}

	@Override
	public Account view(Account account) {
		return ad.view(account);
	}

	@Override
	public Account viewAccountById(Long account_id) {
		return ad.viewAccountById(account_id);
	}

	@Override
	public List<Account> viewAll(Account account) {
		return ad.viewAll(account); 
	}

	@Override
	public Account update(Account account) {
		return ad.update(account);
	}

	@Override
	public Account delete(Account account) {
		return ad.delete(account);
		
	}

	@Override
	public Account deposit(Account account) {
		return ad.deposit(account);
	}

	@Override
	public Account debit(Account account) {
		 return ad.debit(account);
	}

	@Override
	public List<Transactions> transactions(Account account) {
		return ad.transactions(account);
	}

	@Override
	public long viewBalance(Account account) {
		return ad.viewBalance(account);
	}

	@Override
	public List<Account> view() {
		 return ad.viewAll(null);
	}
}