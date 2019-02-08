package com.capgmini.repo;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.beans.Customer;

public class WalletRepoImpl implements WalletRepo {
	Map<String,Customer> map=new HashMap<>();
	@Override
	public boolean save(Customer customer) {
		map.put(customer.getMobileno(),customer);
		return true;
	}

	@Override
	public Customer findOne(String mobileno) {
		Customer customer=map.get(mobileno);
		return customer;
	}

}
