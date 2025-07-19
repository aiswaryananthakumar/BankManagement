package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.techie.mybank.model.CreditCards;
import com.techie.mybank.utils.BankDatabase;

public class CardDAOImpl implements CardDAO {

	BankDatabase bd=new BankDatabase();

	@Override
	public CreditCards enrollment(CreditCards cc) {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS creditcard_acc (" +
                "card_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cardHolderName VARCHAR(45), " +
                "cardNo BIGINT, " +
                "email VARCHAR(45), " +
                "mobileNo BIGINT, " +
                "address VARCHAR(100), " +
                "amount BIGINT DEFAULT 0)";
        String sqlInsert = "INSERT INTO creditcard_acc(cardHolderName, cardNo, email, mobileNo, address, amount) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection comm = bd.getConnection();
             Statement stmt = comm.createStatement();
             PreparedStatement pstmt = comm.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.execute(sqlCreate);

            pstmt.setString(1, cc.getCardHolderName());
            pstmt.setLong(2, cc.getCardNo());
            pstmt.setString(3, cc.getEmail());
            pstmt.setLong(4, cc.getMobileNo());
            pstmt.setString(5, cc.getAddress());
            pstmt.setLong(6, cc.getAmount());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                cc.setCardid(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cc;
		}
	
	@Override
	public List<CreditCards> viewAll() {
		 List<CreditCards> cardList = new ArrayList<>();
		    String sql = "SELECT * FROM creditcard_acc";

		    try (Connection conn = bd.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery()) {

		        while (rs.next()) {
		            CreditCards cc = new CreditCards();
		            cc.setCardid(rs.getLong("card_id"));
		            cc.setCardHolderName(rs.getString("cardHolderName"));
		            cc.setCardNo(rs.getLong("cardno"));
		            cc.setEmail(rs.getString("email"));
		            cc.setMobileNo(rs.getLong("mobileNo"));
		            cc.setAddress(rs.getString("address"));
		            cc.setAmount(rs.getLong("amount"));
		            cardList.add(cc);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return cardList;
	}
	
	@Override
	public CreditCards viewByAccountId(Long card_id) {
		 CreditCards cc = new CreditCards();
	        String sql = "SELECT cardHolderName, cardNo, email, mobileNo, address, amount FROM creditcard_acc WHERE card_id = ?";

	        try (Connection conn = bd.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setLong(1, card_id);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                cc.setCardid(card_id);
	                cc.setCardHolderName(rs.getString("cardHolderName"));
	                cc.setCardNo(rs.getLong("cardNo"));
	                cc.setEmail(rs.getString("email"));
	                cc.setMobileNo(rs.getLong("mobileNo"));
	                cc.setAddress(rs.getString("address"));
	                cc.setAmount(rs.getLong("amount"));
	            }
	            else {
	                System.out.println("No account found with this ID.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return cc;
	}

	public CreditCards update(CreditCards cc) {
		String sql = "UPDATE creditcard_acc SET mobileNo = ? WHERE address = ?";
        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, cc.getMobileNo());
            pstmt.setString(2, cc.getAddress());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewByAccountId(cc.getCardid());
	}


	public CreditCards delete(CreditCards cc) {
		String sql = "DELETE FROM creditcard_acc WHERE card_id = ?";
        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, cc.getCardid());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cc;
	}

	@Override
	public CreditCards deposit(CreditCards cc) {
		String sql = "UPDATE creditcard_acc SET amount = ? WHERE card_id = ?";
        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, cc.getAmount());
            pstmt.setLong(2, cc.getCardid());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewByAccountId(cc.getCardid());
    }
	
	 @Override
	    public CreditCards debit(CreditCards cc) {
		 String selectSql = "SELECT amount FROM creditcard_acc WHERE card_id = ?";
		    String updateSql = "UPDATE creditcard_acc SET amount = ? WHERE card_id = ?";

		    try (Connection conn = bd.getConnection();
		         PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
		         PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {

		        selectPstmt.setLong(1, cc.getCardid());
		        ResultSet rs = selectPstmt.executeQuery();
		        
		        if (rs.next()) {
		            long currentAmount = rs.getLong("amount");
		            long newAmount = currentAmount - cc.getAmount();

		            if (newAmount < 0) {
		                System.out.println("Insufficient balance.");
		                return null;
		            }

		            updatePstmt.setLong(1, newAmount);
		            updatePstmt.setLong(2, cc.getCardid());
		            updatePstmt.executeUpdate();

		            cc.setAmount(newAmount);
		        } else {
		            System.out.println("Card not found.");
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return cc;
	    }

	@Override
	public List<String> viewTransactions(Long cardId) {
		List<String> transactions = new ArrayList<>();
		String sql = "SELECT cardHolderName, amount FROM creditcard_acc WHERE card_id = ?";

        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, cardId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String cardHolderName = rs.getString("cardHolderName");
                long currentAmount = rs.getLong("amount");
                transactions.add("Cardholder Name: " + cardHolderName);
                transactions.add("Initial Amount: " + currentAmount);
                transactions.add("Deposit Amount: " + currentAmount);
                transactions.add("Debit Amount: " + (currentAmount - 1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
	}
}
