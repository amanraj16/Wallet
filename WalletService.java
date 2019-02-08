package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNoAlreadyExistException;
import com.capgemini.exceptions.MobileNoDoesNotExistException;

public interface WalletService {
	public Customer createAccount(String name,String mobileno,BigDecimal amount) throws MobileNoAlreadyExistException;
	public Customer showBalance(String mobileno) throws MobileNoDoesNotExistException;
	public Customer fundTransfer(String sourceMobileNo,String targetmobileNo,BigDecimal amount) throws InsufficientBalanceException, MobileNoDoesNotExistException;
	public Customer depositAmount(String mobileNo,BigDecimal amount) throws MobileNoDoesNotExistException;
	public Customer withdrawAmount(String mobileNo,BigDecimal amount) throws InsufficientBalanceException, MobileNoDoesNotExistException;
}
