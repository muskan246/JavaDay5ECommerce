package com.robomq.service;

import java.sql.ResultSet;

import com.robomq.pojo.Customer;

public interface CustomerService {
	public boolean createCustomer(Customer c);
	//public boolean createCustomer(Customer c);
	public boolean loginCustomer(int cid, String cmobile);
	public ResultSet displayCustomer(int cid);
	public ResultSet updateCustomer(int cid);
	public void deletePreStatement(int cid);
	
}
