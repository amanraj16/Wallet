package com.capgemini.beans;

import java.math.BigDecimal;

public class Customer {
	private String name;
	private String mobileno;
	private Wallet wallet;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, String mobileno, Wallet wallet) {
		super();
		this.name = name;
		this.mobileno = mobileno;
		this.wallet = wallet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}
