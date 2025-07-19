package com.techie.mybank.model;

public class CreditCards {

	private String cardHolderName;
	private long cardNo;
	private String email;
	private String address;
	private long mobileNo;
	private long cardid;
	private long amount;
	
		
	public long getCardid() {
		return cardid;
	}
	public void setCardid(long cardid) {
		this.cardid = cardid;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
	
    @Override
    public String toString() {
        return "CreditCard Details {" +
               "Card ID=" + cardid +
               ", Card Holder Name='" + cardHolderName + '\'' +
               ", Card No=" + cardNo +
               ", Email='" + email + '\'' +
               ", Mobile No=" + mobileNo +
               ", Address='" + address + '\'' +
               ", Balance=" + amount +
               '}';
    }
}
