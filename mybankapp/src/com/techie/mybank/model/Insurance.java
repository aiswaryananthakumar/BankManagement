package com.techie.mybank.model;

public class Insurance {

	    private int policyId;                  
	    private String policyHolderName;        
	    private String dateOfBirth;              
	    private String gender;                   
	    private String contactNumber;           
	    private String email;                 
	    private String address;                  
	    private String policyType;              
	    private double sumInsured;              
	    private double premiumAmount;          
	    private String preExistingDiseases;     
	    private boolean smoker;                  
	    private boolean alcoholConsumer;        
	    private String policyStartDate;          
	    private String policyEndDate; 
	    private double creditAmount;
	    private int accountID;

	    public int getPolicyId() {
	        return policyId;
	    }

	    public void setPolicyId(int policyId) {
	        this.policyId = policyId;
	    }

	    public String getPolicyHolderName() {
	        return policyHolderName;
	    }

	    public void setPolicyHolderName(String policyHolderName) {
	        this.policyHolderName = policyHolderName;
	    }

	    public String getDateOfBirth() {
	        return dateOfBirth;
	    }

	    public void setDateOfBirth(String dateOfBirth) {
	        this.dateOfBirth = dateOfBirth;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getContactNumber() {
	        return contactNumber;
	    }

	    public void setContactNumber(String contactNumber) {
	        this.contactNumber = contactNumber;
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

	    public String getPolicyType() {
	        return policyType;
	    }

	    public void setPolicyType(String policyType) {
	        this.policyType = policyType;
	    }

	    public double getSumInsured() {
	        return sumInsured;
	    }

	    public void setSumInsured(double sumInsured) {
	        this.sumInsured = sumInsured;
	    }

	    public double getPremiumAmount() {
	        return premiumAmount;
	    }

	    public void setPremiumAmount(double premiumAmount) {
	        this.premiumAmount = premiumAmount;
	    }

	    public String getPreExistingDiseases() {
	        return preExistingDiseases;
	    }

	    public void setPreExistingDiseases(String preExistingDiseases) {
	        this.preExistingDiseases = preExistingDiseases;
	    }

	    public boolean isSmoker() {
	        return smoker;
	    }

	    public void setSmoker(boolean smoker) {
	        this.smoker = smoker;
	    }

	    public boolean isAlcoholConsumer() {
	        return alcoholConsumer;
	    }

	    public void setAlcoholConsumer(boolean alcoholConsumer) {
	        this.alcoholConsumer = alcoholConsumer;
	    }

	    public String getPolicyStartDate() {
	        return policyStartDate;
	    }

	    public void setPolicyStartDate(String policyStartDate) {
	        this.policyStartDate = policyStartDate;
	    }

	    public String getPolicyEndDate() {
	        return policyEndDate;
	    }

	    public void setPolicyEndDate(String policyEndDate) {
	        this.policyEndDate = policyEndDate;
	    }
	    
	    public double getCreditAmount() {
	        return creditAmount;
	    }
	    public void setCreditAmount(double creditAmount) {
	        this.creditAmount = creditAmount;
	    }
	    
	    public int getAccountID() {
	        return accountID;
	    }

	    public void setAccountID(int accountID) {
	        this.accountID = accountID;
	    }

	    
	    @Override
	    public String toString() {
	        return "Insurance{" +
	                "policyId=" + policyId +
	                ", policyHolderName='" + policyHolderName + '\'' +
	                ", dateOfBirth='" + dateOfBirth + '\'' +
	                ", gender='" + gender + '\'' +
	                ", contactNumber='" + contactNumber + '\'' +
	                ", email='" + email + '\'' +
	                ", address='" + address + '\'' +
	                ", policyType='" + policyType + '\'' +
	                ", sumInsured=" + sumInsured +
	                ", premiumAmount=" + premiumAmount +
	                ", preExistingDiseases='" + preExistingDiseases + '\'' +
	                ", smoker=" + smoker +
	                ", alcoholConsumer=" + alcoholConsumer +
	                ", policyStartDate='" + policyStartDate + '\'' +
	                ", policyEndDate='" + policyEndDate + '\'' +
	                ", creditAmount=" + creditAmount + '\'' +
	                ", accountID=" + accountID +
	                '}';
	    }
	}