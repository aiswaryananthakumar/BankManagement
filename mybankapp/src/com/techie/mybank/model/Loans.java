package com.techie.mybank.model;

public class Loans {

	private String name;
	private String address;
	private long mobileNo;
	private long loanNumber;
	private String email;
	private long loan_id;
	private String loanDate;
	private long accountBalance;
	private long deposit;
	private long debit;
	
	
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public long getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(long loan_id) {
		this.loan_id = loan_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(long loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public long getDeposit() {
		return deposit;
	}
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	public long getDebit() {
		return debit;
	}
	public void setDebit(long debit) {
		this.debit = debit;
	}
	
	 @Override
	    public String toString() {
	        return "Loans [name=" + name + ", address=" + address + ", mobileNo=" + mobileNo +
	               ", loanDate=" + loanDate + ", accountBalance=" + accountBalance +
	               ", deposit=" + deposit + ", debit=" + debit + "]";
	    }
}
