package com.customerlist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.customerlist.api.entity.CustomerEntity;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.model.Customer;
import com.customerlist.api.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "testing";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerEntity>> listCustomers(@RequestParam(required = false) int page,
			@RequestParam(required = false) int size, @RequestParam(required = false) String sortBy) {
		List<CustomerEntity> customersList = null;
		try {
			customersList = customerService.listCustomers(page, size, sortBy);
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(ex.getResultCode().getMappedHttpStatus());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<CustomerEntity>>(customersList, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerEntity> getCustomer(@PathVariable("id") int id) {
		CustomerEntity customer;
		try {
			customer = customerService.getCustomer(id);
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(ex.getResultCode().getMappedHttpStatus());
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CustomerEntity>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<CustomerEntity> addCustomer(@RequestBody Customer customer) {
		CustomerEntity customerEntity;
		try {
			customerEntity = customerService.addCustomer(customer);
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(ex.getResultCode().getMappedHttpStatus());
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CustomerEntity>(customerEntity, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteCustomer(@PathVariable("id") int id) {
		try {
			customerService.deleteCustomer(id);
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(ex.getResultCode().getMappedHttpStatus());
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<CustomerEntity> editCustomer(@RequestBody Customer customer) {
		CustomerEntity customerEntity;
		try {
			customerEntity = customerService.editCustomer(customer);
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(ex.getResultCode().getMappedHttpStatus());
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CustomerEntity>(customerEntity, HttpStatus.OK);
	}

}
