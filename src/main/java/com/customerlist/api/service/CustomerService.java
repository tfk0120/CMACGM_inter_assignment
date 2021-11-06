package com.customerlist.api.service;

import java.util.List;

import com.customerlist.api.entity.CustomerEntity;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.model.Customer;

public interface CustomerService {
	
	public List<CustomerEntity> listCustomers(int page, int size, String sortBy) throws ResultCodeException;

	public CustomerEntity getCustomer(int id) throws ResultCodeException;

	public CustomerEntity addCustomer(Customer customer) throws ResultCodeException;

	public void deleteCustomer(int id) throws ResultCodeException;

	public CustomerEntity editCustomer(Customer customer) throws ResultCodeException;
}
