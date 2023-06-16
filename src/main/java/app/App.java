package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import controller.EmployeeController;
import controller.EmployeeControllerImpl;
import model.dto.ContractDto;
import model.dto.EmployeeDto;
import model.dto.EmployeeReportDto;
import model.entities.employee.Employee;
import repository.EmployeeDatabaseImpl;
import usescases.EmployeeUseCaseImpl;

import static app.Screen.*;

public class App {

    private static final EmployeeController employeeController = new EmployeeControllerImpl(new EmployeeUseCaseImpl(new EmployeeDatabaseImpl(new InitialDatabase().getEmployeeList())));

    public static void main(String[] args) {
        printInitialScreen();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        while (decision != 0) {
            switch (decision) {
                case 1 -> calculatePayroll();
                case 2 -> generatePayrollReport();
                case 3 -> manageUsers();
            }
            printInitialScreen();
            decision = scanner.nextInt();
        }
    }

    private static void calculatePayroll() {
        System.out.println();
        System.out.println("***************************************");
        LocalDate startPeriodDate = InputReader.getDate("Insert the date from when to calculate payroll (dd-MM-yyyy): ");
        LocalDate endPeriodDate = InputReader.getDate("Insert the date until when to calculate payroll (dd-MM-yyyy): ");
        double payroll = employeeController.calculatePayroll(startPeriodDate, endPeriodDate);
        System.out.println("You have to pay: " + payroll + " " + "from: " + startPeriodDate.toString() + " to " + endPeriodDate.toString());
    }

    private static void generatePayrollReport() {
        System.out.println();
        System.out.println("***************************************");
        LocalDate startPeriodDate = InputReader.getDate("Insert the date from when to generate payroll report (dd-MM-yyyy): ");
        LocalDate lastPeriodDate = InputReader.getDate("Insert the date until when to generate payroll report (dd-MM-yyyy): ");
        ArrayList<EmployeeReportDto> reports = (ArrayList<EmployeeReportDto>) employeeController.generatePayrollReport(startPeriodDate, lastPeriodDate);
        for (EmployeeReportDto report: reports) {
            System.out.println("Report Employee: " + report.getName());
            System.out.println("- Payroll: " + report.getPayroll());
            System.out.println("- Changes:");
            for (String str: report.getChanges()) {
                System.out.println("   - " + str);
            }
        }
    }

    private static void manageUsers() {
        printManageUsersScreen();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        while (decision != 0) {
            switch (decision) {
                case 1 -> addEmployee();
                case 2 -> editEmployee();
                case 3 -> deleteEmployee();
                case 4 -> viewAllEmployees();
            }
            printManageUsersScreen();
            decision = scanner.nextInt();
        }
    }

    private static void addEmployee() {
        String name = InputReader.getString("Enter employee full name: ");
        String phoneNumber = InputReader.getString("Enter employee phoneNumber: ");
        ContractDto contractDto = getContract();
        EmployeeDto employeeDto = new EmployeeDto(name, phoneNumber);
        employeeController.addEmployee(employeeDto, contractDto);
    }

    private static void editEmployee() {
        employeeController.printEmployees();
        String employeeName = InputReader.getString("Select an employee to modify: ");
        Employee employee = employeeController.getEmployee(employeeName);
        if (employee == null) {
            System.out.println("That employee does not exist, please select an existing employee");
            editEmployee();
        }
        else {
            chooseEdit(employee);
        }
    }

    private static void chooseEdit(Employee employee) {
        printEmployeeDataScreen(employee);
        chooseEditScreen();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        Employee updatedEmployee = employee;
        while (decision != 0) {
            switch (decision) {
                case 1 -> {
                    String name = InputReader.getString("Enter employee full name: ");
                    String phoneNumber = InputReader.getString("Enter employee phoneNumber: ");
                    updatedEmployee = new Employee(employee.getID(), name, phoneNumber, employee.getContract()); //Pasarle un UpdateProfileDto (Con id, name y phoneNumber)
                    employeeController.editEmployee(updatedEmployee, employee.getID());
                }
                case 2 -> {
                    ContractDto contract = getContract();
                    updatedEmployee = new Employee(employee.getID(), employee.getName(), employee.getPhoneNumber(), contract); //Pasarle un UpdateContract (Con id y contract)
                    employeeController.editEmployee(updatedEmployee, employee.getID());
                }
                case 3 -> {
                    Employee undoEmployee = employeeController.undo(employee.getID());
                    if (undoEmployee != null) updatedEmployee = undoEmployee;
                }
                case 4 -> {
                    Employee redoEmployee = employeeController.redo(employee.getID());
                    if (redoEmployee != null) updatedEmployee = redoEmployee;
                }
                default -> {
                    System.out.println("Please select a valid option");
                    chooseEdit(employee);
                }
            }
            printEmployeeDataScreen(updatedEmployee);
            chooseEditScreen();
            decision = scanner.nextInt();
        }
    }

    private static ContractDto getContract() {
        ContractDto contract = null;
        chooseContractScreen();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        switch (decision) {
            case 1 -> {
                LocalDate start = InputReader.getDate("Contract start date (dd-MM-yyyy): ");
                LocalDate finish = InputReader.getDate("Contract finish date (dd-MM-yyyy): ");
                int payPerHour = InputReader.getInt("Pay per hour: ");
                contract = new FullTimeContractDto(start, finish, payPerHour);
            }
            case 2 -> {
                LocalDate start = InputReader.getDate("Contract start date (dd-MM-yyyy): ");
                LocalDate finish = InputReader.getDate("Contract finish date (dd-MM-yyyy): ");
                int payPerHour = InputReader.getInt("Pay per hour: ");
                int totalHours = InputReader.getInt("Total hours: ");
                contract = new TotalHourContractDto(start, finish, payPerHour, totalHours);
            }
        }
        return contract;
    }


    private static void deleteEmployee() {
        String employeeName = InputReader.getString("Select a employee to modify: ");
        Employee employee = employeeController.getEmployee(employeeName);
        if (employee == null) {
            System.out.println("That employee does not exist, please select an existing employee");
            editEmployee();
        }
        else {
            employeeController.deleteEmployee(employee.getID());
        }
    }

    private static void viewAllEmployees() {
        employeeController.printEmployees();
    }

}
