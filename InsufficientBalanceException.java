package com.capgemini.exceptions;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String msg)
	{
		super(msg);
	}
	public InsufficientBalanceException()
	{}
}
