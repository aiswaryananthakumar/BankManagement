package com.techie.mybank.model;

public class Account {
	
			private String accountName;
			private long accountNo;
			private long accountid;
			private String email;
			private long mobileno;
			private String address;
			private long accountBalance;
			private long creditAmount;
			private long debitAmount;
				
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public long getAccountid() {
				return accountid;
			}
			public void setAccountid(long accountid) {
				this.accountid = accountid;
			}
			public String getAccountName() {
				return accountName;
			}
			public void setAccountName(String accountName) {
				this.accountName = accountName;
			}
			public long getAccountNo() {
				return accountNo;
			}
			public void setAccountNo(long accountNo) {
				this.accountNo = accountNo;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public long getMobileno() {
				return mobileno;
			}
			public void setMobileno(long mobileno) {
				this.mobileno = mobileno;
			}
			
			public long getAccountBalance() {
				return accountBalance;
			}
			public void setAccountBalance(long accountBalance) {
				this.accountBalance = accountBalance;
			}
			public long getCreditAmount() {
				return creditAmount;
			}
			public void setCreditAmount(long creditAmount) {
				this.creditAmount = creditAmount;
			}
			public long getDebitAmount() {
				return debitAmount;
			}
			public void setDebitAmount(long debitAmount) {
				this.debitAmount = debitAmount;
			}
			@Override
		    public String toString() {
		        return "Account{" +
		                "accountid=" + accountid +
		                ", accountNo=" + accountNo +
		                ", accountName='" + accountName + '\'' +
		                ", email='" + email + '\'' +
		                ", mobileno=" + mobileno +
		                ", address='" + address + '\'' +
		                ", accountBalance=" + accountBalance +
		                ", creditAmount=" + creditAmount +
		                ", debitAmount=" + debitAmount +
		                '}';
		    }
			
}
