package com.customerlist.api.model;

public class Customer {
	
	private int id;
	
	private String firstName;
	
	private String lastName;

	private String phone;

	private String email;

	private String city;

	private String zipCode;

	private String addrLine;

	private String addrSupp;

	private String career;

	public Customer(String firstName, String lastName, String phone, String email, String city, String zipCode,
			String addrLine, String addrSupp, String career) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.zipCode = zipCode;
		this.addrLine = addrLine;
		this.addrSupp = addrSupp;
		this.career = career;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
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
