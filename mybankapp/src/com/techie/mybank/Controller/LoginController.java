package com.techie.mybank.Controller;

import java.util.Scanner;

import com.techie.mybank.model.Login;
import com.techie.mybank.service.LoginService;
import com.techie.mybank.service.LoginServiceImpl;

public class LoginController {
	Scanner sc=new Scanner(System.in);
	LoginService los=new LoginServiceImpl();	
			
	public boolean login() {
		System.out.println("Please enter UserName:");
		String userName=sc.next();
	
		System.out.println("Please enter password:");
		String password=sc.next();
		
		Login log=new Login();
		log.setUserName(userName);
		log.setPassword(password);
				
		Login foundUser = los.findbyuserName(log);
		
		if (foundUser != null) {  
            if (foundUser.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
public void logout() {
	System.out.println("Successfully logged out");
   }
}