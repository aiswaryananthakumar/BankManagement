package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.techie.mybank.model.Insurance;
import com.techie.mybank.utils.BankDatabase;

public class InsuranceDAOImpl implements InsuranceDAO {

	    BankDatabase bd = new BankDatabase();

	    @Override
	    public Insurance open(Insurance insurance) {
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "INSERT INTO insurance (policyHolderName, dateOfBirth, gender, contactNumber, email, address, policyType, sumInsured, premiumAmount, preExistingDiseases, smoker, alcoholConsumer, policyStartDate, policyEndDate, creditAmount, accountID) "
	                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            
	            ps.setString(1, insurance.getPolicyHolderName());
	            ps.setString(2, insurance.getDateOfBirth());
	            ps.setString(3, insurance.getGender());
	            ps.setString(4, insurance.getContactNumber());
	            ps.setString(5, insurance.getEmail());
	            ps.setString(6, insurance.getAddress());
	            ps.setString(7, insurance.getPolicyType());
	            ps.setDouble(8, insurance.getSumInsured());
	            ps.setDouble(9, insurance.getPremiumAmount());
	            ps.setString(10, insurance.getPreExistingDiseases());
	            ps.setBoolean(11, insurance.isSmoker());
	            ps.setBoolean(12, insurance.isAlcoholConsumer());
	            ps.setString(13, insurance.getPolicyStartDate());
	            ps.setString(14, insurance.getPolicyEndDate());
	            ps.setDouble(15, insurance.getCreditAmount());
	            ps.setInt(16, insurance.getAccountID());

	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                insurance.setPolicyId(rs.getInt(1));
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return insurance;
	    }

	    @Override
	    public List<Insurance> view() {
	        List<Insurance> insuranceList = new ArrayList<>();
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "SELECT * FROM insurance";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Insurance insurance = new Insurance();
	                insurance.setPolicyId(rs.getInt("policyId"));
	                insurance.setPolicyHolderName(rs.getString("policyHolderName"));
	                insurance.setDateOfBirth(rs.getString("dateOfBirth"));
	                insurance.setGender(rs.getString("gender"));
	                insurance.setContactNumber(rs.getString("contactNumber"));
	                insurance.setEmail(rs.getString("email"));
	                insurance.setAddress(rs.getString("address"));
	                insurance.setPolicyType(rs.getString("policyType"));
	                insurance.setSumInsured(rs.getDouble("sumInsured"));
	                insurance.setPremiumAmount(rs.getDouble("premiumAmount"));
	                insurance.setPreExistingDiseases(rs.getString("preExistingDiseases"));
	                insurance.setSmoker(rs.getBoolean("smoker"));
	                insurance.setAlcoholConsumer(rs.getBoolean("alcoholConsumer"));
	                insurance.setPolicyStartDate(rs.getString("policyStartDate"));
	                insurance.setPolicyEndDate(rs.getString("policyEndDate"));
	                insurance.setCreditAmount(rs.getDouble("creditAmount"));
	                insurance.setAccountID(rs.getInt("accountID"));
	                insuranceList.add(insurance);
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return insuranceList;
	    }

	    @Override
	    public Insurance viewPolicyById(int policyId) {
	        Insurance insurance = null;
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "SELECT * FROM insurance WHERE policyId = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, policyId);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                insurance = new Insurance();
	                insurance.setPolicyId(rs.getInt("policyId"));
	                insurance.setPolicyHolderName(rs.getString("policyHolderName"));
	                insurance.setDateOfBirth(rs.getString("dateOfBirth"));
	                insurance.setGender(rs.getString("gender"));
	                insurance.setContactNumber(rs.getString("contactNumber"));
	                insurance.setEmail(rs.getString("email"));
	                insurance.setAddress(rs.getString("address"));
	                insurance.setPolicyType(rs.getString("policyType"));
	                insurance.setSumInsured(rs.getDouble("sumInsured"));
	                insurance.setPremiumAmount(rs.getDouble("premiumAmount"));
	                insurance.setPreExistingDiseases(rs.getString("preExistingDiseases"));
	                insurance.setSmoker(rs.getBoolean("smoker"));
	                insurance.setAlcoholConsumer(rs.getBoolean("alcoholConsumer"));
	                insurance.setPolicyStartDate(rs.getString("policyStartDate"));
	                insurance.setPolicyEndDate(rs.getString("policyEndDate"));
	                insurance.setCreditAmount(rs.getDouble("creditAmount"));
	                insurance.setAccountID(rs.getInt("accountID"));
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return insurance;
	    }

	    @Override
	    public void update(Insurance insurance) {
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "UPDATE insurance SET contactNumber = ?, address = ?, creditAmount = ? WHERE policyId = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, insurance.getContactNumber());
	            ps.setString(2, insurance.getAddress());
	            ps.setDouble(3, insurance.getCreditAmount());
	            ps.setInt(4, insurance.getPolicyId());
	            ps.executeUpdate();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(Insurance insurance) {
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "DELETE FROM insurance WHERE policyId = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, insurance.getPolicyId());
	            ps.executeUpdate();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Override
	    public List<Insurance> viewInsuranceTransaction(int policyId) {
	        List<Insurance> transactions = new ArrayList<>();
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "SELECT * FROM insurance WHERE policyId = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, policyId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Insurance insurance = new Insurance();
	                insurance.setPolicyId(rs.getInt("policyId"));
	                insurance.setCreditAmount(rs.getDouble("creditAmount"));
	                insurance.setAccountID(rs.getInt("accountID"));
	                insurance.setPolicyStartDate(rs.getString("policyStartDate"));
	                insurance.setPolicyEndDate(rs.getString("policyEndDate"));
	                transactions.add(insurance);
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return transactions;
	    }
	    
	    @Override
	    public double viewInsuranceBalance(int policyId) {
	        double totalBalance = 0;
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "SELECT SUM(creditAmount) AS total FROM insurance WHERE policyId = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, policyId);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                totalBalance = rs.getDouble("total");
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return totalBalance;
	    }
	    
	    @Override
	    public void deposit(int policyId) {
	        try {
	            Connection conn = bd.getConnection();
	            String sql = "UPDATE insurance SET creditAmount = creditAmount + ? WHERE policyId = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            
	            Insurance insurance = viewPolicyById(policyId); 
	            if (insurance != null) {
	                double monthlyCredit = insurance.getCreditAmount(); 
	                
	                ps.setDouble(1, monthlyCredit); 
	                ps.setInt(2, policyId);
	                int rows = ps.executeUpdate();
	                
	                if (rows > 0) {
	                    System.out.println("Monthly deposit successful for Policy ID: " + policyId);
	                } else {
	                    System.out.println("Deposit failed");
	                }
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}