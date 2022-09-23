package com.example.demo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private Connection con;

	public EmployeeRepositoryImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public Collection<Employee> findAll() {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select * from employee_details";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					employee = mapRowToObject(resultSet);
					employeeList.add(employee);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean save(Employee obj) {
		String sql = "insert into employee_details values (?,?,?,?,?,?,?)";
		int rowUpdated = 0;
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, obj.getFirstNameOfEmployee());
			pstmt.setString(2, obj.getLastNameOfEmployee());
			pstmt.setString(3, obj.getAddressOfEmployee());
			pstmt.setString(4, obj.getEmailOfEmployee());
			pstmt.setLong(5, obj.getPhoneNumberOfEmployee());
			Date dateOfBirthOfEmployee = Date.valueOf(obj.getDateOfBirthOfEmployee());
			pstmt.setDate(6, dateOfBirthOfEmployee);
			Date weddingDateOfEmployee = null;
			if (obj.getWeddingDateOfEmployee() != null) {
				weddingDateOfEmployee = Date.valueOf(obj.getWeddingDateOfEmployee());
			}
			pstmt.setDate(7, weddingDateOfEmployee);
			rowUpdated = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated == 1 ? true : false;
	}

	@Override
	public Collection<Employee> findByFirstName(String firstNameOfEmployee) throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		employeeList = findAll().stream().filter(e -> e.getFirstNameOfEmployee().equals(firstNameOfEmployee)).collect(Collectors.toList());
		if (employeeList.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-102", "Employee Not found with the given name: " + firstNameOfEmployee);
		} else {
			return employeeList;
		}
	}

	@Override
	public Collection<Employee> findByFirstNameAndMobileNumber() throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select firstNameOfEmployee,phoneNumberOfEmployee from employee_details";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowFirstNameAndNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowFirstNameAndNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-103", "No Employees Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean updateTheEmailAndNumberOfEmployees(String updatedEmail, long phoneNumberOfEmployee, String emailOfEmployee)
			throws EmployeeNotFoundException {
		String sql = "update employee_details SET emailOfEmployee=?, phoneNumberOfEmployee=? where emailOfEmployee=?";
		int rowUpdated = 0;
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, updatedEmail);
			statement.setLong(2, phoneNumberOfEmployee);
			statement.setString(3, emailOfEmployee);
			rowUpdated = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowUpdated == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-104",
				"Employee with the given email: " + emailOfEmployee + " cannot be found.So this employee cannot be updated");
		}

	}

	@Override
	public boolean deleteByFirstName(String firstNameOfEmployee, String emailOfEmployee) throws EmployeeNotFoundException {
		int rowDeleted = 0;
		String sql = "delete from employee_details where firstNameOfEmployee=? and emailOfEmployee=?";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, firstNameOfEmployee);
			statement.setString(2, emailOfEmployee);
			rowDeleted = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowDeleted == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-105",
					"Particular Employee with the given name: " + firstNameOfEmployee + " and email: " + emailOfEmployee + " is not found");
		}
	}

	@Override
	public Collection<Employee> findByDateOfBirth(LocalDate dateOfBirthOfEmployee)
			throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select firstNameOfEmployee,emailOfEmployee from employee_details where MONTH(dateOfBirthOfEmployee)=? and DAY(dateOfBirthOfEmployee)=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(dateOfBirthOfEmployee);
			pstmt.setInt(1, date.getMonth()+1);
			pstmt.setInt(2, date.getDate());
			System.out.println(date.getMonth()+" "+date.getDate());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowFirstNameAndEmail(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowFirstNameAndEmail(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-106",
						"No Employees have been found with the given date of birth: " + dateOfBirthOfEmployee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Collection<Employee> findByWeddingDateOfAnEmployee(LocalDate weddingDateOfEmployee)
			throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select firstNameOfEmployee,phoneNumberOfEmployee from employee_details where  MONTH(weddingDateOfEmployee)=? and DAY(weddingDateOfEmployee)=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(weddingDateOfEmployee);
			pstmt.setInt(1, date.getMonth()+1);
			pstmt.setInt(2, date.getDate());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowFirstNameAndNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowFirstNameAndNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-107",
						"No Employees have been found with the given Wedding Date: " + weddingDateOfEmployee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	private Employee mapRowToObject(ResultSet resultSet) throws SQLException {
		String firstNameOfEmployee = resultSet.getString("firstNameOfEmployee");
		String lastNameOfEmployee = resultSet.getString("lastNameOfEmployee");
		String addressOfEmployee = resultSet.getString("addressOfEmployee");
		String emailOfEmployee = resultSet.getString("emailOfEmployee");
		long phoneNumberOfEmployee = resultSet.getLong("phoneNumberOfEmployee");
		LocalDate dateOfBirthOfEmployee = resultSet.getDate("dateOfBirthOfEmployee").toLocalDate();
		LocalDate weddingDateOfEmployee = null;
		if (resultSet.getDate("weddingDateOfEmployee") != null) {
			weddingDateOfEmployee = resultSet.getDate("weddingDateOfEmployee").toLocalDate();
		}
		return new Employee(firstNameOfEmployee, lastNameOfEmployee, addressOfEmployee, emailOfEmployee, phoneNumberOfEmployee, dateOfBirthOfEmployee, weddingDateOfEmployee);
	}

	private Employee mapRowFirstNameAndNumber(ResultSet resultSet) throws SQLException {
		String firstNameOfEmployee = resultSet.getString("firstNameOfEmployee");
		long phoneNumberOfEmployee = resultSet.getLong("phoneNumberOfEmployee");
		return new Employee(firstNameOfEmployee, phoneNumberOfEmployee);
	}

	private Employee mapRowFirstNameAndEmail(ResultSet resultSet) throws SQLException {
		String firstNameOfEmployee = resultSet.getString("firstNameOfEmployee");
		String emailOfEmployee = resultSet.getString("emailOfEmployee");
		return new Employee(firstNameOfEmployee, emailOfEmployee);
	}

}
