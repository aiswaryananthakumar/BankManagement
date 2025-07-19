package com.techie.mybank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BankDatabase {
	private static final String URL = "jdbc:mysql://localhost:3306/mybankaccount";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static BankDatabase bd=new BankDatabase();
    
public Connection getConnection() {

	try {
		Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
}
		
	public String  createtableloans(){
		Connection comm=bd.getConnection();
	try {
		Statement stmt=comm.createStatement();
		String sql="CREATE table loan_acc"+"(loan_id Integer PRIMARY KEY not Null AUTO_INCREMENT ,"+
		"loanNumber BIGINT(10),"+"name VARCHAR(45),"+"email VARCHAR(45),"+"mobileNo BIGINT(10),"+"address VARCHAR(45),loandate Varchar(10))";
	
		stmt.executeUpdate(sql);
		comm.close();			
	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
	}
	
	 public String createTableLogin() {
		 Connection comm = bd.getConnection();
	        String sql = "CREATE TABLE login ("+ "userid BIGINT NOT NULL PRIMARY KEY," + "username VARCHAR(50)," + "password VARCHAR(50),"+ "isActive BOOLEAN,"+ "isDelete BOOLEAN)";
	        
	        try { 
	             Statement stmt = comm.createStatement();
	            stmt.executeUpdate(sql);
	            comm.close();
	            
	            return null;
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 public String createTableAccount() {
		 Connection comm = bd.getConnection();
		 String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
	                "account_id INT PRIMARY KEY AUTO_INCREMENT," +
	                "accountno BIGINT," +
	                "accountName VARCHAR(45)," +
	                "email VARCHAR(45)," +
	                "mobileNo BIGINT," +
	                "address VARCHAR(45)," +
	                "accountBalance DECIMAL(15,2)," +
	                "creditAmount DECIMAL(15,2)," +
	                "debitAmount DECIMAL(15,2))";
		 
		 try {
			 Statement stmt = comm.createStatement();
			 stmt.executeUpdate(sql);
			 comm.close();
			 return "Account table created successfully";	 
		 } 
		 catch (Exception e) {	 
			 e.printStackTrace();
			 return "Failed to create account table";
		 }
	 }
	 
	 public String createTableCreditCards() {
		    Connection comm = bd.getConnection();
		    String sql = "CREATE TABLE IF NOT EXISTS credit_cards (" +
		            "cardid BIGINT PRIMARY KEY AUTO_INCREMENT," +
		            "cardHolderName VARCHAR(100)," +
		            "cardNo BIGINT," +
		            "email VARCHAR(100)," +
		            "address VARCHAR(255)," +
		            "mobileNo BIGINT)";
		    
		    try {
		        Statement stmt = comm.createStatement();
		        stmt.executeUpdate(sql);
		        comm.close();
		        return "Credit cards table created successfully";
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "Failed to create credit cards table";
		    }
		}
	 
	    public String createTableLoans() {
		    Connection comm = bd.getConnection();
		    String sql = "CREATE TABLE IF NOT EXISTS loans (" +
		            "loan_id BIGINT PRIMARY KEY AUTO_INCREMENT," +
		            "name VARCHAR(100)," +
		            "address VARCHAR(255)," +
		            "mobileNo BIGINT," +
		            "loanNumber BIGINT," +
		            "email VARCHAR(100)," +
		            "loanDate DATE," +
		            "accountBalance BIGINT," +
		            "deposit BIGINT," +
		            "debit BIGINT)";
		    
		    try {
		        Statement stmt = comm.createStatement();
		        stmt.executeUpdate(sql);
		        comm.close();
		        return "Loans table created successfully";
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "Failed to create loans table";
		    }
		}
	    
	    public String createTableInsurance() {
	        Connection comm = bd.getConnection();
	        String sql = "CREATE TABLE IF NOT EXISTS insurance (" +
	                "policyId INT PRIMARY KEY AUTO_INCREMENT," +
	                "policyHolderName VARCHAR(100)," +
	                "dateOfBirth VARCHAR(20)," +
	                "gender VARCHAR(10)," +
	                "contactNumber VARCHAR(20)," +
	                "email VARCHAR(100)," +
	                "address VARCHAR(255)," +
	                "policyType VARCHAR(50)," +
	                "sumInsured DOUBLE," +
	                "premiumAmount DOUBLE," +
	                "preExistingDiseases VARCHAR(255)," +
	                "smoker BOOLEAN," +
	                "alcoholConsumer BOOLEAN," +
	                "policyStartDate VARCHAR(20)," +
	                "policyEndDate VARCHAR(20)," +
	                "creditAmount DOUBLE)";

	        try {
	            Statement stmt = comm.createStatement();
	            stmt.executeUpdate(sql);
	            comm.close();
	            return "Insurance table created successfully";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Failed to create insurance table";
	        }
	    }
}