package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techie.mybank.model.Loans;
import com.techie.mybank.utils.BankDatabase;

public class LoanDAOImpl  implements LoanDAO{

	Scanner sc=new Scanner(System.in);
	
	BankDatabase bd=new BankDatabase();

	public Loans enrollment(Loans loan) {
		Connection conn = bd.getConnection();
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS loans ("
                    + "loan_id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100),"
                    + "loanNumber BIGINT,"
                    + "email VARCHAR(100),"
                    + "mobileNo BIGINT,"
                    + "address VARCHAR(200),"
                    + "loanDate VARCHAR(50),"
                    + "accountBalance BIGINT DEFAULT 0,"
                    + "deposit BIGINT DEFAULT 0,"
                    + "debit BIGINT DEFAULT 0)";
            conn.createStatement().execute(createTableSQL);

            String insertSQL = "INSERT INTO loans (name, loanNumber, email, mobileNo, address, loanDate, accountBalance, deposit, debit) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, loan.getName());
            pstmt.setLong(2, loan.getLoanNumber());
            pstmt.setString(3, loan.getEmail());
            pstmt.setLong(4, loan.getMobileNo());
            pstmt.setString(5, loan.getAddress());
            pstmt.setString(6, loan.getLoanDate());
            pstmt.setLong(7, loan.getAccountBalance());
            pstmt.setLong(8, loan.getDeposit());
            pstmt.setLong(9, loan.getDebit());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                loan.setLoan_id(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loan;
	}

	public Loans update(Loans loan) {
		try (Connection conn = bd.getConnection()) {
            String sql = "UPDATE loans SET mobileNo = ?, address = ? WHERE loan_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, loan.getMobileNo());
            pstmt.setString(2, loan.getAddress());
            pstmt.setLong(3, loan.getLoan_id());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ViewAccountById(loan.getLoan_id());
	}
	
	public Loans deposit(long loan_id, long amount) {
		    try (Connection conn = bd.getConnection()) {

		        String sql = "UPDATE loans SET accountBalance = accountBalance + ?, deposit = deposit + ? WHERE loan_id = ?";
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        pstmt.setLong(1, amount);
		        pstmt.setLong(2, amount); 
		        pstmt.setLong(3, loan_id);
		        pstmt.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return ViewAccountById(loan_id);
		}

	
	public Loans debit(long loan_id, long amount) {
		    try (Connection conn = bd.getConnection()) {

		        String selectSQL = "SELECT accountBalance FROM loans WHERE loan_id = ?";
		        PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
		        selectStmt.setLong(1, loan_id);
		        ResultSet rs = selectStmt.executeQuery();

		        if (rs.next()) {
		            long currentBalance = rs.getLong("accountBalance");

		            if (currentBalance >= amount) {
		               
		                String updateSQL = "UPDATE loans SET accountBalance = accountBalance - ?, debit = debit + ? WHERE loan_id = ?";
		                PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
		                updateStmt.setLong(1, amount);
		                updateStmt.setLong(2, amount);
		                updateStmt.setLong(3, loan_id);
		                updateStmt.executeUpdate();
		            } else {
		                System.out.println("Insufficient balance. Debit failed for Loan ID: " + loan_id);
		                return null;
		            }
		        } else {
		            System.out.println("Loan ID not found.");
		            return null;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return ViewAccountById(loan_id); 
		}


	@Override
	public Loans ViewAccountById(long loan_id) {
		Loans loan = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM loans WHERE loan_id = ?")) {
            pstmt.setLong(1, loan_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loan = extractLoanFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loan;
	}

	@Override
	public List<Loans> view() {
		List<Loans> loanList = new ArrayList<>();
        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM loans");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                loanList.add(extractLoanFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanList;
	}

	@Override
	public List<String> viewAllTransactions(long loan_id) {
		    List<String> transactionDetails = new ArrayList<>();
		    String query = "SELECT loan_id, deposit, debit, accountBalance FROM loans WHERE loan_id = ?";
		    
		    try (Connection con = bd.getConnection();
		         PreparedStatement ps = con.prepareStatement(query)) {
		         
		        ps.setLong(1, loan_id);
		        ResultSet rs = ps.executeQuery();
		        
		        if (rs.next()) {
		            long deposit = rs.getLong("deposit");
		            long debit = rs.getLong("debit");
		            long balance = rs.getLong("accountBalance");

		            transactionDetails.add("Loan ID: " + loan_id);
		            transactionDetails.add("Total Deposited: ₹" + deposit);
		            transactionDetails.add("Total Debited: ₹" + debit);
		            transactionDetails.add("Current Loan Balance: ₹" + balance);
		        } else {
		            transactionDetails.add("No loan account found with ID: " + loan_id);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        transactionDetails.add("Error retrieving transaction details.");
		    }

		    return transactionDetails;
		}

	@Override
	public Loans delete(Loans loan) {
		try (Connection con = bd.getConnection();
		         PreparedStatement ps = con.prepareStatement("DELETE FROM loans WHERE loan_id = ?")) {
		        ps.setLong(1, loan.getLoan_id());
		        int rows = ps.executeUpdate();
		        if (rows > 0) return loan;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
	}

	@Override
	public Loans closure(long loan_id) {
		 try (Connection conn = bd.getConnection()) {
		        String sql = "UPDATE loans SET accountBalance = 0, deposit = 0, debit = 0 WHERE loan_id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setLong(1, loan_id);
		        int rows = ps.executeUpdate();

		        if (rows > 0) {
		            System.out.println("Loan ID: " + loan_id + " has been closed successfully.");
		            return ViewAccountById(loan_id);
		        } else {
		            System.out.println("Loan closure failed. No such loan ID.");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
	}


    @Override
    public Loans payment(Long loan_id) {
        try (Connection conn = bd.getConnection()) {
            Loans loan = ViewAccountById(loan_id);
            if (loan != null) {
                long deposit = loan.getDeposit();
                long currentBalance = loan.getAccountBalance();

                if (deposit > 0 && deposit <= currentBalance) {
                    long newBalance = currentBalance - deposit;
                    long newDebit = loan.getDebit() + deposit;

                    String sql = "UPDATE loans SET accountBalance = ?, debit = ? WHERE loan_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setLong(1, newBalance);
                    pstmt.setLong(2, newDebit);
                    pstmt.setLong(3, loan_id);

                    int rows = pstmt.executeUpdate();
                    if (rows > 0) {
                        loan.setAccountBalance(newBalance);
                        loan.setDebit(newDebit);
                        return loan;
                    }
                } else {
                    System.out.println("Payment failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


	@Override
	public long viewBalance(long loan_id) {
		try (Connection conn = bd.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement("SELECT accountBalance FROM loans WHERE loan_id = ?")) {
		        pstmt.setLong(1, loan_id);
		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            return rs.getLong("accountBalance");
		        } else {
		            System.out.println("Loan account not found for ID: " + loan_id);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return -1;
	}
	
	private Loans extractLoanFromResultSet(ResultSet rs) throws SQLException {
        Loans loan = new Loans();
        loan.setLoan_id(rs.getLong("loan_id"));
        loan.setName(rs.getString("name"));
        loan.setLoanNumber(rs.getLong("loanNumber"));
        loan.setEmail(rs.getString("email"));
        loan.setMobileNo(rs.getLong("mobileNo"));
        loan.setAddress(rs.getString("address"));
        loan.setLoanDate(rs.getString("loanDate"));
        loan.setAccountBalance(rs.getLong("accountBalance"));
        loan.setDeposit(rs.getLong("deposit"));
        loan.setDebit(rs.getLong("debit"));
        return loan;
    }
}	