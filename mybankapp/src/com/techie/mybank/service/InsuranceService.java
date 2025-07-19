package com.techie.mybank.service;

import java.util.List;
import com.techie.mybank.model.Insurance;

public interface InsuranceService {
	
    public Insurance open(Insurance insurance);
    
    public List<Insurance> view();
    
    public Insurance viewPolicyById(int policyId);
    
    public void update(Insurance insurance);
    
    public void delete(Insurance insurance);
    
    public List<Insurance> viewInsuranceTransaction(int policyId);
    
    public double viewInsuranceBalance(int policyId);
    
    public void deposit(int policyId); 

}