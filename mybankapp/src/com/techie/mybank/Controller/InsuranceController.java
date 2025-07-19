package com.techie.mybank.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.techie.mybank.model.Insurance;
import com.techie.mybank.service.InsuranceService;
import com.techie.mybank.service.InsuranceServiceImpl;

public class InsuranceController {
		    
		    Scanner sc = new Scanner(System.in);
		    InsuranceService insuranceService = new InsuranceServiceImpl();

		    public void home() {
		        System.out.println("Please choose the desired Option");
		        System.out.println("===========================================");
		        System.out.println("Please select Option 1 if you want to open an insurance");
		        System.out.println("Please select Option 2 if you want to view all insurance");
		        System.out.println("Please select Option 3 if you want to View Insurance Policy by Policy ID");
		        System.out.println("Please select Option 4 if you want to update an insurance");
		        System.out.println("Please select Option 5 if you want to delete an insurance");
		        System.out.println("Please select Option 6 if you want to View Insurance Transactions in insurance");
		        System.out.println("Please select Option 7 if you want to View Insurance Balance in insurance");
		        System.out.println("Please select Option 8 if you want to Deposit Amount in Insurance");
		        System.out.println("============================================");

		        int option = sc.nextInt();
		        
		        switch (option) {
		            case 1:System.out.println("You have selected Insurance Opening process");
		                openPolicy();
		                break;
		            case 2:System.out.println("You have selected ViewAll Insurance process");
		                viewAllPolicies();
		                break;
		            case 3:System.out.println("You have selected View Insurance Policy by Policy ID process");
		                viewPolicyById();
		                break;
		            case 4:System.out.println("You have selected Insurance Update process");
		                updatePolicy();
		                break;
		            case 5:System.out.println("You have selected Insurance Delete process");
		                deletePolicy();
		                break;
		            case 6:System.out.println("You have selected View Insurance Transaction process");
		                viewInsuranceTransactions();
		                break;
		            case 7:System.out.println("You have selected View Insurance Balance process");
		                viewInsuranceBalance();
		                break;
		            case 8:System.out.println("You have selected Deposit Amount in Insurance process");
		            	depositAmount(); 
		            	break;
		            default:
		                System.out.println("Please select the correct desired option");
		                break;
		        }
		    }

		    private void openPolicy() {
		    	System.out.println("\nYou have choosen Insurance Opening Process");
		    	System.out.println("=========================================");
		        Insurance insurance = new Insurance();
		        
		        sc.nextLine(); 
		        
		        while (true) {
		            System.out.print("Enter Policy Holder Name: ");
		            String policyHolderName = sc.nextLine();
		            if (isValidPolicyHolderName(policyHolderName)) {
		                insurance.setPolicyHolderName(policyHolderName);
		                break;
		            } else {
		                System.out.println("Invalid Policy Holder Name");
		            }
		        }

		        System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
		        insurance.setDateOfBirth(sc.nextLine());

		        System.out.print("Enter Gender: ");
		        insurance.setGender(sc.nextLine());

		        while (true) {
		            System.out.print("Enter Contact Number: ");
		            String contact = sc.nextLine();
		            if (isValidPhoneNumber(contact)) {
		                insurance.setContactNumber(contact);
		                break;
		            } else {
		                System.out.println("Invalid Contact Number");
		            }
		        }

		        while (true) {
		            System.out.print("Enter Email: ");
		            String email = sc.nextLine();
		            if (isValidEmail(email)) {
		                insurance.setEmail(email);
		                break;
		            } else {
		                System.out.println("Invalid Email Address");
		            }
		        }

		        System.out.print("Enter Address: ");
		        insurance.setAddress(sc.nextLine());

		        System.out.print("Enter Policy Type: ");
		        insurance.setPolicyType(sc.nextLine());

		        System.out.print("Enter Sum Insured: ");
		        insurance.setSumInsured(readPositiveDouble("Enter Sum Insured: "));

		        System.out.print("Enter Premium Amount: ");
		        insurance.setPremiumAmount(readPositiveDouble("Enter Premium Amount: "));

		        sc.nextLine(); 
		        System.out.print("Enter Pre-existing Diseases: ");
		        insurance.setPreExistingDiseases(sc.nextLine());

		        System.out.print("Are you a smoker? (true/false): ");
		        insurance.setSmoker(sc.nextBoolean());

		        System.out.print("Are you an alcohol consumer? (true/false): ");
		        insurance.setAlcoholConsumer(sc.nextBoolean());
		        sc.nextLine();
		        
		        System.out.print("Enter Policy Start Date (yyyy-mm-dd): ");
		        insurance.setPolicyStartDate(sc.nextLine());

		        System.out.print("Enter Policy End Date (yyyy-mm-dd): ");
		        insurance.setPolicyEndDate(sc.nextLine());
		        
		        System.out.print("Enter Credit Amount: ");
		        insurance.setCreditAmount(readPositiveDouble("Enter Credit Amount: "));
		        
		        System.out.println("Enter Account ID: ");
		        insurance.setAccountID(sc.nextInt());
		        sc.nextLine();

		        Insurance saved = insuranceService.open(insurance);
		        System.out.println("Policy opened successfully! Policy ID: " + saved.getPolicyId());
		        
		        home();
		    }

