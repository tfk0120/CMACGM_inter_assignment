package com.customerlist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerlist.api.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer>{

	
}
