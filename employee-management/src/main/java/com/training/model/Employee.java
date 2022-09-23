package com.training.model;

import java.time.LocalDate;

public class Employee {
	private String firstNameOfEmployee;
	private String lastNameOfEmployee;
	private String addressOfEmployee;
	private String emailOfEmployee;
	private long phoneNumberOfEmployee;
	private LocalDate dateOfBirthOfEmployee;
	private LocalDate weddingDateOfEmployee;
	public Employee() {
		super();
		
	}
	public Employee(String firstNameOfEmployee, String lastNameOfEmployee, String addressOfEmployee,
			String emailOfEmployee, long phoneNumberOfEmployee, LocalDate dateOfBirthOfEmployee,
			LocalDate weddingDateOfEmployee) {
		super();
		this.firstNameOfEmployee = firstNameOfEmployee;
		this.lastNameOfEmployee = lastNameOfEmployee;
		this.addressOfEmployee = addressOfEmployee;
		this.emailOfEmployee = emailOfEmployee;
		this.phoneNumberOfEmployee = phoneNumberOfEmployee;
		this.dateOfBirthOfEmployee = dateOfBirthOfEmployee;
		this.weddingDateOfEmployee = weddingDateOfEmployee;
	}
	public Employee(String firstNameOfEmployee, long phoneNumberOfEmployee) {
		super();
		this.firstNameOfEmployee = firstNameOfEmployee;
		this.phoneNumberOfEmployee = phoneNumberOfEmployee;
	}
	public Employee(String firstNameOfEmployee, String emailOfEmployee) {
		super();
		this.firstNameOfEmployee = firstNameOfEmployee;
		this.emailOfEmployee = emailOfEmployee;
	}
	public String getFirstNameOfEmployee() {
		return firstNameOfEmployee;
	}
	public void setFirstNameOfEmployee(String firstNameOfEmployee) {
		this.firstNameOfEmployee = firstNameOfEmployee;
	}
	public String getLastNameOfEmployee() {
		return lastNameOfEmployee;
	}
	public void setLastNameOfEmployee(String lastNameOfEmployee) {
		this.lastNameOfEmployee = lastNameOfEmployee;
	}
	public String getAddressOfEmployee() {
		return addressOfEmployee;
	}
	public void setAddressOfEmployee(String addressOfEmployee) {
		this.addressOfEmployee = addressOfEmployee;
	}
	public String getEmailOfEmployee() {
		return emailOfEmployee;
	}
	public void setEmailOfEmployee(String emailOfEmployee) {
		this.emailOfEmployee = emailOfEmployee;
	}
	public long getPhoneNumberOfEmployee() {
		return phoneNumberOfEmployee;
	}
	public void setPhoneNumberOfEmployee(long phoneNumberOfEmployee) {
		this.phoneNumberOfEmployee = phoneNumberOfEmployee;
	}
	public LocalDate getDateOfBirthOfEmployee() {
		return dateOfBirthOfEmployee;
	}
	public void setDateOfBirthOfEmployee(LocalDate dateOfBirthOfEmployee) {
		this.dateOfBirthOfEmployee = dateOfBirthOfEmployee;
	}
	public LocalDate getWeddingDateOfEmployee() {
		return weddingDateOfEmployee;
	}
	public void setWeddingDateOfEmployee(LocalDate weddingDateOfEmployee) {
		this.weddingDateOfEmployee = weddingDateOfEmployee;
	}
	@Override
	public String toString() {
		return "Employee [firstNameOfEmployee=" + firstNameOfEmployee + ", lastNameOfEmployee=" + lastNameOfEmployee
				+ ", addressOfEmployee=" + addressOfEmployee + ", emailOfEmployee=" + emailOfEmployee
				+ ", phoneNumberOfEmployee=" + phoneNumberOfEmployee + ", dateOfBirthOfEmployee="
				+ dateOfBirthOfEmployee + ", weddingDateOfEmployee=" + weddingDateOfEmployee + "]";
	}
	










}
