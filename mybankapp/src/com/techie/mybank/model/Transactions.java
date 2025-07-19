package com.techie.mybank.model;

public class Transactions {

	private long transactionNumber;
	private long transactionId;
	private long accountId;
	private String type;
	private long transactionAmount;
	private String transactionDate;
	private String transactionName;
	
	public long getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}	
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
               ", Account ID: " + accountId +
               ", Type: " + type +
               ", Amount: " + transactionAmount +
               ", Date: " + transactionDate;
    }
	
	
}