package com.customerlist.api.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.customerlist.api.entity.CustomerEntity;
import com.customerlist.api.exception.ResultCode;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.model.Customer;
import com.customerlist.api.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<CustomerEntity> listCustomers(int page, int size, String sortBy) throws ResultCodeException {
		if(size == 0)
			return  customerRepository.findAll();
		if(sortBy==null || sortBy.isEmpty()) {
			Pageable firstPageWithTwoElements = PageRequest.of(page, size);
			return customerRepository.findAll(firstPageWithTwoElements).toList();
		}
		Pageable firstPageWithTwoElements = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return customerRepository.findAll(firstPageWithTwoElements).toList();
		
	}

	@Override
	public CustomerEntity getCustomer(int id) throws ResultCodeException {
		try {
			return customerRepository.getById(id);
		}catch(EntityNotFoundException ex) {
			throw ResultCode.RESOURCE_NOT_FOUND.exception();
		}
	}

	@Override
	public CustomerEntity addCustomer(Customer customer) throws ResultCodeException {
		return customerRepository.save(new CustomerEntity(customer));
	}

	@Override
	public void deleteCustomer(int id) throws ResultCodeException {
		try {
			customerRepository.getById(id);
		}catch(EntityNotFoundException ex) {
			throw ResultCode.RESOURCE_NOT_FOUND.exception();
		}
		customerRepository.deleteById(id);
	}

	@Override
	public CustomerEntity editCustomer(Customer customer) throws ResultCodeException {
		try {
			customerRepository.getById(customer.getId());
		}catch(EntityNotFoundException ex) {
			throw ResultCode.RESOURCE_NOT_FOUND.exception();
		}
		return customerRepository.save(new CustomerEntity(customer));
	}

}