		    private void viewAllPolicies() {
		    	System.out.println("\nYou have selected ViewAll Insurance process");
		    	System.out.println("=========================================");
		        
		        List<Insurance> policies = insuranceService.view();
		        if (policies.isEmpty()) {
		            System.out.println("No insurance policies found");
		        } else {
		            policies.forEach(System.out::println);
		        }
		        home();
		    }

		    private void viewPolicyById() {
		    	System.out.println("\nYou have selected View Insurance Policy by Policy ID process");
		    	System.out.println("=========================================");
		        
		        System.out.print("Enter Policy ID: ");
		        int policyId = sc.nextInt();

		        Insurance insurance = insuranceService.viewPolicyById(policyId);
		        if (insurance != null) {
		            System.out.println(insurance);
		        } else {
		            System.out.println("Policy not found!");
		        }
		        home();
		    }

		    private void updatePolicy() {
		    	System.out.println("\nYou have selected Insurance Update process");
		    	System.out.println("=========================================");
		      
		        Insurance insurance = new Insurance();

		        System.out.print("Enter Policy ID to update: ");
		        insurance.setPolicyId(sc.nextInt());

		        sc.nextLine(); 
		        System.out.print("Enter updated Contact Number: ");
		        insurance.setContactNumber(sc.nextLine());
		        if (!isValidPhoneNumber(insurance.getContactNumber())) {
		            System.out.println("Invalid Contact Number! Must be exactly 10 digits.");
		            return;
		        }

		        System.out.print("Enter updated Address: ");
		        insurance.setAddress(sc.nextLine());

		        double newCreditAmount = readPositiveDouble("Enter New Credit Amount: ");
		        insurance.setCreditAmount(newCreditAmount);
		        
		        insuranceService.update(insurance);
		        System.out.println("Policy updated successfully!");

		        home();
		    }

		    private void deletePolicy() {
		    	System.out.println("\nYou have selected Insurance Delete process");
		    	System.out.println("=========================================");
		        
		        System.out.print("Enter Policy ID to delete: ");
		        int policyId = sc.nextInt();

		        Insurance insurance = new Insurance();
		        insurance.setPolicyId(policyId);

		        insuranceService.delete(insurance);
		        System.out.println("Policy deleted successfully!");
		        
		        home();
		    }
		    
		    private void viewInsuranceTransactions() {
		    	System.out.println("\nYou have selected View Insurance Transaction process");
		    	System.out.println("=========================================");
		        
		        List<Insurance> policies = insuranceService.view();

		        if (policies.isEmpty()) {
		            System.out.println("No transactions found.");
		        } else {
		            policies = policies.stream()
		                    .sorted((p1, p2) -> p1.getPolicyStartDate().compareTo(p2.getPolicyStartDate()))
		                    .collect(Collectors.toList());

		            System.out.println("Month\t\tCredit Amount");
		            System.out.println("==============================");
		            for (Insurance insurance : policies) {
		                System.out.println(insurance.getPolicyStartDate() + "\t" + insurance.getCreditAmount());
		            }
		        }
		        home();
		    }
		    
		    private void viewInsuranceBalance() {
		    	System.out.println("\nYou have selected View Insurance Balance process");
		    	System.out.println("=========================================");
		        
		        System.out.print("Enter Policy ID to view balance: ");
		        int policyId = sc.nextInt();
		        
		        double balance = insuranceService.viewInsuranceBalance(policyId);

		        System.out.println("Insurance Balance for Policy ID " + policyId + " is: " + balance);

		        home();

		    }
		    
		    private void depositAmount() {
		        System.out.println("\nYou have selected Deposit Amount in Insurance process");
		        System.out.println("=========================================");
		        
		        System.out.print("Enter Policy ID to deposit: ");
		        int policyId = sc.nextInt();

		        insuranceService.deposit(policyId);
		        System.out.println("Monthly Credit Amount deposited successfully!");

		        home();
		    }
		    
		    private boolean isValidPhoneNumber(String number) {
		        return number != null && number.matches("\\d{10}");
		    }

		    private boolean isValidEmail(String email) {
		        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
		    }
		    
		    private boolean isValidPolicyHolderName(String policyHolderName) {
		        return policyHolderName != null && policyHolderName.matches("[A-Z . a-z]+");
		    }
		    
		    private double readPositiveDouble(String prompt) {
		        double value;
		        while (true) {
		            try {
		                System.out.print(prompt);
		                value = Double.parseDouble(sc.nextLine());
		                if (value > 0) {
		                    return value;
		                } else {
		                    System.out.println("Value must be greater than 0");
		                }
		            } catch (NumberFormatException e) {
		                System.out.println("Invalid number! Please enter a numeric value");
		            }
		        }
		    }
	}