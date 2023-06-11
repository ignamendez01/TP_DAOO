package app;

import java.time.LocalDate;
import java.util.Scanner;

import controller.EmployeeController;
import controller.EmployeeControllerImpl;
import model.entities.contract.Contract;
import model.entities.contract.FullTimeContract;
import model.entities.contract.TotalHourContract;
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
                case 1 -> {
                    calculatePayroll();
                }
                case 2 -> {
                    generatePayrollReport();
                }
                case 3 -> {
                    manageUsers();
                }
            }
            printInitialScreen();
            decision = scanner.nextInt();
        }
    }

    private static void calculatePayroll() {
        System.out.println();
        System.out.println("***************************************");
        LocalDate localDate = InputReader.getDate("Insert day to calculate payroll (dd-MM-yyyy): ");
        double payroll = employeeController.calculatePayroll(localDate);
        System.out.println("You have to pay: " + payroll + " " + "at the date: " + localDate.toString());
    }

    private static void generatePayrollReport() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("Insert day to generate payroll report");
        // generar el reporte
        // imprimir el reporte
    }

    private static void manageUsers() {
        printManageUsersScreen();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        while (decision != 0) {
            switch (decision) {
                case 1 -> {
                    addEmployee();
                }
                case 2 -> {
                    editEmployee();
                }
                case 3 -> {
                    deleteEmployee();
                }
                case 4 -> {
                    viewAllEmployees();
                }
            }
            printManageUsersScreen();
            decision = scanner.nextInt();
        }
    }

    private static void addEmployee() {
        String name = InputReader.getString("Enter employee full name: ");
        String phoneNumber = InputReader.getString("Enter employee phoneNumber: ");
        Contract contract = getContract();
        Employee employee = new Employee(name, phoneNumber, contract);
        employeeController.addEmployee(employee);
    }

    private static void editEmployee() {
        String employeeName = InputReader.getString("Select a employee to modify: ");
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
                    updatedEmployee = new Employee(employee.getID(), name, phoneNumber, employee.getContract());
                    employeeController.editEmployee(updatedEmployee, employee.getID());
                }
                case 2 -> {
                    Contract contract = getContract();
                    updatedEmployee = new Employee(employee.getID(), employee.getName(), employee.getPhoneNumber(), contract);
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

    private static Contract getContract() {
        Contract contract = null;
        chooseContractScreen();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        switch (decision) {
            case 1 -> {
                LocalDate start = InputReader.getDate("Contract start date (dd-MM-yyyy): ");
                LocalDate finish = InputReader.getDate("Contract finish date (dd-MM-yyyy): ");
                int payPerHour = InputReader.getInt("Pay per hour: ");
                contract = new FullTimeContract(start, finish, payPerHour);
            }
            case 2 -> {
                LocalDate start = InputReader.getDate("Contract start date (dd-MM-yyyy): ");
                LocalDate finish = InputReader.getDate("Contract finish date (dd-MM-yyyy): ");
                int payPerHour = InputReader.getInt("Pay per hour: ");
                int totalHours = InputReader.getInt("Total hours: ");
                contract = new TotalHourContract(start, finish, payPerHour, totalHours);
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
