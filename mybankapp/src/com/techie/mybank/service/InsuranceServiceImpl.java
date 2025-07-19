package com.techie.mybank.service;

import java.util.List;
import com.techie.mybank.DAO.InsuranceDAO;
import com.techie.mybank.DAO.InsuranceDAOImpl;
import com.techie.mybank.model.Insurance;

public class InsuranceServiceImpl implements InsuranceService {
	
	 InsuranceDAO ins = new InsuranceDAOImpl();

	    @Override
	    public Insurance open(Insurance insurance) {
	        return ins.open(insurance);
	    }

	    @Override
	    public List<Insurance> view() {
	        return ins.view();
	    }

	    @Override
	    public Insurance viewPolicyById(int policyId) {
	        return ins.viewPolicyById(policyId);
	    }

	    @Override
	    public void update(Insurance insurance) {
	        ins.update(insurance);
	    }

	    @Override
	    public void delete(Insurance insurance) {
	        ins.delete(insurance);
	    }
	    
	    @Override
	    public List<Insurance> viewInsuranceTransaction(int policyId) {
	        return ins.viewInsuranceTransaction(policyId);
	    }

	    @Override
	    public double viewInsuranceBalance(int policyId) {
	        return ins.viewInsuranceBalance(policyId);
	    }
	    
	    @Override
	    public void deposit(int policyId) {   
	        ins.deposit(policyId);
	    }
}