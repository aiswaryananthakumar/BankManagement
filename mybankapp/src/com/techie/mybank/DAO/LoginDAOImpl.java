package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techie.mybank.model.Login;
import com.techie.mybank.utils.BankDatabase;

public class LoginDAOImpl implements LoginDAO{
	
	BankDatabase bd=new BankDatabase();
	
	@Override
	public Login findbyuserName(Login log) {
	
		String sql = "SELECT * FROM login WHERE username = ?  AND password = ? AND isActive = 1 AND isDelete = 0";

        try (Connection conn = bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, log.getUserName());
            pstmt.setString(2, log.getPassword());
          
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Login foundLogin = new Login();
                foundLogin.setUserName(rs.getString("username"));
                foundLogin.setPassword(rs.  getString("password"));
                foundLogin.setIsActive(rs.getBoolean("isActive"));
                foundLogin.setIsDelete(rs.getBoolean("isDelete"));
                
                System.out.println("Login Successfull!");
                return foundLogin; 
            }
            else {
                System.out.println("User not found in database.");
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public Login activate(Login log) {
		return update(log);
	}

	@Override
	public Login deactivate(Login log) {
		return update(log);
	}
	
	@Override
	public Login delete(Login log) {
		String sql = "DELETE FROM login WHERE username = ? AND password = ? AND isActive = 1 AND isDelete = 0";

        try (Connection comm = bd.getConnection();
             PreparedStatement pstmt = comm.prepareStatement(sql)) {

            pstmt.setString(1, log.getUserName());
            pstmt.setString(2, log.getPassword());

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
                return log; 
            }
            else {
                System.out.println("Delete failed! User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	
	public Login create(Login log) {
		 String sql = "INSERT INTO login (username, password, isActive, isDelete) VALUES (?, ?, ?, ?)";

	        try (Connection comm = bd.getConnection();
	             PreparedStatement pstmt = comm.prepareStatement(sql)) {

	            pstmt.setString(1, log.getUserName());
	            pstmt.setString(2, log.getPassword());
	            pstmt.setBoolean(3, log.getIsActive());
	            pstmt.setBoolean(4, log.getIsDelete());

	            int rowsInserted = pstmt.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("User created successfully!");
	                return log; 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	@Override
	public Login update(Login log) {
		String sql = "UPDATE login SET isActive = ?, isDelete = ? WHERE username = ? AND password = ?";

        try (Connection comm = bd.getConnection();
             PreparedStatement pstmt = comm.prepareStatement(sql)) {
        	
        	pstmt.setString(1, log.getPassword());
            pstmt.setBoolean(2, log.getIsActive());
            pstmt.setBoolean(3, log.getIsDelete());
            pstmt.setString(4, log.getUserName());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
            	System.out.println("User details updated successfully!");
                return log;
            }
            else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	}
