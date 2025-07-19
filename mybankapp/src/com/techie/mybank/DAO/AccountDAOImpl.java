package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;
import com.techie.mybank.utils.BankDatabase;

public class AccountDAOImpl implements AccountDAO{
	
	BankDatabase bd=new BankDatabase();

	@Override
	public Account open(Account account) {
		Connection conn = bd.getConnection();

        try {
            Statement stmt = conn.createStatement();
            String sqlCreate = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "accountno BIGINT, " +
                    "accountname VARCHAR(45), " +
                    "email VARCHAR(45), " +
                    "mobileno BIGINT, " +
                    "address VARCHAR(100), " +
                    "accountBalance BIGINT, " +
                    "creditAmount BIGINT, " +
                    "debitAmount BIGINT)";
            stmt.execute(sqlCreate);
            
            String sqlInsert = "INSERT INTO accounts (accountno, accountName, email, mobileNo, address, accountBalance, creditAmount, debitAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, account.getAccountNo());
            pstmt.setString(2, account.getAccountName());
            pstmt.setString(3, account.getEmail());
            pstmt.setLong(4, account.getMobileno());
            pstmt.setString(5, account.getAddress());
            pstmt.setLong(6, account.getAccountBalance());
            pstmt.setLong(7, account.getCreditAmount());
            pstmt.setLong(8, account.getDebitAmount());
         
            int status = pstmt.executeUpdate();
            if(status>0) {
            	ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    account.setAccountid(generatedKeys.getLong(1));
                }
      
            String loginInsert = "INSERT INTO login (username, password, isActive, isDelete) VALUES (?, ?, ?, ?)";
            PreparedStatement loginStmt = conn.prepareStatement(loginInsert);
            String email = account.getEmail();
            String name = account.getAccountName();
            loginStmt.setString(1, email);
            loginStmt.setString(2, name); 
            loginStmt.setBoolean(3, true); 
            loginStmt.setBoolean(4, false); 
            loginStmt.executeUpdate();
            
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
	
		
	public Account view(Account account) {
		 return viewAccountById(account.getAccountid());
	}
	
	public Account viewAccountById(Long account_id) {
		Connection conn = bd.getConnection();
        Account account = null;

        try {
            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, account_id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                account = extractAccountFromResultSet(rs);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }	
		
	@Override
	public List<Account> viewAll(Account account) {
		 Connection conn = bd.getConnection();
	        List<Account> accounts = new ArrayList<>();

	        try {
	        	 String sql = "SELECT * FROM accounts";
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery();

	             while (rs.next()) {
	                 Account acc1 = new Account();
	                 acc1.setAccountid(rs.getLong("account_id"));
	                 acc1.setAccountNo(rs.getLong("accountno"));
	                 acc1.setAccountName(rs.getString("accountname"));
	                 acc1.setEmail(rs.getString("email"));
	                 acc1.setMobileno(rs.getLong("mobileno"));
	                 acc1.setAddress(rs.getString("address"));
	                 acc1.setAccountBalance(rs.getLong("accountBalance"));
	                 acc1.setCreditAmount(rs.getLong("creditAmount"));
	                 acc1.setDebitAmount(rs.getLong("debitAmount"));
	                 accounts.add(acc1);
	             }
	             conn.close();
	         } 
	        catch (Exception e) {
	             e.printStackTrace();
	         }

	         return accounts;
	    }

	@Override
	public Account update(Account account) {
		Connection conn = bd.getConnection();

        try {
            String sql = "UPDATE accounts SET mobileNo = ?, address = ? WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, account.getMobileno());
            pstmt.setString(2, account.getAddress());
            pstmt.setLong(3, account.getAccountid());

            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewAccountById(account.getAccountid());
    }

	@Override
	public Account delete(Account account) {
		Connection conn = bd.getConnection();

        try {
            String sql = "DELETE FROM accounts WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, account.getAccountid());
            pstmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

	@Override
	public Account deposit(Account account) {
		 Connection conn = bd.getConnection();

	        try {
	            String sql = "UPDATE accounts SET accountBalance = accountBalance + ?, creditAmount = ? WHERE account_id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setLong(1, account.getCreditAmount());
	            pstmt.setLong(2, account.getCreditAmount());
	            pstmt.setLong(3, account.getAccountid());
	            pstmt.executeUpdate();

	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return viewAccountById(account.getAccountid());
	    }

	@Override
	public Account debit(Account account) {
		 Connection conn = bd.getConnection();

	        try {
	            String sql = "UPDATE accounts SET accountBalance = accountBalance - ?, debitAmount = ? WHERE account_id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setLong(1, account.getDebitAmount());
	            pstmt.setLong(2, account.getDebitAmount());
	            pstmt.setLong(3, account.getAccountid());
	            pstmt.executeUpdate();

	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return viewAccountById(account.getAccountid());
	    }

	@Override
	public List<Transactions> transactions(Account account) {
	    List<Transactions> txList = new ArrayList<>();
	    Connection conn = bd.getConnection();

	    try {
	        String sql = "SELECT * FROM accounts WHERE account_id = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setLong(1, account.getAccountid());

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            long credit = rs.getLong("creditAmount");
	            long debit = rs.getLong("debitAmount");

	            if (credit > 0) {
	                Transactions creditTx = new Transactions();
	                creditTx.setAccountId(account.getAccountid());
	                creditTx.setType("Credit");
	                creditTx.setTransactionAmount(credit);
	                txList.add(creditTx);
	            }

	            if (debit > 0) {
	                Transactions debitTx = new Transactions();
	                debitTx.setAccountId(account.getAccountid());
	                debitTx.setType("Debit");
	                debitTx.setTransactionAmount(debit);
	                txList.add(debitTx);
	            }
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return txList;
	}
	
	@Override
	public long viewBalance(Account account) {
		 Connection conn = bd.getConnection();
	        long balance = 0;

	        try {
	            String sql = "SELECT accountBalance FROM accounts WHERE account_id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setLong(1, account.getAccountid());

	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                balance = rs.getLong("accountBalance");
	            }

	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return balance;
	    }
	private Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        Account acc = new Account();
        acc.setAccountid(rs.getLong("account_id"));
        acc.setAccountNo(rs.getLong("accountno"));
        acc.setAccountName(rs.getString("accountname"));
        acc.setEmail(rs.getString("email"));
        acc.setMobileno(rs.getLong("mobileno"));
        acc.setAddress(rs.getString("address"));
        acc.setAccountBalance(rs.getLong("accountBalance"));
        acc.setCreditAmount(rs.getLong("creditAmount"));
        acc.setDebitAmount(rs.getLong("debitAmount"));
        return acc;
    }
	}