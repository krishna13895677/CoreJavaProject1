package com.training.ifaces;

import java.time.LocalDate;
import java.util.Collection;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee> {
	public Collection<Employee> findByFirstName(String empFirstName) throws EmployeeNotFoundException;

	public Collection<Employee> findByFirstNameAndMobileNumber() throws EmployeeNotFoundException;

	public boolean updateTheEmailAndNumberOfEmployees(String updatedEmail, long empPhoneNumber, String empEmail) throws EmployeeNotFoundException;

	public boolean deleteByFirstName(String empLastName, String empEmail) throws EmployeeNotFoundException;

	public Collection<Employee> findByDateOfBirth(LocalDate empDateOfBirth)
			throws EmployeeNotFoundException;

	public Collection<Employee> findByWeddingDateOfAnEmployee(LocalDate empWeddingDate)
			throws EmployeeNotFoundException;
}
