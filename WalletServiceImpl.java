package com.capgemini.service;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNoAlreadyExistException;
import com.capgemini.exceptions.MobileNoDoesNotExistException;
import com.capgmini.repo.WalletRepo;
import com.capgmini.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {
	public WalletServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	WalletRepo walletRepo=new WalletRepoImpl();
	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}
	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount)throws MobileNoAlreadyExistException {
		Customer cust=walletRepo.findOne(mobileno);
		
		if(cust==null)
		{
			Wallet wallet=new Wallet();
			wallet.setBalance(amount);
			Customer customer=new Customer();
			customer.setMobileno(mobileno);
			customer.setName(name);
			customer.setWallet(wallet);
			if(walletRepo.save(customer))	
				return customer;
		}	
		else
		throw new MobileNoAlreadyExistException("This Mobile Number already exist");
		return null;
	}

	@Override
	public Customer showBalance(String mobileno)throws MobileNoDoesNotExistException {
		Customer cust=walletRepo.findOne(mobileno);
		if(cust!=null)
			return cust;
		throw new MobileNoDoesNotExistException("This Mobile Number Does not exist");
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)throws InsufficientBalanceException,MobileNoDoesNotExistException {
		Wallet wallet=new Wallet();
		Wallet wallet1=new Wallet();
		Customer cust1=walletRepo.findOne(sourceMobileNo);
		if(cust1!=null)
		{
			Customer cust2=walletRepo.findOne(targetMobileNo);
				if(cust2!=null)
				{
					if((cust1.getWallet().getBalance().compareTo(amount))>=0)
					{
						wallet.setBalance(cust1.getWallet().getBalance().subtract(amount));
						cust1.setWallet(wallet);
						wallet1.setBalance(cust2.getWallet().getBalance().add(amount));
						cust2.setWallet(wallet1);
						return cust1;
					}
					throw new InsufficientBalanceException("Insufficient Balance");
				}
				throw new MobileNoDoesNotExistException("This Mobile Number does not exist");
		
	}
		throw new MobileNoDoesNotExistException("This Mobile Number does not exist");
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNoDoesNotExistException {
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer!=null)
		{
			Wallet wallet=new Wallet();
			wallet.setBalance(customer.getWallet().getBalance().add(amount));
			customer.setWallet(wallet);
			return customer;
		}
		throw new MobileNoDoesNotExistException("This Mobile Number does not exist");
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalanceException, MobileNoDoesNotExistException {
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer!=null)
		{
			Wallet wallet=new Wallet();
			if(customer.getWallet().getBalance().compareTo(amount)>=0)
			{
				wallet.setBalance(customer.getWallet().getBalance().subtract(amount));
				customer.setWallet(wallet);
				return customer;
			}
			throw new InsufficientBalanceException("Balance is not sufficient");
		}
		throw new MobileNoDoesNotExistException("This Mobile Number does not exist");
	}

}
