package com.techie.mybank.service;

import com.techie.mybank.model.Login;

public interface LoginService {

    public Login create(Login log);
	public Login update(Login log);
	public Login findbyuserName(Login log);
	public Login activate(Login log);
	public Login deactivate(Login log);
	public Login delete(Login log);
	
}
