package com.example.demo.sevices;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.EmployeeNotFoundException;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeService {
	Connection con;
	EmployeeRepository repo;
	private static final Logger logger = LogManager.getRootLogger();

	public EmployeeService() {
		super();
		this.con = ConnectionFactory.getMySqlConnection();
		this.repo = new EmployeeRepositoryImpl(con);
	}

	public void save(Employee obj) {
		boolean hasSaved=this.repo.save(obj);
		if (hasSaved) {
			logger.info("is Employee Created:=" + hasSaved);
		} else {
			logger.error("is Employee Created:=" + hasSaved);
		}

	}

	public void findByFirstName(String firstNameOfEmployee) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByFirstName(firstNameOfEmployee);
			logger.info("List of employees who are having first name as: " + firstNameOfEmployee);
		} catch (EmployeeNotFoundException e) {
			logger.error("Not able to find with first name: " + firstNameOfEmployee);
		}
		for (Employee employee : employeeList) {
			logger.info(employee);
		}
	}

	public void findByFirstNameAndMobileNumber() {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByFirstNameAndMobileNumber();
			logger.info("First name and PhoneNumber of all employees");
		} catch (EmployeeNotFoundException e1) {
			logger.error("First name and PhoneNumber of employees cannot be found");
		}

		employeeList.forEach(e -> System.out.println(" FirstName :"+e.getFirstNameOfEmployee() + " and PhoneNumber: " + e.getPhoneNumberOfEmployee()));
	}

	public void updateTheEmailAndNumberOfEmployees(String updatedEmail, long phoneNumberOfEmployee, String emailOfEmployee) {
		try {
			boolean update = this.repo.updateTheEmailAndNumberOfEmployees(updatedEmail, phoneNumberOfEmployee, emailOfEmployee);
			logger.info("Does an employee with email: " + emailOfEmployee + " get updated:=" + update);
		} catch (EmployeeNotFoundException e) {
			logger.error(
					"Employee with the given email: " + emailOfEmployee + " cannot be found.So this employee cannot be updated");
		}
	}

	public void deleteByFirstName(String firstNameOfEmployee, String emailOfEmployee) {
		try {
			boolean delete = this.repo.deleteByFirstName(firstNameOfEmployee, emailOfEmployee);
			logger.info("Does an employee with email: " + emailOfEmployee + " get deleted:=" + delete);
		} catch (EmployeeNotFoundException e) {
			logger.error(
					"Particular Employee with the given name: " + firstNameOfEmployee + " and email: " + emailOfEmployee + " is not found");
		}
	}

	public void findByDateOfBirth(LocalDate dateOfBirthOfEmployee) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByDateOfBirth(dateOfBirthOfEmployee);
			logger.info("First name and PhoneNumber of all employees who have born on=" + dateOfBirthOfEmployee);
		} catch (EmployeeNotFoundException e1) {
			logger.error("No Employees have been found with the given date of birth: " + dateOfBirthOfEmployee);
		}
		for(Employee employee:employeeList) {
			System.out.println(" FirstName :"+employee.getFirstNameOfEmployee() + " and PhoneNumber: " + employee.getEmailOfEmployee());
		}
	}

	public void findByWeddingDateOfAnEmployee(LocalDate weddingDateOfEmployee) {
		Collection<Employee> employeeList = new ArrayList<>();
		
		try {
			employeeList = this.repo.findByWeddingDateOfAnEmployee(weddingDateOfEmployee);
			logger.info("First name and PhoneNumber of all employees who got married on=" + weddingDateOfEmployee);

		} catch (EmployeeNotFoundException e1) {
			logger.error("No Employees have been found with the given date of birth: " + weddingDateOfEmployee);
		}

	
		for(Employee employee:employeeList) {
			System.out.println(" FirstName :"+employee.getFirstNameOfEmployee() + " and PhoneNumber: " + employee.getPhoneNumberOfEmployee());
		}
	}
}
