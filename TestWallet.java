package com.capgemini.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNoAlreadyExistException;
import com.capgemini.exceptions.MobileNoDoesNotExistException;
import com.capgemini.service.WalletServiceImpl;

public class TestWallet {
	WalletServiceImpl walletService=new WalletServiceImpl();
	@Test(expected=com.capgemini.exceptions.MobileNoDoesNotExistException.class)
	public void whenMobileNumberDoesNotExistWhileDepositingAmount()throws MobileNoDoesNotExistException {
		BigDecimal amount=new BigDecimal(1000);
		walletService.depositAmount("7060608501",amount);
	}
	@Test(expected=com.capgemini.exceptions.MobileNoAlreadyExistException.class)
	public void whenAllEntriesAreCorrectWhileCreatingNewAccount()throws MobileNoAlreadyExistException{
		BigDecimal amount=new BigDecimal(2000);
		walletService.createAccount("Aman","7060608501",amount);
		walletService.createAccount("Aman","7060608501",amount);
		
	}
	@Test(expected=com.capgemini.exceptions.MobileNoDoesNotExistException.class)
	public void whenMobileNoDoesnotExistWhileWithdrawingAmount()throws MobileNoDoesNotExistException, InsufficientBalanceException
	{
		BigDecimal amount=new BigDecimal(1000);
		walletService.withdrawAmount("7599260545", amount);
	}
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenBalanceIsNotSufficientWhileTransferingFunds()throws InsufficientBalanceException, MobileNoDoesNotExistException, MobileNoAlreadyExistException
	{
		BigDecimal amount=new BigDecimal(2000);
		walletService.createAccount("Raj","7599260545",amount);
		walletService.createAccount("Aman","7060608501",amount);
		BigDecimal amount1=new BigDecimal(3000);
		walletService.fundTransfer("7060608501", "7599260545", amount1);
	}
	@Test(expected=com.capgemini.exceptions.MobileNoDoesNotExistException.class)
	public void whenMobileNoDoesNotExistWhileBalanceShow()throws MobileNoDoesNotExistException
	{
		walletService.showBalance("7060608501");
	}
	@Test(expected=com.capgemini.exceptions.MobileNoDoesNotExistException.class)
	public void whenSenderMobileNoDoesNotExistWhileFundsTransfer()throws MobileNoDoesNotExistException, MobileNoAlreadyExistException, InsufficientBalanceException
	{
		BigDecimal amount=new BigDecimal(1000);
		walletService.createAccount("Aman","7599260545", amount);
		BigDecimal amount1=new BigDecimal(2000);
		walletService.fundTransfer("7060608501","7599260545", amount1);	
	}
	@Test(expected=com.capgemini.exceptions.MobileNoDoesNotExistException.class)
	public void whenReceiverMobileNoDoesNotExistWhileFundsTransfer()throws MobileNoDoesNotExistException, MobileNoAlreadyExistException, InsufficientBalanceException
	{
		BigDecimal amount=new BigDecimal(1000);
		walletService.createAccount("Aman","7599260545", amount);
		BigDecimal amount1=new BigDecimal(2000);
		walletService.fundTransfer("7599260545","7060608501", amount1);	
	}
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenRemainingBalanceIsInsufficientWhileWithdrawingAmount()throws MobileNoDoesNotExistException, InsufficientBalanceException, MobileNoAlreadyExistException
	{
		BigDecimal amount=new BigDecimal(1000);
		walletService.createAccount("Aman","7599260545", amount);
		BigDecimal amount1=new BigDecimal(2000);
		walletService.withdrawAmount("7599260545", amount1);
	}

}
