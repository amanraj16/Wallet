package com.capgemini.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exceptions.MobileNoAlreadyExistException;
import com.capgemini.service.WalletServiceImpl;

public class MainApp {
		private static Scanner sc=new Scanner(System.in);
		private static WalletServiceImpl walletServiceImpl=new WalletServiceImpl();
		public static void main(String[] args) {
			showMenu();
		}
		private static void showMenu()
		{
			int choice;
			while(true)
			{
				System.out.println("***Please select your choice from below***");
				System.out.println("Press 1 to Create Account");
				System.out.println("Press 2 to Show Balance");
				System.out.println("Press 3 to Transfer Fund");
				System.out.println("Press 4 to Deposit Amount");
				System.out.println("Press 5 to Withdraw Amount");
				System.out.println("Press 6 to Exit the system");
				System.out.println("----Select your choice----");
				choice=sc.nextInt();
				switch(choice)
				{
				case 1:createAccount();
					   break;
				case 2:showBalance();
					   break;
				case 3:fundTransfer();
					   break;
				case 4:depositAmount();
					   break;
				case 5:withdrawAmount();
					   break;
				case 6:System.exit(0);
					   break;
				default:System.out.println("---SORRY, Wrong Choice Entered---");
						   
			}
			}
		}
			public static void createAccount()
			{
				System.out.println("Enter your NAME, MOBILE NUMBER and WALLET AMOUNT");
			   String name=sc.next();
			   String mobileno=sc.next();
			   BigDecimal amount=sc.nextBigDecimal();
			   int len=mobileno.length();
			   if(name==null||len!=10)
			   {
				   System.out.println("One of your entry is wrong...Please try again");
				   System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
					System.out.println();
			   }
			   else
			   {
				   try {
					   		Customer cust= walletServiceImpl.createAccount(name, mobileno,amount);
					   		System.out.println("Your account has been created successfully. Your details are as follows-->");
					   		System.out.println("NAME : "+cust.getName());
					   		System.out.println("MOBILE NUMBER : "+cust.getMobileno());
					   		System.out.println("WALLET BALANCE : "+cust.getWallet().getBalance());
					   		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
							System.out.println();
				   		}catch(Exception e)
				   		{
				   			System.out.println(e.getMessage());
				   			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
							System.out.println();
				   		}
			   }
		}
	private static void withdrawAmount() {
		System.out.println("Enter MOBILE NUMBER and WITHDRAW AMOUNT");
		String mobileno=sc.next();
		if(mobileno.length()!=10)
		{System.out.println("Enter a valid MOBILE NUMBER");
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println();
		}
		BigDecimal amount=sc.nextBigDecimal();
			try {
					Customer customer=walletServiceImpl.withdrawAmount(mobileno, amount);
					System.out.println("MOBILE NUMBER : "+customer.getMobileno());
					System.out.println("REMAINING BALANCE : "+customer.getWallet().getBalance());
					System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
					System.out.println();
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
					System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
					System.out.println();
				}
		}
	

	private static void depositAmount() {
		System.out.println("Enter MOBILE NUMBER and DESPOSIT AMOUNT");
		String mobileno=sc.next();
		if(mobileno.length()!=10)
		{System.out.println("Enter a valid MOBILE NUMBER");
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println();
		}
		BigDecimal amount=sc.nextBigDecimal();
			try {
				Customer customer=walletServiceImpl.depositAmount(mobileno, amount);
				System.out.println("MOBILE NUMBER : "+customer.getMobileno());
				System.out.println("UPDATED BALANCE : "+customer.getWallet().getBalance());
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
			}
		}
	

	private static void fundTransfer() {
		System.out.println("Enter SOURCE MOBILE NUMBER, TARGET MOBILE NUMBER and AMOUNT");
		String sMobileNo=sc.next();
		if(sMobileNo.length()!=10)
			{
			System.out.println("SOURCE MOBILE NUMBER is invalid");
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println();
			}
		try {
			Customer custS=walletServiceImpl.showBalance(sMobileNo);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println();
		}
		String tMobileNo=sc.next();
		 if(tMobileNo.length()!=10)
				{
			 System.out.println("TARGET MOBILE NUMBER is invalid");
			 System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
				
				}
		 try {
			 Customer custT=walletServiceImpl.showBalance(tMobileNo);
		 }catch(Exception e)
		 {
			 System.out.println(e.getMessage());
			 System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
		 }
		BigDecimal amount=sc.nextBigDecimal();
		try {
				Customer cust1=walletServiceImpl.withdrawAmount(sMobileNo, amount);
				Customer cust2=walletServiceImpl.depositAmount(tMobileNo, amount);
				System.out.println("Amount "+amount+" is successfully transfered.");
				System.out.println("REMAINING BALANCE : "+cust1.getWallet().getBalance());
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
			}
		}
	

	private static void showBalance() {
		System.out.println("Enter Mobile Number");
		String mobileno=sc.next();
		int len=mobileno.length();
		if(len!=10)
		{
			System.out.println("Please enter 10 digits of MOBILE NUMBER");
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println();
		}
		else
		{
			try {
				Customer cust=walletServiceImpl.showBalance(mobileno);
				System.out.println("Your BALANCE is : "+cust.getWallet().getBalance());
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
				System.out.println();
			}
		}
		
	}
	}
