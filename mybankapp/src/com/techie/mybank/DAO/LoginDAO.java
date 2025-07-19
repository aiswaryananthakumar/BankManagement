package com.techie.mybank.DAO;

import com.techie.mybank.model.Login;

public interface LoginDAO {

	
	public Login update(Login log);
	public Login findbyuserName(Login log);
	public Login activate(Login log);
	public Login deactivate(Login log);
	public Login delete(Login log);
	public Login create(Login log);
	
	
	
	
	
}
