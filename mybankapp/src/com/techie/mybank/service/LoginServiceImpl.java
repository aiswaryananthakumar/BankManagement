package com.techie.mybank.service;

import com.techie.mybank.DAO.LoginDAO;
import com.techie.mybank.DAO.LoginDAOImpl;
import com.techie.mybank.model.Login;

public class LoginServiceImpl implements LoginService {

	LoginDAO lod=new LoginDAOImpl();
	
	public Login create(Login log) {
		 return lod.create(log);
	
	}

	@Override
	public Login findbyuserName(Login log) {
		Login foundUser = lod.findbyuserName(log);
        if (foundUser == null) {  
            System.out.println("No user found with username: " + log.getUserName());
        }
        return foundUser;
	}

	@Override
	public Login activate(Login log) {
		if (log == null) return null; 
        log.setIsActive(true);
        return lod.update(log);
        }

	@Override
	public Login deactivate(Login log) {
		 if (log == null) return null; 
	        log.setIsActive(false);
	        return lod.update(log);
	}

	@Override
	public Login delete(Login log) {
		Login foundUser = findbyuserName(log);
        if (foundUser != null) {
            return lod.delete(foundUser);
        }
        return null;
	}

	@Override
	public Login update(Login log) {
		if (log == null) return null; 
        return lod.update(log);
	}
}
