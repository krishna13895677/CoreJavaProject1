package com.training.model;

import java.time.LocalDate;
import java.util.Scanner;
import com.example.demo.sevices.EmployeeService;
import com.training.exceptions.EmployeeNotFoundException;

public class App {

	public static LocalDate areYouMarried(Scanner input) {
		while (true) {
			System.out.println("Are you married? y/n or Y/N");
			String choiceForWedding = input.nextLine();
			if (choiceForWedding.equalsIgnoreCase("y")) {
				System.out.println("Wedding date:");
				LocalDate weddingDateOfEmployee = LocalDate.parse(input.nextLine());
				return weddingDateOfEmployee;
			}
			else if (choiceForWedding.equalsIgnoreCase("n")) {
				return null;
			}
			else {
				System.out.println("PROVIDE VALID OPTION");
			}
		}
	}

	public static void gettingEmployeeDetails() throws EmployeeNotFoundException {
		Scanner input = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		while (true) {
			System.out.println("Enter number between 1 to 7");
			System.out.println("1-----Add Employee");
			System.out.println("2-----Find Employees By First Name");
			System.out.println("3-----Find First Name and Phone Number of all Employees");
			System.out.println("4-----Update Email and PhoneNumber of a Particular Employee");
			System.out.println("5-----Delete Employee by First Name");
			System.out.println("6-----Find First Name and Email of all Employees by Birthday");
			System.out.println("7-----Find First Name and Phone Number of all Employees by Wedding Date");
			System.out.println("Enter the number");
			int choice = Integer.parseInt(input.nextLine());
			if (choice == 1) {
				System.out.println("-------------Enter required details to add Employee---------------------");
				System.out.println("First Name:");
				String firstNameOfEmployee = input.nextLine();
				System.out.println("Last Name:");
				String lastNameOfEmployee = input.nextLine();
				System.out.println("Address:");
				String addressOfEmployee = input.nextLine();
				System.out.println("Email:");
				String emailOfEmployee = input.nextLine();
				System.out.println("Phone Number:");
				long phoneNumberOfEmployee=Long.parseLong(input.nextLine());
				System.out.println("Date of birth:");
				LocalDate dateOfBirthOfEmployee = LocalDate.parse(input.nextLine());
				LocalDate weddingDateOfEmployee =areYouMarried(input);
				service.save(new Employee(firstNameOfEmployee, lastNameOfEmployee, addressOfEmployee, emailOfEmployee, phoneNumberOfEmployee, dateOfBirthOfEmployee, weddingDateOfEmployee));
			} else if (choice == 2) {
				System.out.println("2->Find Employees By First Name");
				System.out.println("First Name:");
				String firstNameOfEmployee = input.nextLine();
				service.findByFirstName(firstNameOfEmployee);
			} else if (choice == 3) {
				System.out.println("3->Find First Name and Phone Number of all Employees");
				service.findByFirstNameAndMobileNumber();
			} else if (choice == 4) {
				System.out.println("4->Update Email and PhoneNumber of a Particular Employee");
				System.out.println("Updated Email:");
				String updatedEmail = input.nextLine();
				System.out.println("Phone Number:");
				 long phoneNumberOfEmployee=Long.parseLong(input.nextLine());
				System.out.println("Old Email:");
				String emailOfEmployee = input.nextLine();
				service.updateTheEmailAndNumberOfEmployees(updatedEmail, phoneNumberOfEmployee, emailOfEmployee);
			} else if (choice == 5) {
				System.out.println("5->Delete Employee by First Name");
				System.out.println("First Name:");
				String firstNameOfEmployee = input.nextLine();
				System.out.println("Email:");
				String emailOfEmployee = input.nextLine();
				service.deleteByFirstName(firstNameOfEmployee, emailOfEmployee);
			} else if (choice == 6) {
				System.out.println("6->Find First Name and Email of all Employees by Birthday");
				System.out.println("Date of birth:");
				LocalDate dateOfBirthOfEmployee = LocalDate.parse(input.nextLine());
				service.findByDateOfBirth(dateOfBirthOfEmployee);
			} else if (choice == 7) {
				System.out.println("7->Find First Name and Phone Number of all Employees by Wedding Date");
				System.out.println("Wedding date:");
				LocalDate weddingDateOfEmployee = LocalDate.parse(input.nextLine());
				service.findByWeddingDateOfAnEmployee(weddingDateOfEmployee);
			} else {
				System.out.println("ONLY ENTER THE NUMBER BETWEEN 1 TO 7");
				continue;
			}
			if (!doYouWantToContinue(input, service)) {
				break;
			}

		}

	}

	public static boolean doYouWantToContinue(Scanner input, EmployeeService service) throws EmployeeNotFoundException {
		while (true) {
			System.out.println("Do you want to continue? y/n or Y/N");
			String continueChoice = input.nextLine();
			if (continueChoice.equalsIgnoreCase("y")) {
				return true;

			} else if (continueChoice.equalsIgnoreCase("n")) {
				input.close();
				System.out.println("Successfully Exited from the Menu");
				return false;

			} else {
				System.out.println("PROVIDE VALID OPTION!!!!!!!!!!!");
			}
		}

	}

	public static void main(String[] args) throws EmployeeNotFoundException {
		gettingEmployeeDetails();
	}

}
