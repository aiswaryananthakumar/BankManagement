package com.techie.mybank.DAO;

import java.util.List;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;

public interface AccountDAO {

	
	public Account open(Account account);
	public Account view(Account account);
	public Account viewAccountById(Long accountNo);
	public List<Account> viewAll(Account account);
	public Account update(Account account);
	public Account delete(Account account);
	public Account deposit(Account account);
	public Account debit(Account account);
	public List<Transactions> transactions(Account account);
	public	long viewBalance(Account account);
}
