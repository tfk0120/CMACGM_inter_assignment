package com.customerlist.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.customerlist.api.model.Customer;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "phone")
	String phone;

	@Column(name = "email")
	String email;

	@Column(name = "city")
	String city;

	@Column(name = "zip_code")
	String zipCode;

	@Column(name = "addr_line")
	String addrLine;

	@Column(name = "addr_supp")
	String addrSupp;

	@Column(name = "career")
	String career;

	public CustomerEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerEntity(Customer customer) {
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.phone = customer.getPhone();
		this.email = customer.getEmail();
		this.city = customer.getCity();
		this.zipCode = customer.getZipCode();
		this.addrLine = customer.getAddrLine();
		this.addrSupp = customer.getAddrSupp();
		this.career = customer.getCareer();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddrLine() {
		return addrLine;
	}

	public void setAddrLine(String addrLine) {
		this.addrLine = addrLine;
	}

	public String getAddrSupp() {
		return addrSupp;
	}

	public void setAddrSupp(String addrSupp) {
		this.addrSupp = addrSupp;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}
	
	
	
}
